package ru.genplan.equipment;

import java.util.List;
import java.util.Set;

public interface IFixture {
	/**
	 * ������������� ����� � ��
	 * @return
	 */
	public int getId();

	/**
	 * ������������ �����
	 * @return
	 */
	public String getName();
	public String getSectionName();
	
	/**
	 * ������������� �����������
	 * @return
	 */
	public int getPlanogramId();
	
	/**
	 * ���������� ����� X
	 * @return
	 */
	public int getX();
	/**
	 * ���������� ����� Y
	 * @return
	 */
	public int getY();
	/**
	 * ���������� ����� Z
	 * @return
	 */
	public int getZ();
	/**
	 * ������ �����
	 * @return
	 */
	public int getWidth();
	
	/**
	 * ������� �����
	 * @return
	 */
	public int getDepth();
	
	/**
	 * ������������ ���-�� ��������� ��� �������� �� ���������
	 * @return
	 */
	public int getMerchymax();
		
	/**
	 * ��������� ��� �������� � ������ �����
	 * @return
	 */
	public int getSpaceAvailable();
	
	/**
	 * ������ ����� � ������ ������ ������ ��� �����
	 * @return
	 */
	public int getAbsoluteUpFixNo();
	public int getAbsoluteDownFixNo();
	
	/**
	 * ���� �����, ����������� ";"
	 * @return
	 */
	public Set<String> getFixtureTags();
	public Set<String> getSectionTags();
	
	public int getSectionNo();
	
	/**
	 * ��� �����
	 * @return
	 */
	public int getFixtureType();
	
	
	/**
	 * ��������� �������� ���������� �����, ������, ������� � �����
	 * @return
	 */
	public int getLeftOverHang();
	public int getRightOverHang();
	public int getFrontOverHang();
	public int getBackOverHang();
		
	/**
	 * ������� ������� ����� � ������
	 * @return
	 */
	public boolean isLeftBorder();
	public boolean isRightBorder();
		
	
	/**
	 * �������� ���������� ����� �������� ��� ����������
	 * @return
	 */
	public int getXspacing();
	public int getYspacing();
	
	
	public int getAvailableWidth();
	
	/**
	 * ��������� �������� ���������� ����� �� ����� ����� �� X
	 * ���� x = -1 �� ����� �� ������ �����
	 * @param x
	 * @return
	 */
	public int getAvailableWidthLeft(int x);
	
	/**
	 * ��������� �������� ���������� ����� �� ����� ������ �� X
	 * ���� x = -1 �� ������ �� ������ �����
	 * @param x
	 * @return
	 */
	public int getAvailableWidthRight(int x);
	/**
	 * ��������� �������� ���������� ����� �� ����� ����� x � x1
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
