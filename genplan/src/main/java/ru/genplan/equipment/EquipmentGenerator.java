package ru.genplan.equipment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.stream.Collectors.*;
import static ru.genplan.equipment.EquipmentDirection.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class EquipmentGenerator {
	
	private static final Logger LOG = LogManager.getLogger(BaseFixture.class);

	/*
	 * Набор полок 
	 */
	List<IFixture> fixtures;
	/*
	 * Свободное место в каждом сегменте, в зависимости от направления
	 */
	Map<Integer, List<IFixture>> grouppedFix;
	/*
	 * Предикат для отбора полок блока
	 */
	Predicate<IFixture> predicate;
	private final int x;
	private final int y;
	private final int width;
	private final int height;
	private final int[] needWidth;
	private final EquipmentDirection direction;
	private List<Integer[]> coordinates;
	/*
	 * 
	 */
	public EquipmentGenerator(Predicate<IFixture> predicate, int x, int y, int width, int height) {
		this.predicate = predicate;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.needWidth = new int[] {};
		this.direction = null;
		this.coordinates = new ArrayList<>();
	}
		
	public EquipmentGenerator(Predicate<IFixture> predicate, EquipmentDirection direction, int[] needWidth) {
		this.predicate = predicate;
		this.needWidth = needWidth;
		this.direction = direction;
		this.x = -1;
		this.y = -1;
		this.width = -1;
		this.height = -1;
		this.coordinates = new ArrayList<>();
	}
	
	private Function<? super IFixture,Integer> getGroupFunction() {
		Function<? super IFixture,Integer> f = null;
		switch(direction) {
			case LEFTBOTTOM:
			case LEFTTOP:
			case LEFT: f = IFixture::getX;					   
					   break;
			case RIGHTBOTTOM:
			case RIGHTTOP:
			case RIGHT:f = IFixture::getX;
					   break;
			case BOTTOM: f = IFixture::getY;
			             break;
			case TOP:  f = IFixture::getY;
					   break;
		};
		return f;
	}
	
	public void setFixtures(List<IFixture> fixtures) {
		if (this.predicate == null) { 
		   this.grouppedFix = fixtures.stream()
				                      .collect(groupingBy(getGroupFunction()));
		} else {
		   this.grouppedFix = fixtures.stream()
				                      .filter(this.predicate)
				                      .collect(groupingBy(getGroupFunction()));
		}
	}
		
	private void calculateXCoordinate(EquipmentDirection dir) {
		Iterator<Integer> keys = grouppedFix.keySet().iterator();
		// Набор полок по текущей координате X
		List<IFixture>    curFixs = null;
		// Необходимое место для текущего блока
		int curNeedWidth = 0;
		// Необходимое место для текущей полки
		int fixNeedWidth = 0;
		// Доступное (выделенное) место на полке
		int availableWidth = 0;
		// текущий x
		int curX = 0;
		// максимальный x на полке
		int fixX1 = 0;
		Integer curKey = null;
		curNeedWidth = needWidth[0];
		for(int i = 0;i<needWidth.length;) {
            if (curX >= fixX1) {
            	if (!keys.hasNext()) {
    				throw new RuntimeException("TO DO write error message");
    			}	
            	curKey = keys.next();
    			curX = curKey;
    			curFixs = grouppedFix.get(curKey);
			}
			
			fixNeedWidth = Math.round((float)curNeedWidth/curFixs.size());
			for(IFixture f:curFixs) {
				availableWidth = f.getAvailableWidthBetween(curX, curX + fixNeedWidth);
				curNeedWidth -= availableWidth;
				fixX1 = Math.max(fixX1, f.getX()+f.getWidth());
			}
			curX += fixNeedWidth;
			if (curNeedWidth == 0) {
				i++;
				curNeedWidth = needWidth[i];
			}
		}
	}
	
	/*
	 * 
	 */
	protected IEquipment generateByXY() {
		List<? extends IFixture> temp_fixs = null;
		List<IFixture> fixs = null;
		for(IFixture fix:fixtures) {
			temp_fixs = fix.generate(x, y, width, height);
			if (temp_fixs!= null) {
			  if (fixs==null) {
				  fixs = new ArrayList<IFixture>();
			  }
			  fixs.addAll(temp_fixs);
			}
		}
		return new BaseEquipment(fixs);
	}
	
	
}
