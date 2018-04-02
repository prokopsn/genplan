package ru.genplan.mybatis.mappers;

import java.util.List;

public interface PredicateMapper {
		List<FixtureData> getAllFixture(int planogramId);
		List<SectionData> getAllSection(int planogramId);
}
