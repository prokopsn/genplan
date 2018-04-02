package ru.genplan.predicate;

public class PredicateHeader {
	private Integer id;
	private Integer parentId;
	private Integer level;
	
	public PredicateHeader(Integer id, Integer parentId, Integer level) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.level = level;
	}
	
	public Integer getId() {
		return id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public Integer getLevel() {
		return level;
	}
	
}
