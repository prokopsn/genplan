package ru.genplan.mybatis;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import ru.genplan.equipment.IEquipment;
import ru.genplan.equipment.IEquipmentFactory;
import ru.genplan.equipment.IFixture;
import ru.genplan.mybatis.mappers.EquipmentService;
import ru.genplan.mybatis.mappers.FixtureData;

import ru.genplan.mybatis.mappers.SectionData;

public class BaseEquipmentFactory implements IEquipmentFactory {
   private static final Logger LOG = LogManager.getLogger(BaseEquipmentFactory.class);
   
   /**
	* Для каждой секции определяем 
	*    номер Группы (слева и справа)
	*    номер секции (слева и справа)
	*/
	private Map<Integer,SectionData> initSections(List<SectionData> sec) {
	   Map<Integer,SectionData> result = new HashMap<>();
	   int curX = -1000;
       int groupNo = 0;
	   int secNo = 1;
	   sec.sort(Comparator.comparingInt(SectionData::getSectionNo));
	   for(SectionData s:sec) {
		  if (curX != s.getX()) {
		      // Увеличиваем счетчик группы
			  groupNo++;
		  }
		  s.setGroupLeftNo(groupNo);
		  s.setLeftNo(s.getSectionNo());
		  curX = s.getX() + s.getWidth();
	   }
		   
	   curX = -1000;
	   groupNo = 0;
	   sec.sort(Comparator.comparingInt(SectionData::getSectionNo).reversed());
	   for(SectionData s:sec) {
		  if (curX != s.getX()+s.getWidth()) {
			// Увеличиваем счетчик группы
	   	    groupNo++;
		  }
		  s.setGroupRightNo(groupNo);
		  s.setRightNo(secNo);
		  secNo++;
		  curX = s.getX();
		  result.put(s.getSectionNo(), s);
	   }
	   return result;
	}
	
	/**
	 * Определяем для полок абсолютные и относительные номера
	 * абсолютный номер полки не зависит от блока а зависит лишь от оборудования.
	 * относительный номер полки зависит от блока
	 */
	private void initFixtures(List<FixtureData> fixtures, Map<Integer,SectionData> sections) {
	   int fixNo = -1;
	   int fixNum = 1;
	   int priorY = 2000;
	   int spaceAvailable;
	   List<FixtureData> fixs = new ArrayList<>();
	   FixtureData fix = null;
	   SectionData sec;
	   // Связываем полки с секциями.
	   // Если полка принадлежит нескольким секциям,
	   // делим полку между ними
	   for(FixtureData f:fixtures) {
		   if (f.getFirstSectionNo()!= -1 
			   && f.getLastSectionNo()!=-1 
			   && f.getLastSectionNo()!=f.getFirstSectionNo()) {
			   LOG.trace("Fixture id={}, x={}, y={}, firstSectionNo={}, lastSectionNo={}", f.getId(), f.getX(), f.getY(), f.getFirstSectionNo(), f.getLastSectionNo());
			   for(int i = f.getFirstSectionNo(); i<=f.getLastSectionNo();i++) {
				   sec = sections.get(i);
				   LOG.trace("Section={}, x={}, width={}",sec.getSectionNo(), sec.getX(), sec.getWidth());
				   fix = new FixtureData(f);
				   LOG.trace("Fixture, x={}, width={}",fix.getX(), fix.getWidth());
				   fix.setX(Math.max(f.getX(), sec.getX()));
				   LOG.trace("Fixture new x={}",fix.getX());
				   fix.setWidth(Math.min(f.getX()+f.getWidth(), sec.getX()+sec.getWidth())-fix.getX());
				   if (i > f.getFirstSectionNo()) {
				     fix.setLeftBorder(false);
				     fix.setLeftOverHang(0);
				   }
				   if (i < f.getLastSectionNo()) {
					 fix.setRightBorder(false);  
					 fix.setRightOverHang(0);
				   }
				   fix.setFirstSectionNo(i);
				   fix.setLastSectionNo(i);
				   fix.setSection(sec);
				   fixs.add(fix);
			   }
			   fixtures.remove(fix);
		   }
		   if (f.getFirstSectionNo()!=-1) {
		     f.setSection(sections.get(f.getFirstSectionNo()));
		   }
	   }
	   fixtures.addAll(fixs);
	   // Заполнение нумерации сверху вниз
	   // Сортируем полки
	   //    - по номеру сегмента ASC
	   //    - по Y DESC
	   fixtures.sort(Comparator.comparingInt(FixtureData::getFirstSectionNo)
				               .thenComparingInt(FixtureData::getY).reversed()
		            );
	   for(FixtureData f:fixtures) {
		  if (f.getFirstSectionNo() != fixNo) {
			  fixNum = 1;
			  spaceAvailable = 2000 - f.getY() - f.getHeight();
		  } else {
			  fixNum++;
			  spaceAvailable = priorY - f.getY() - f.getHeight();
		  };
		  if (f.getSpaceAvailable()==-1) {
			  f.setSpaceAvailable(spaceAvailable);
		  }
		  priorY = f.getY();
		  if (f.getUpFixNo()==-1) {
		      f.setUpFixNo(fixNum);
		  }
		  //f.setRelativeUpFixNo(fixNum);
		  fixNo = f.getFirstSectionNo();
	   }
		   
	   fixNo = -1;
	   fixNum = 1;
	   // Заполнение нумерации снизу вверх
	   fixtures.sort(Comparator.comparingInt(FixtureData::getFirstSectionNo)
			    		       .thenComparingInt(FixtureData::getY)
	   );
	   for (FixtureData f:fixtures) {
		  if (f.getFirstSectionNo() != fixNo) {
		      fixNum = 1;
		  } else {
			  fixNum++;
		  }
		  if (f.getDownFixNo()==-1) {
		      f.setDownFixNo(fixNum);
		  }
		  //f.setRelativeDownFixNo(fixNum);
		  fixNo = f.getFirstSectionNo();
	  }
    }
	
	@Override
	public IEquipment generateEquipment(int planogram_id) {
	    List<IFixture> eq = new ArrayList<>();
		// Обрабатываем секции
		List<SectionData> sections = new EquipmentService().getAllSection(planogram_id);
		// 
		Map<Integer,SectionData> mapSections = initSections(sections);
		// Обрабатываем полки
		List<FixtureData> fixtures = new EquipmentService().getAllFixture(planogram_id);
		initFixtures(fixtures, mapSections);
		for(FixtureData f: fixtures) {
			eq.add(new BaseFixture(f));
		}
		LOG.debug(eq.size());
		//BaseEquipment eq = new BaseEquipment();
		return new BaseEquipment(eq);
	}

}
