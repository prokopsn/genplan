package ru.genplan.predicate;

import java.util.List;
/**
 * Предикат
 * @author Sergey Prokopchik
 *
 */
public class GenplanPredicate {
	/*
	 * Идентификатор
	 */
	private Integer id;
	/*
	 * Наименование по нему будет идти привязка
	 */
	private String name;
	/*
	 * Тип
	 */
	private Integer type;
	/*
	 * Список логических операций. 
	 */
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
	@Override
	public String toString() {
		return "id="+id
			  +", name="+name
			  +", type="+type
			  +"   logicalOperations="+logicalOperations;
	}

}
