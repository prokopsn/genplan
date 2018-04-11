package ru.genplan.equipment;

import java.util.Map;
/**
 * ��������� ��� ���������� ��������� ���������� �����, ��� ������ �� 
 * ������������
 * @author Sergey Prokopchik
 *
 */
public interface IGenerateStrategy {
	/**
	 * ���������� ������������ � ���������� ��� � ���� Map<String, IEquipment>
	 * ��� ���� - ������������ (���?) ����� ��� �������� ����������� ������������
	 * @return
	 */
	public Map<String, IEquipment> generate();
}
