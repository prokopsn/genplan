package ru.genplan.equipment;

import java.util.List;
import java.util.Map;
/**
 * Интерфейс для реализации стратегии размещения блока, или блоков на 
 * оборудовании
 * @author Sergey Prokopchik
 *
 */
public interface IGenerateStrategy {
	/**
	 * Передает список полок оборудования для использования в алгоритме
	 * @param fixtures - список полок оборудования
	 */
	public void setFixtures(List<IFixture> fixtures);
	/**
	 * Генерирует оборудование и возвращает его в виде Map<String, IEquipment>
	 * где ключ - наименование (код?) блока для которого сгенерилось оборудование
	 * @return
	 */
	public Map<String, IEquipment> generate();
}
