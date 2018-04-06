package ru.genplan.equipment;

import java.util.List;
import java.util.function.Predicate;

import ru.genplan.predicate.PredicateOperation;
import ru.genplan.predicate.PredicateValue;

public class EquipmentPredicatesOld {
	public static Predicate<IFixture> checkSectionName(String name) {
		return p -> {
			if (p.getSectionName()==null) return false;
			return p.getSectionName() == name;
		};
	}
	
	public static Predicate<IFixture> checkDownFixtueNum(int num) {
		return p -> {
			return p.getAbsoluteDownFixNo() == num;
		};
	}
	
	public static Predicate<IFixture> checkUpFixtueNum(int num) {
		return p -> {
			return p.getAbsoluteUpFixNo() == num;
		};
	}
	
	public static Predicate<IFixture> checkX(int x) {
		return p -> {
			return p.getX() > x;
		};
	}
	
	public static Predicate<IFixture> predicateAnd(List<Predicate<? super IFixture>> predList) {
		Predicate<IFixture> result = p -> true;
		for(Predicate<? super IFixture> p:predList) {
			result = result.and(p);
		}
		return result;
	}
	
	public static Predicate<IFixture> predicateOr(List<Predicate<? super IFixture>> predList) {
		Predicate<IFixture> result = p -> false;
		for(Predicate<? super IFixture> p:predList) {
			result = result.or(p);
		}
		return result;
	}
	
	public static boolean checkString(String checkValue, PredicateOperation oper, List<PredicateValue> values) {
		if (checkValue==null) {
			if (oper == PredicateOperation.NULL) return true;
		      return false;
		}
		if (values==null) return false;
		switch (oper) {
		  case EQUAL: return checkValue == values.get(0).getStringValue();
		  case GREATER_OR_EQUAL: 
		  case BETWEEN:
		  case GREATER:
		  case LESS: throw new UnsupportedOperationException(
	                    String.format("Operation %s unsupported for data type [String]",
	                    		oper.getOperation())
	                 );
		  case LESS_OR_EQUAL: return false;
		  case NOT_EQUAL: return checkValue != values.get(0).getStringValue();
		  case NOT_IN: for(PredicateValue v:values) {
	  			          if (checkValue == v.getStringValue()) return false;
	    	           };
	    	           return true;
		  case IN:     for(PredicateValue v:values) {
			  			  if (checkValue == v.getStringValue()) return true;
			    	   };
			    	   return false;
		  case NOT_NULL: return true;
		  case NULL: return false;
		  default:	return false;
		}
	}
	
	public static boolean checkInteger(Integer checkValue, PredicateOperation oper, List<PredicateValue> values) {
		if (checkValue==null) {
			if (oper == PredicateOperation.NULL) return true;
		      return false;
		}
		if (values==null) return false;
		switch (oper) {
		  case EQUAL: return checkValue == values.get(0).getIntValue();
		  case GREATER_OR_EQUAL: return checkValue >= values.get(0).getIntValue();
		  case BETWEEN: return checkValue >= values.get(0).getIntValue() && checkValue <= values.get(0).getIntValue();
		  case GREATER: return checkValue > values.get(0).getIntValue();
		  case LESS: return checkValue < values.get(0).getIntValue();
		  case LESS_OR_EQUAL: return checkValue <= values.get(0).getIntValue();
		  case NOT_EQUAL: return checkValue != values.get(0).getIntValue();
		  case NOT_IN: for(PredicateValue v:values) {
	  			          if (checkValue == v.getIntValue()) return false;
	    	           };
	    	           return true;
		  case IN:     for(PredicateValue v:values) {
			  			  if (checkValue == v.getIntValue()) return true;
			    	   };
			    	   return false;
		  case NOT_NULL: return true;
		  case NULL: return false;
		  default:	return false;
		}
	}
	
	public static boolean checkFloat(Float checkValue, PredicateOperation oper, List<PredicateValue> values) {
		if (checkValue==null) {
			if (oper == PredicateOperation.NULL) return true;
		      return false;
		}
		if (values==null) return false;
		switch (oper) {
		  case EQUAL: return checkValue == values.get(0).getFloatValue();
		  case GREATER_OR_EQUAL: return checkValue >= values.get(0).getFloatValue();
		  case BETWEEN: return checkValue >= values.get(0).getFloatValue() && checkValue <= values.get(0).getFloatValue();
		  case GREATER: return checkValue > values.get(0).getFloatValue();
		  case LESS: return checkValue < values.get(0).getFloatValue();
		  case LESS_OR_EQUAL: return checkValue <= values.get(0).getFloatValue();
		  case NOT_EQUAL: return checkValue != values.get(0).getFloatValue();
		  case NOT_IN: for(PredicateValue v:values) {
	  			          if (checkValue == v.getFloatValue()) return false;
	    	           };
	    	           return true;
		  case IN:     for(PredicateValue v:values) {
			  			  if (checkValue == v.getFloatValue()) return true;
			    	   };
			    	   return false;
		  case NOT_NULL: return true;
		  case NULL: return false;
		  default:	return false;
		}
	}
	
	public static Predicate<IFixture> sectionName(PredicateOperation oper, List<PredicateValue> values, Boolean negate) {
		Predicate<IFixture> result =  p -> {
			return checkString(p.getSectionName(),oper,values);
		};
		if (negate) return result.negate();
		return result;
	}
}
