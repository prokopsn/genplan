package ru.genplan.mybatis.predicate;

import java.util.List;

import ru.genplan.predicate.GenplanPredicate;

public interface PredicateMapper {
		List<GenplanPredicate> getAllPredicates(int planogramId);
}
