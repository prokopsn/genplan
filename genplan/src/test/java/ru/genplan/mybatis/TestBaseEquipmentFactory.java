package ru.genplan.mybatis;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ru.genplan.equipment.EquipmentPredicates;
import ru.genplan.equipment.IEquipment;
import ru.genplan.equipment.IFixture;
import ru.genplan.mybatis.mappers.TestEquipmentService;

@DisplayName("Test BaseFixture & BaseEquipmentFactory")
public class TestBaseEquipmentFactory {
	private static IEquipment baseEq; 
	@BeforeAll
	public static void getFixtureData() {
		BaseEquipmentFactory factory = new BaseEquipmentFactory();
		baseEq = factory.generateEquipment(1239720);
	}
	
	@Test
	@DisplayName("������� � ����������")
	public void testSize() {
		assertAll("����������",
			    ()-> assertEquals(baseEq.getX(),0,"���������� X"),
			    ()-> assertEquals(baseEq.getY(),0,"���������� Y"),
			    ()-> assertEquals(baseEq.getWidth(),1165,"������"),
			    ()-> assertEquals(baseEq.getHeight(),182,"������")
		     );	
	}
	
	@Test
	@DisplayName("���-�� ����� � ������")
	public void testCount() {
		assertAll("���-�� ����� � ������",
			    ()-> assertEquals(baseEq.getFixtureCount(),70,"���-�� �����"),
			    ()-> assertEquals(baseEq.getSectionCount(),10,"���-�� ������")
		     );	
	}
	
	@Test
	@DisplayName("��������� ������ ������������ �� ����������")
	public void testGeneratePredicate() {
		Predicate<IFixture> pf;
		pf = EquipmentPredicates.predicateOr(Arrays.asList(
				EquipmentPredicates.predicateAnd(Arrays.asList(EquipmentPredicates.checkDownFixtueNum(2),
						                                       EquipmentPredicates.checkX(800)))
			   ,EquipmentPredicates.checkUpFixtueNum(1)));
		baseEq.generate(pf,0,0,1165,182);
	}
}
