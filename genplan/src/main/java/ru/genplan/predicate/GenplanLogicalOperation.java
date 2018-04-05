package ru.genplan.predicate;

import java.util.List;

public class GenplanLogicalOperation {
	private Integer id;
	private Integer parentId;
	private String logicalOperationCode;
	private Integer logicalOperationlevel;
	private String logicalOperationNegate;
	private List<GenplanPredicateMember> predicateMember;
	public GenplanLogicalOperation(Integer id, Integer parentId, String logicalOperationCode,
			Integer logicalOperationlevel, String logicalOperationNegate) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.logicalOperationCode = logicalOperationCode;
		this.logicalOperationlevel = logicalOperationlevel;
		this.logicalOperationNegate = logicalOperationNegate;
	}
	public List<GenplanPredicateMember> getPredicateMember() {
		return predicateMember;
	}
	public void setPredicateMember(List<GenplanPredicateMember> predicateMember) {
		this.predicateMember = predicateMember;
	}
	public Integer getId() {
		return id;
	}
	public Integer getParentId() {
		return parentId;
	}
	public String getLogicalOperationCode() {
		return logicalOperationCode;
	}
	public Integer getLogicalOperationlevel() {
		return logicalOperationlevel;
	}
	public String getLogicalOperationNegate() {
		return logicalOperationNegate;
	}
}
