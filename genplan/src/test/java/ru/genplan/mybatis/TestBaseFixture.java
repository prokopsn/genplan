package ru.genplan.mybatis;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ru.genplan.equipment.BaseFixture;
import ru.genplan.equipment.IFixture;
import ru.genplan.equipment.data.FixtureData;
import ru.genplan.equipment.data.SectionData;
import ru.genplan.mybatis.mappers.TestEquipmentService;

@DisplayName("Test BaseFixture")
public class TestBaseFixture {
	private static FixtureData fix;
	private static SectionData sec;
	private static BaseFixture basefix;
	
	@BeforeAll
	public static void getFixtureData() {
		fix = new TestEquipmentService().getFixture(119044207);
		sec = new TestEquipmentService().getSection(3376617);
		fix.setSection(sec);
		basefix = new BaseFixture(fix);
	}
	
	@Test
	@DisplayName("Координаты")
	public void testX() {
		assertAll("Координаты",
				    ()-> assertEquals(basefix.getX(),fix.getX(),"Координата X"),
				    ()-> assertEquals(basefix.getWidth(),fix.getWidth(),"Ширина полки"),
				    ()-> assertEquals(basefix.getY(),fix.getY(),"Координата Y"),
				    ()-> assertEquals(basefix.getHeight(),fix.getHeight(),"Высота полки")
			     );	
	}
	
	@Test
	@DisplayName("Теги полки и секции")
	public void testTags() {
		fix.setFixtureTags("One;Two;Three");
	}
	
	@Test
	@DisplayName("Генерация полок + определение свободного места")
	public void testGenerate() {
		List<? extends IFixture> fixs = null;
		List<? extends IFixture> fixs1 = null;
		assertNull(fixs,"Генерируем для области не попадающей по Y, должно быть null");
		assertEquals(100,basefix.getAvailableWidth());
		assertEquals(1,basefix.getAvailableWidthLeft(126));
		assertEquals(0,basefix.getAvailableWidthLeft(124));
		assertEquals(0,basefix.getAvailableWidthRight(1240));
		fixs = basefix.generate(226, 0, 44, 40);
		
		assertNull(fixs,"Генерируем для области не попадающей по X, должно быть null");
		fixs = basefix.generate(146, 120, 44, 40);
		System.out.println("1 "+basefix.getAvailableWidth());
		assertEquals(1, fixs.size(),"Должна сгенерироваться одна полка");
		assertEquals(146, fixs.get(0).getX());
		assertEquals(44, fixs.get(0).getWidth());
		fixs1 = basefix.generate(135, 120, 80, 40);	
		System.out.println("2 "+basefix.getAvailableWidth());
		assertEquals(2, fixs1.size(),"Должно сгенерироваться две полки");
		assertEquals(135, fixs1.get(0).getX());
		assertEquals(11, fixs1.get(0).getWidth());
		assertEquals(190, fixs1.get(1).getX());
		assertEquals(25, fixs1.get(1).getWidth());
		
		List<? extends IFixture> fixs2 = null;
		fixs2 = basefix.generate(130, 120, 100, 40);
		System.out.println("3 "+basefix.getAvailableWidth());
		assertEquals(2, fixs2.size(),"Должно сгенерироваться две полки");
		assertEquals(130, fixs2.get(0).getX());
		assertEquals(5, fixs2.get(0).getWidth());
		assertEquals(215, fixs2.get(1).getX());
		assertEquals(10, fixs2.get(1).getWidth());
		
		fixs.get(0).remove();
		System.out.println("4 "+basefix.getAvailableWidth());
		fixs = basefix.generate(150, 120, 1, 40);
		assertEquals(1, fixs.size(),"Должна сгенерироваться одна полка");
		assertEquals(150, fixs.get(0).getX());
		assertEquals(1, fixs.get(0).getWidth());
		assertEquals(48,basefix.getAvailableWidth());
		assertEquals(48,basefix.getAvailableWidthRight(1));
		assertEquals(9,basefix.getAvailableWidthLeft(150));
		assertEquals(0,basefix.getAvailableWidthRight(1240));
	}
}
