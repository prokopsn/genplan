package ru.genplan.predicate;

import java.util.HashMap;
/**
 * Допустимые операции для сравнений значений предикатов
 * @author Sergey Prokopchik
 *
 */
public enum PredicateOperation {
	EQUAL("="),
	LESS("<"),
	GREATER(">"),
	GREATER_OR_EQUAL(">="),
	LESS_OR_EQUAL("<="),
	NOT_EQUAL("!="),
	IN("IN"),
	NOT_IN("NOT IN"),
	BETWEEN("BETWEEN"),
	NULL("NULL"),
	NOT_NULL("NOT NULL");
	private String operation;
    final static HashMap<String, PredicateOperation> genplanOperations = new HashMap<String, PredicateOperation>();

    static
    {
        for(PredicateOperation op : PredicateOperation.values())
        {
        	genplanOperations.put(op.getOperation(), op);
        }
    }
	
	public String getOperation() {
		return operation;
	}

	private PredicateOperation(String operation) {
		this.operation = operation;
	}
	
	public static PredicateOperation get(String operation) 
    {
        return genplanOperations.get(operation);
    }

	
	
}
