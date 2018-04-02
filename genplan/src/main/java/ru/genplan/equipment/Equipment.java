package ru.genplan.equipment;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ru.genplan.mybatis.mappers.PlanogramData;
import ru.genplan.mybatis.mappers.SectionData;

public class Equipment {
   private static final Logger LOG = LogManager.getLogger(Equipment.class);
   private PlanogramData planogram;
   private Map<Integer,SectionData> sections;
   private List<IFixtureData> fixtures;
   
   
   public Equipment(PlanogramData planogram
		          , List<SectionData> sections
		          , List<IFixtureData> fixtures) {
		super();
		LOG.debug("Equipment start");
		this.planogram = planogram;
		initSectionNum(sections);
		this.sections = new HashMap<Integer,SectionData>();
		for (SectionData s: sections) {
			this.sections.put(s.getSectionNo(), s);
		}
		this.fixtures = fixtures;
		initFixtureNum();
   }
   
   public List<IFixture> generate(IFixtureGenerator generator) {
	   return new ArrayList<IFixture>();
   }
   
   /**
    * Определяем для полок абсолютные и относительные номера
    * абсолютный номер полки не зависит от блока а зависит лишь от оборудования.
    * относительный номер полки зависит от блока
    */
   private void initFixtureNum() {
	   int fixNo = -1;
	   int fixNum = 1;
	   int priorY = 2000;
	   int spaceAvailable;
	   // Заполнение нумерации сверху вниз
	   // Сортируем полки
	   //    - по номеру сегмента ASC
	   //    - по Y DESC
	   fixtures.sort(
			         Comparator.comparingInt(IFixtureData::getFirstSectionNo)
			                   .thenComparingInt(IFixtureData::getY).reversed()
	   );
	   for (IFixtureData f:fixtures) {
		   if (f.getFirstSectionNo() != fixNo) {
			   fixNum = 1;
			   spaceAvailable = 2000 - f.getY() - f.getHeight();
		   } else {
			   fixNum++;
			   spaceAvailable = priorY - f.getY() - f.getHeight();
		   }
		   if (f.getSpaceAvailable()==null) {
			   f.setSpaceAvailable(spaceAvailable);
		   }
		   priorY = f.getY();
		   if (f.getUpFixNo()==null) {
			   f.setUpFixNo(fixNum);
		   }
		   //f.setRelativeUpFixNo(fixNum);
		   fixNo = f.getFirstSectionNo();
	  }
	   
	  fixNo = -1;
	  fixNum = 1;
	  // Заполнение нумерации снизу вверх
	  fixtures.sort(Comparator.comparingInt(IFixtureData::getFirstSectionNo)
		    		          .thenComparingInt(IFixtureData::getY)
	  );
	  for (IFixtureData f:fixtures) {
		  if (f.getFirstSectionNo() != fixNo) {
		      fixNum = 1;
		  } else {
			  fixNum++;
		  }
		  if (f.getDownFixNo()==null) {
		      f.setDownFixNo(fixNum);
		  }
		  //f.setRelativeDownFixNo(fixNum);
		  fixNo = f.getFirstSectionNo();
	  }
   }
   /**
    * Для каждой секции определяем 
    *    номер Группы (слева и справа)
    *    номер секции (слева и справа)
    */
   public void initSectionNum(List<SectionData> sec) {
	   Integer curX = null;
	   Integer groupNo = 1;
	   Integer secNo = 1;
	   sec.sort(Comparator.comparingInt(SectionData::getSectionNo));
	   for(SectionData s:sec) {
		  if (curX != null && curX.intValue() != s.getX().intValue()) {
			// Увеличиваем счетчик группы
			  LOG.debug("curX="+curX+" x="+s.getX());
			  groupNo++;
		  }
		  s.setGroupLeftNo(groupNo);
		  s.setLeftNo(s.getSectionNo());
		  curX = s.getX() + s.getWidth();
	   }
	   
	   curX = null;
	   groupNo = 1;
	   sec.sort(Comparator.comparingInt(SectionData::getSectionNo).reversed());
	   for(SectionData s:sec) {
		  if (curX != null && curX != s.getX()+s.getWidth()) {
			// Увеличиваем счетчик группы
			  groupNo++;
		  }
		  s.setGroupRightNo(groupNo);
		  s.setRightNo(secNo);
		  secNo++;
		  curX = s.getX();
	   }
   }
}
