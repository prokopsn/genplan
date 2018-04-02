package ru.genplan.predicate;

import java.util.Date;

public class PredicateValue {
	
	private Integer intValue;
	private Float floatValue;
	private String stringValue;
	private Date dateValue;
	private Integer predicateValueId;
	
	public Integer getIntValue() {
		return intValue;
	}
	public Float getFloatValue() {
		return floatValue;
	}
	public String getStringValue() {
		return stringValue;
	}
	public Integer getPredicateValueId() {
		return predicateValueId;
	}
	public Date getDateValue() {
		return dateValue;
	}
	
	public PredicateValue(Integer predicateValueId, Integer intValue, Float floatValue, String stringValue, Date dateValue) {
		super();
		this.predicateValueId = predicateValueId;
		this.intValue = intValue;
		this.floatValue = floatValue;
		this.stringValue = stringValue;
		this.dateValue = dateValue;
	}
}
