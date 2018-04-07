package ru.genplan.mybatis.mappers;

import java.util.List;

import ru.genplan.equipment.data.FixtureData;
import ru.genplan.equipment.data.PlanogramData;
import ru.genplan.equipment.data.SectionData;


public interface TestEquipmentMapper {
	List<FixtureData> getAllFixture(int planogramId);
	List<SectionData> getAllSection(int planogramId);
	PlanogramData getPlanogram(int planogramId);
	FixtureData getFixture(int fixtureId);
	SectionData getSection(int sectionId);
}
