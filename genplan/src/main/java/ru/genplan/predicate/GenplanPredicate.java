package ru.genplan.predicate;

import java.util.List;

public class GenplanPredicate {
	private Integer id;
	private String predicateCode;
	private String predicateOperation;
	private List<PredicateValue> predicateValues;
	
	public GenplanPredicate(Integer id, String predicateCode, String predicateOperation,
			List<PredicateValue> predicateValues) {
		super();
		this.id = id;
		this.predicateCode = predicateCode;
		this.predicateOperation = predicateOperation;
		this.predicateValues = predicateValues;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getPredicateCode() {
		return predicateCode;
	}
	public String getPredicateOperation() {
		return predicateOperation;
	}
	public List<PredicateValue> getPredicateValues() {
		return predicateValues;
	}
}
