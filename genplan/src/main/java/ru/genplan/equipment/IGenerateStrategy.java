package ru.genplan.equipment;

import java.util.List;
import java.util.Map;
/**
 * ��������� ��� ���������� ��������� ���������� �����, ��� ������ �� 
 * ������������
 * @author Sergey Prokopchik
 *
 */
public interface IGenerateStrategy {
	/**
	 * �������� ������ ����� ������������ ��� ������������� � ���������
	 * @param fixtures - ������ ����� ������������
	 */
	public void setFixtures(List<IFixture> fixtures);
	/**
	 * ���������� ������������ � ���������� ��� � ���� Map<String, IEquipment>
	 * ��� ���� - ������������ (���?) ����� ��� �������� ����������� ������������
	 * @return
	 */
	public Map<String, IEquipment> generate();
}
