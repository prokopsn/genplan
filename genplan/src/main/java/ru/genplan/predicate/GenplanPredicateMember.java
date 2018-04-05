package ru.genplan.predicate;

import java.util.List;

public class GenplanPredicateMember {
	public void setPredicateValues(List<PredicateValue> predicateValues) {
		this.predicateValues = predicateValues;
	}
	private Integer id;
	private String predicateCode;
	private String predicateOperation;
	private Boolean negate;
	private List<PredicateValue> predicateValues;
	
	public GenplanPredicateMember(Integer id, String predicateCode, String predicateOperation, String negate) {
		super();
		this.id = id;
		this.predicateCode = predicateCode;
		this.predicateOperation = predicateOperation;
		this.negate = negate=="Y";
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
	public Boolean getNegate() {
		return negate;
	}
}
