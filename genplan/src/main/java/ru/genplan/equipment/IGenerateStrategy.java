package ru.genplan.equipment;

import java.util.Map;
/**
 * Интерфейс для реализации стратегии размещения блока, или блоков на 
 * оборудовании
 * @author Sergey Prokopchik
 *
 */
public interface IGenerateStrategy {
	/**
	 * Генерирует оборудование и возвращает его в виде Map<String, IEquipment>
	 * где ключ - наименование (код?) блока для которого сгенерилось оборудование
	 * @return
	 */
	public Map<String, IEquipment> generate();
}
