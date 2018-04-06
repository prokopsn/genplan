package ru.genplan.predicate;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import ru.genplan.equipment.IFixture;
import ru.genplan.mybatis.mappers.PredicateService;

public class PredicateFactory { 
	Map<String, Predicate<IFixture>> predicates;
	private List<GenplanPredicate> genplanPredicates;
	private Predicate<IFixture> generatePredicate(GenplanPredicate predicate, Integer predicateId, PredicateLogicalOperation op) {
		Predicate<IFixture> result = null;
		Predicate<IFixture> temp = null;
		for (GenplanLogicalOperation lo:predicate.getGenplanLogicalOperation())	{
			if (lo.getParentId() == predicateId) {
				temp = generatePredicate(predicate, lo.getId(), lo.getLogicalOperation());
				if (temp!= null) {
					if (result==null) {
						result = temp;
					} else {
						if (op==PredicateLogicalOperation.AND) {
							result = result.and(temp);
						} else {
							result = result.or(temp);
						}
					}
				}
			}
			for (GenplanPredicateMember pm: lo.getPredicateMember()) {
				temp = EquipmentPredicates
						 .getPredicate(pm.getPredicateCode())
						 .apply(pm.getPredicateOperation(),pm.getPredicateValues(), pm.getNegate());
				if (temp!= null) {
					if (result==null) {
						result = temp;
					} else {
						if (op==PredicateLogicalOperation.AND) {
							result = result.and(temp);
						} else {
							result = result.or(temp);
						}
					}
				}
			}
			if (result != null && lo.getLogicalOperationNegate()) {
				result = result.negate();
			}
		}
		return result;
	}
	
	private Predicate<IFixture> generatePredicate(GenplanPredicate predicate) {
		return generatePredicate(predicate, null, PredicateLogicalOperation.OR);		
	}
	public PredicateFactory(int planogramId) {
		Predicate<IFixture> prd;
		genplanPredicates = new PredicateService().getAllPredicates(planogramId);
		for (GenplanPredicate p:genplanPredicates) {
			prd = generatePredicate(p);
			if (prd!=null) {
				predicates.put(p.getName(), prd);
			}
		}
	}
}
