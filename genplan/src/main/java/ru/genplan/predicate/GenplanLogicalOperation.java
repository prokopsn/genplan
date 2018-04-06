package ru.genplan.predicate;

import java.util.List;

public class GenplanLogicalOperation {
	public PredicateLogicalOperation getLogicalOperation() {
		return logicalOperation;
	}
	/*
	 * �������������
	 */
	private Integer id;
	/*
	 * ������ �� ��������
	 */
	private Integer parentId;
	/*
	 * ��� ���������� �������� AND ��� OR
	 */
	private String logicalOperationCode;
	/*
	 * ������������� ���������� �������� � ���� enum
	 */
	private PredicateLogicalOperation logicalOperation;
	/*
	 * ������� (� ������) ���������� ��������
	 */
	private Integer logicalOperationlevel;
	/*
	 * ������� ���������
	 */
	private Boolean logicalOperationNegate;
	private List<GenplanPredicateMember> predicateMember;
	public GenplanLogicalOperation(Integer id
			                     , Integer parentId
			                     , String logicalOperationCode
			                     , Integer logicalOperationlevel
			                     , String logicalOperationNegate) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.logicalOperationCode = logicalOperationCode;
		this.logicalOperationlevel = logicalOperationlevel;
		this.logicalOperationNegate = logicalOperationNegate=="Y";
		this.logicalOperation = logicalOperationCode=="AND"?PredicateLogicalOperation.AND:PredicateLogicalOperation.OR;
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
	public Boolean getLogicalOperationNegate() {
		return logicalOperationNegate;
	}
	
	@Override
	public String toString() {
		return "id="+id
			  +", logicalOperation="+logicalOperation
			  +", logicalOperationCode="+logicalOperationCode
			  +", logicalOperationLevel="+logicalOperationlevel
			  +", logicalOperationNegate="+logicalOperationNegate
			  +System.lineSeparator()
			  +"       predicateMember="+predicateMember;
	}
}
