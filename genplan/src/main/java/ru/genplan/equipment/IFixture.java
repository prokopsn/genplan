package ru.genplan.equipment;

import java.util.List;
import java.util.Set;

public interface IFixture {
	/**
	 * Идентификатор полки в БД
	 * @return
	 */
	public int getId();

	/**
	 * Наименование полки
	 * @return
	 */
	public String getName();
	public String getSectionName();
	
	/**
	 * Идентификатор планограммы
	 * @return
	 */
	public int getPlanogramId();
	
	/**
	 * Координата полки X
	 * @return
	 */
	public int getX();
	/**
	 * Координата полки Y
	 * @return
	 */
	public int getY();
	/**
	 * Координата полки Z
	 * @return
	 */
	public int getZ();
	/**
	 * Ширина полки
	 * @return
	 */
	public int getWidth();
	
	/**
	 * Глубина полки
	 * @return
	 */
	public int getDepth();
	
	/**
	 * Максимальное кол-во фейсингов для выкладки по вертикали
	 * @return
	 */
	public int getMerchymax();
		
	/**
	 * Доступное длч выкладки в высоту место
	 * @return
	 */
	public int getSpaceAvailable();
	
	/**
	 * Номера полок в рамках секции сверху или снизу
	 * @return
	 */
	public int getAbsoluteUpFixNo();
	public int getAbsoluteDownFixNo();
	
	/**
	 * Теги полки, разделитель ";"
	 * @return
	 */
	public Set<String> getFixtureTags();
	public Set<String> getSectionTags();
	
	public int getSectionNo();
	
	/**
	 * Тип полки
	 * @return
	 */
	public int getFixtureType();
	
	
	/**
	 * Возможная величина свешивания слева, справа, спереди и сзади
	 * @return
	 */
	public int getLeftOverHang();
	public int getRightOverHang();
	public int getFrontOverHang();
	public int getBackOverHang();
		
	/**
	 * Признак границы слева и справа
	 * @return
	 */
	public boolean isLeftBorder();
	public boolean isRightBorder();
		
	
	/**
	 * Величина расстояния между крючками для перфорации
	 * @return
	 */
	public int getXspacing();
	public int getYspacing();
	
	
	public int getAvailableWidth();
	
	/**
	 * Получение величины свободного места на полке слева от X
	 * Если x = -1 то слева от начала полки
	 * @param x
	 * @return
	 */
	public int getAvailableWidthLeft(int x);
	
	/**
	 * Получение величины свободного места на полке справа от X
	 * Если x = -1 то справа от начала полки
	 * @param x
	 * @return
	 */
	public int getAvailableWidthRight(int x);
	/**
	 * Получение величины свободного места на полке между x и x1
	 * @param x 
	 * @param x1
	 * @return
	 */
	public int getAvailableWidthBetween(int x, int x1);
	
	/**
	 * 
	 */
	public List<? extends IFixture> generate(int x, int y, int width, int height);
	public void remove();
	public void remove(IFixture fixture);
	//public void removeAll(List<IFixture> fixtures);
	public void setParent(IFixture parent);
	public IFixture getParent();

	public int getHeight();
	
	public int getSectionFixtureCount();
	
	public int getGroupLeftEqualNo();

	public int getGroupRightEqualNo();
	
	public int getGroupLeftNo();
	
	public int getGroupRightNo();
	
	public int getLeftNo();
	
	public int getRightNo();

}
