package ru.genplan.mybatis;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ru.genplan.equipment.EquipmentPredicates;
import ru.genplan.equipment.IFixture;

public class TestEquipmentPredicates {
	@Test
	@DisplayName("Координаты")
	public void testOne() {
		Predicate<IFixture> pf;
		pf = EquipmentPredicates.predicateOr(Arrays.asList(EquipmentPredicates.checkDownFixtueNum(1)
				                                          ,EquipmentPredicates.checkUpFixtueNum(1)));
	}
}
