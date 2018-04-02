package ru.genplan.equipment;

import java.util.List;
import java.util.function.Predicate;

public class EquipmentPredicates {
	public static Predicate<IFixture> checkSectionName(String name) {
		return p -> {
			if (p.getSectionName()==null) return false;
			return p.getSectionName() == name;
		};
	}
	
	public static Predicate<IFixture> checkDownFixtueNum(int num) {
		return p -> {
			return p.getAbsoluteDownFixNo() == num;
		};
	}
	
	public static Predicate<IFixture> checkUpFixtueNum(int num) {
		return p -> {
			return p.getAbsoluteUpFixNo() == num;
		};
	}
	
	public static Predicate<IFixture> checkX(int x) {
		return p -> {
			return p.getX() > x;
		};
	}
	
	public static Predicate<IFixture> predicateAnd(List<Predicate<? super IFixture>> predList) {
		Predicate<IFixture> result = p -> true;
		for(Predicate<? super IFixture> p:predList) {
			result = result.and(p);
		}
		return result;
	}
	
	public static Predicate<IFixture> predicateOr(List<Predicate<? super IFixture>> predList) {
		Predicate<IFixture> result = p -> false;
		for(Predicate<? super IFixture> p:predList) {
			result = result.or(p);
		}
		return result;
	}
}
