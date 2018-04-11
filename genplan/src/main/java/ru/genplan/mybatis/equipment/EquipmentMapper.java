package ru.genplan.mybatis.equipment;

import java.util.List;

import ru.genplan.equipment.data.FixtureData;
import ru.genplan.equipment.data.PlanogramData;
import ru.genplan.equipment.data.SectionData;


public interface EquipmentMapper {
	List<FixtureData> getAllFixture(int planogramId);
	List<SectionData> getAllSection(int planogramId);
	PlanogramData getPlanogram(int planogramId);
}
