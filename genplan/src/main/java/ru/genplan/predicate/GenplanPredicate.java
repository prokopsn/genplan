package ru.genplan.predicate;

import java.util.List;

public class GenplanPredicate {
	
	private Integer id;
	private String name;
	private Integer type;
	private List<GenplanLogicalOperation> logicalOperations;
	
	public List<GenplanLogicalOperation> getGenplanLogicalOperation() {
		return logicalOperations;
	}
	public void setGenplanLogicalOperation(List<GenplanLogicalOperation> logicalOperations) {
		this.logicalOperations = logicalOperations;
	}
	public GenplanPredicate(Integer id, String name, Integer type) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
	}
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Integer getType() {
		return type;
	}

}
