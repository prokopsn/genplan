package ru.genplan.mybatis.mappers;

import java.util.List;


public interface EquipmentMapper {
	List<FixtureData> getAllFixture(int planogramId);
	List<SectionData> getAllSection(int planogramId);
	PlanogramData getPlanogram(int planogramId);
}
