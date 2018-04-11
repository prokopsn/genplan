package ru.genplan.predicate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ru.genplan.mybatis.predicate.PredicateService;

public class PredicateFactory<T> {
	private static final Logger LOG = LogManager.getLogger(PredicateFactory.class);
	Map<String, Predicate<T>> predicates = new HashMap<>();
	private List<GenplanPredicate> genplanPredicates;
	@SuppressWarnings("unchecked")
	private Predicate<T> generatePredicate(GenplanPredicate predicate, Integer predicateId, PredicateLogicalOperation op) {
		LOG.debug("generatePredicate: ");
		Predicate<T> result = null;
		Predicate<T> temp = null;
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
				temp = (Predicate<T>)EquipmentPredicates
						 .getPredicate(pm.getPredicateCode(), pm.getPredicateOperation(),pm.getPredicateValues(), pm.getNegate());
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
	
	private Predicate<T> generatePredicate(GenplanPredicate predicate) {
		return generatePredicate(predicate, null, PredicateLogicalOperation.OR);		
	}
	public PredicateFactory(int planogramId) {
		LOG.debug("PredicateFactory constructor entered planorgram_id={}",planogramId);
		Predicate<T> prd;
		genplanPredicates = new PredicateService().getAllPredicates(planogramId);
		LOG.debug("  defined {} predicates",genplanPredicates.size());
		for (GenplanPredicate p:genplanPredicates) {
			LOG.debug("Predicate "+p);
			prd = generatePredicate(p);
			if (prd!=null) {
				predicates.put(p.getName(), prd);
			}
		}
	}
}
