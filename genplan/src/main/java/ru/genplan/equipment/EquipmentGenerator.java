package ru.genplan.equipment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class EquipmentGenerator {
	private static final Logger LOG = LogManager.getLogger(BaseFixture.class);
	List<IFixture> fixtures;
	/*
	 * Свободное место в каждом сегменте, в зависимости от направления
	 */
	Map<Integer,Integer> segmentFreeSpace;
	Map<Integer,Integer> rowFreeSpace;
	
	Predicate<IFixture> predicate;
	int x;
	int y;
	int width;
	int height;
	int[] needWidth;
	EquipmentDirection direction;
	/*
	 * 
	 */
	public EquipmentGenerator(Predicate<IFixture> predicate, int x, int y, int width, int height) {
		this.predicate = predicate;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public EquipmentGenerator(Predicate<IFixture> predicate, EquipmentDirection direction, int needWidth) {
		this.predicate = predicate;
		this.needWidth = new int[] {needWidth};
		this.direction = direction;
	}
	
	public EquipmentGenerator(Predicate<IFixture> predicate, EquipmentDirection direction, int[] needWidth) {
		this.predicate = predicate;
		this.needWidth = needWidth;
		this.direction = direction;
	}
	
	public void setFixtures(List<IFixture> fixtures) {
		if (this.predicate == null) { 
		   this.fixtures = fixtures;
		} else {
		   this.fixtures = fixtures.stream().filter(this.predicate).collect(Collectors.toList());
		}
		fillFreeSpace();
	}
	/*
	 * Заполнение структур segmentFreeSpace и rowFreeSpace
	 */
	private void fillFreeSpace() {
		//segmentFreeSpace = fixtures.stream().so
	}
	
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
