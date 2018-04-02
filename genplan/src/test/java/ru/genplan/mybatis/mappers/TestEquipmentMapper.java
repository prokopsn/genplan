package ru.genplan.mybatis.mappers;

import java.util.List;


public interface TestEquipmentMapper {
	List<FixtureData> getAllFixture(int planogramId);
	List<SectionData> getAllSection(int planogramId);
	PlanogramData getPlanogram(int planogramId);
	FixtureData getFixture(int fixtureId);
	SectionData getSection(int sectionId);
}
