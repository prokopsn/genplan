package ru.genplan;

import java.util.List;
import java.util.Map;

import ru.genplan.mybatis.mappers.FixtureData;
import ru.genplan.mybatis.mappers.SectionData;

public class Planogram {
	private Map<Integer,SectionData> sections;
	private List<FixtureData> fixtures;
}
