package ru.genplan.mybatis.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test FixtureData")
public class TestFixtureData {
	private static List<FixtureData> fixs;
	private static FixtureData fix;
	
	@BeforeAll
	public static void getSectionData() {
		fixs = new TestEquipmentService().getAllFixture(1239720);
		if (fixs.size()>0) {
			fixs.sort(Comparator.comparingInt(FixtureData::getX));
			List<FixtureData> fd = fixs
		                             .stream()
 		                             .filter((p)->p.getId()==119044199)
		                             .collect(Collectors.toList());
		    fix = fd.get(0);
		}
	}
	
	@Test
	@DisplayName("Кол-во полок")
	public void testCount() {
		assertEquals(fixs.size(), 70);
	}
	
	@Test
	@DisplayName("Номера секций")
	public void testSectionNo() {
		assertEquals(fix.getFirstSectionNo(), 1);
		assertEquals(fix.getLastSectionNo(), 1);
	}
	
	@Test
	@DisplayName("Теги полки")
	public void testSectionTag() {
		assertNull(fix.getFixtureTag());
		assertNull(fix.getFixTag());
		fix.setFixtureTags("THIS;IS SECTION;Tag");
		assertEquals(fix.getFixtureTag(), "THIS;IS SECTION;Tag");
		assertEquals(fix.getFixTag().contains("THIS"),true);
		assertEquals(fix.getFixTag().contains("TAG"),false);
	}
	
	@Test
	@DisplayName("Координаты полки")
	public void testXYZ() {
		assertEquals(fix.getX(), 0);
		assertEquals(fix.getY(), 68);
		assertEquals(fix.getZ(), 0);
		assertEquals(fix.getWidth(), 125);
		assertEquals(fix.getHeight(), 4);
		assertEquals(fix.getDepth(), 50);
	}
}
