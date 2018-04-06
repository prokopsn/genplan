package ru.genplan.predicate;

import java.util.List;
import java.util.function.Predicate;

public interface PredicateFunction<T> {
	Predicate<T> apply(PredicateOperation oper, List<PredicateValue> values, Boolean negate);
}
