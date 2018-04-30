package ru.genplan.equipment;

import java.util.List;
import java.util.function.Predicate;

public interface IEquipment {
	
   public int getAvailableWidth();
	
	/**
	 * ѕолучение величины свободного места слева от X
	 * ≈сли x = -1 то слева от начала полки
	 * @param x
	 * @return
	 */
	public int getAvailableWidthLeft(int x);
	
	/**
	 * ѕолучение величины свободного места справа от X
	 * ≈сли x = -1 то справа от начала полки
	 * @param x
	 * @return
	 */
	public int getAvailableWidthRight(int x);
	
	public int getAvailableWidthBetween(int x, int x1);
	
	public IEquipment generate(int x, int y, int width, int height);
	public IEquipment generate(Predicate<IFixture> pred, int x, int y, int width, int height);
	public IEquipment generate(Predicate<IFixture> pred);
	
	public void remove();
	
	public int getX();
	
	public int getY();

	public int getWidth();

	public int getHeight();
	
	public int getFixtureCount();
	
	public int getSectionCount();
	public List<IFixture> getFixtures();

}
