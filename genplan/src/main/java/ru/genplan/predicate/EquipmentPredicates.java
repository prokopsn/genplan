package ru.genplan.predicate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import ru.genplan.equipment.IFixture;
/**
 * 
 * @author Sergey Prokopchik
 *
 */
@SuppressWarnings("serial")
public class EquipmentPredicates {
	private static Map<String,PredicateFunction<IFixture>> predicates;
	static {
		predicates = new HashMap<String,PredicateFunction<IFixture>>() {{
		  	put("equipment.section.name",             EquipmentPredicates::sectionName);
		  	put("equipment.fixture.absoluteDownFixNo",EquipmentPredicates::absoluteDownFixNo);
		  	put("equipment.fixture.absoluteUpFixNo",  EquipmentPredicates::absoluteUpFixNo);
		  	put("equipment.fixture.availableWidth",   EquipmentPredicates::availableWidth);
		  	put("equipment.fixture.id",		          EquipmentPredicates::id);
		  	put("equipment.fixture.depth",            EquipmentPredicates::depth);
		  	put("equipment.fixture.type",             EquipmentPredicates::fixtureType);
		  	put("equipment.fixture.merchYMax",        EquipmentPredicates::merchYMax);
		  	put("equipment.fixture.name",             EquipmentPredicates::name);
		  	put("equipment.section.fixtureCount",     EquipmentPredicates::sectionFixtureCount);
		  	put("equipment.section.sectionNo",        EquipmentPredicates::sectionNo);
		  	put("equipment.fixture.spaceAvailable",   EquipmentPredicates::spaceAvailable);
		  	put("equipment.fixture.width",            EquipmentPredicates::width);
		  	put("equipment.fixture.x",                EquipmentPredicates::x);
		  	put("equipment.fixture.y",                EquipmentPredicates::y);
		}};
	}
	
	public static PredicateFunction<IFixture> getPredicate(String predicateName) {
		return predicates.get(predicateName);
	}
	/**
	 * Метод выполняет объединение предикатов через Predicate.and
	 * @param predList - список предикатов
	 * @return
	 */
	public static Predicate<IFixture> predicateAnd(List<Predicate<? super IFixture>> predList) {
		Predicate<IFixture> result = p -> true;
		for(Predicate<? super IFixture> p:predList) {
			result = result.and(p);
		}
		return result;
	}
	/**
	 * Метод выполняет объединение предикатов через  Predicate.or
	 * @param predList - список предикатов
	 * @return
	 */
	public static Predicate<IFixture> predicateOr(List<Predicate<? super IFixture>> predList) {
		Predicate<IFixture> result = p -> false;
		for(Predicate<? super IFixture> p:predList) {
			result = result.or(p);
		}
		return result;
	}
	/**
	 * Метод проверяет значение checkValue [String] на соответствие переданным данным 
	 * @param checkValue проверяемое значение
	 * @param oper операция
	 * @param values значения на соответствие которым осуществляется проверка
	 * @return
	 */
	public static boolean checkString(String checkValue, PredicateOperation oper, List<PredicateValue> values) {
		if (checkValue==null) {
			if (oper == PredicateOperation.NULL) return true;
		      return false;
		}
		if (values==null) return false;
		switch (oper) {
		  case GREATER_OR_EQUAL:
		  case LESS_OR_EQUAL:
		  case BETWEEN:
		  case GREATER:
		  case LESS: throw new UnsupportedOperationException(
	                    String.format("Operation %s unsupported for data type [String]",
	                    		oper.getOperation())
	                 );
		  case EQUAL: return checkValue == values.get(0).getStringValue();
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
	/**
	 * Метод проверяет значение checkValue [Integer] на соответствие переданным данным 
	 * @param checkValue проверяемое значение
	 * @param oper операция
	 * @param values значения на соответствие которым осуществляется проверка
	 * @return
	 */
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
	
	/**
	 * Метод проверяет значение checkValue [Float] на соответствие переданным данным 
	 * @param checkValue проверяемое значение
	 * @param oper операция
	 * @param values значения на соответствие которым осуществляется проверка
	 * @return
	 */
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
	/**
	 * equipment.section.tag
	 * @param oper
	 * @param values
	 * @param negate
	 * @return
	 */
	public static Predicate<IFixture> sectionTag(PredicateOperation oper, List<PredicateValue> values, Boolean negate) {
		Predicate<IFixture> result =  p -> {
			return checkString(p.getSectionName(),oper,values);
		};
		if (negate) return result.negate();
		return result;
	}
	/**
	 * equipment.section.name
	 * @param oper
	 * @param values
	 * @param negate
	 * @return
	 */
	public static Predicate<IFixture> sectionName(PredicateOperation oper, List<PredicateValue> values, Boolean negate) {
		Predicate<IFixture> result =  p -> {
			return checkString(p.getSectionName(),oper,values);
		};
		if (negate) return result.negate();
		return result;
	}
	/**
	 * equipment.fixture.absoluteDownFixNo
	 * @param oper
	 * @param values
	 * @param negate
	 * @return
	 */
	public static Predicate<IFixture> absoluteDownFixNo(PredicateOperation oper, List<PredicateValue> values, Boolean negate) {
		Predicate<IFixture> result =  p -> {
			return checkInteger(p.getAbsoluteDownFixNo(),oper,values);
		};
		if (negate) return result.negate();
		return result;
	}
	
	/**
	 * equipment.fixture.absoluteUpFixNo
	 * @param oper
	 * @param values
	 * @param negate
	 * @return
	 */
	public static Predicate<IFixture> absoluteUpFixNo(PredicateOperation oper, List<PredicateValue> values, Boolean negate) {
		Predicate<IFixture> result =  p -> {
			return checkInteger(p.getAbsoluteUpFixNo(),oper,values);
		};
		if (negate) return result.negate();
		return result;
	}
	/**
	 * equipment.fixture.availableWidth
	 * @param oper
	 * @param values
	 * @param negate
	 * @return
	 */
	public static Predicate<IFixture> availableWidth(PredicateOperation oper, List<PredicateValue> values, Boolean negate) {
		Predicate<IFixture> result =  p -> {
			return checkInteger(p.getAvailableWidth(),oper,values);
		};
		if (negate) return result.negate();
		return result;
	}
	/**
	 * equipment.fixture.depth
	 * @param oper
	 * @param values
	 * @param negate
	 * @return
	 */
	public static Predicate<IFixture> depth(PredicateOperation oper, List<PredicateValue> values, Boolean negate) {
		Predicate<IFixture> result =  p -> {
			return checkInteger(p.getDepth(),oper,values);
		};
		if (negate) return result.negate();
		return result;
	}
	/**
	 * equipment.fixture.type
	 * @param oper
	 * @param values
	 * @param negate
	 * @return
	 */
	public static Predicate<IFixture> fixtureType(PredicateOperation oper, List<PredicateValue> values, Boolean negate) {
		Predicate<IFixture> result =  p -> {
			return checkInteger(p.getFixtureType(),oper,values);
		};
		if (negate) return result.negate();
		return result;
	}
	/**
	 * equipment.fixture.id
	 * @param oper
	 * @param values
	 * @param negate
	 * @return
	 */
	public static Predicate<IFixture> id(PredicateOperation oper, List<PredicateValue> values, Boolean negate) {
		Predicate<IFixture> result =  p -> {
			return checkInteger(p.getId(),oper,values);
		};
		if (negate) return result.negate();
		return result;
	}
	
	/**
	 * equipment.fixture.merchYMax
	 * @param oper
	 * @param values
	 * @param negate
	 * @return
	 */
	public static Predicate<IFixture> merchYMax(PredicateOperation oper, List<PredicateValue> values, Boolean negate) {
		Predicate<IFixture> result =  p -> {
			return checkInteger(p.getMerchymax(),oper,values);
		};
		if (negate) return result.negate();
		return result;
	}
	
	/**
	 * equipment.fixture.name
	 * @param oper
	 * @param values
	 * @param negate
	 * @return
	 */
	public static Predicate<IFixture> name(PredicateOperation oper, List<PredicateValue> values, Boolean negate) {
		Predicate<IFixture> result =  p -> {
			return checkString(p.getName(),oper,values);
		};
		if (negate) return result.negate();
		return result;
	}
	
	/**
	 * equipment.section.fixtureCount
	 * @param oper
	 * @param values
	 * @param negate
	 * @return
	 */
	public static Predicate<IFixture> sectionFixtureCount(PredicateOperation oper, List<PredicateValue> values, Boolean negate) {
		Predicate<IFixture> result =  p -> {
			return checkInteger(p.getSectionFixtureCount(),oper,values);
		};
		if (negate) return result.negate();
		return result;
	}
	
	/**
	 * equipment.section.sectionNo
	 * @param oper
	 * @param values
	 * @param negate
	 * @return
	 */
	public static Predicate<IFixture> sectionNo(PredicateOperation oper, List<PredicateValue> values, Boolean negate) {
		Predicate<IFixture> result =  p -> {
			return checkInteger(p.getSectionNo(),oper,values);
		};
		if (negate) return result.negate();
		return result;
	}
	
	/**
	 * equipment.fixture.spaceAvailable
	 * @param oper
	 * @param values
	 * @param negate
	 * @return
	 */
	public static Predicate<IFixture> spaceAvailable(PredicateOperation oper, List<PredicateValue> values, Boolean negate) {
		Predicate<IFixture> result =  p -> {
			return checkInteger(p.getSpaceAvailable(),oper,values);
		};
		if (negate) return result.negate();
		return result;
	}
	
	/**
	 * equipment.fixture.width
	 * @param oper
	 * @param values
	 * @param negate
	 * @return
	 */
	public static Predicate<IFixture> width(PredicateOperation oper, List<PredicateValue> values, Boolean negate) {
		Predicate<IFixture> result =  p -> {
			return checkInteger(p.getWidth(),oper,values);
		};
		if (negate) return result.negate();
		return result;
	}
	
	/**
	 * equipment.fixture.x
	 * @param oper
	 * @param values
	 * @param negate
	 * @return
	 */
	public static Predicate<IFixture> x(PredicateOperation oper, List<PredicateValue> values, Boolean negate) {
		Predicate<IFixture> result =  p -> {
			return checkInteger(p.getX(),oper,values);
		};
		if (negate) return result.negate();
		return result;
	}
	
	/**
	 * equipment.fixture.y
	 * @param oper
	 * @param values
	 * @param negate
	 * @return
	 */
	public static Predicate<IFixture> y(PredicateOperation oper, List<PredicateValue> values, Boolean negate) {
		Predicate<IFixture> result =  p -> {
			return checkInteger(p.getY(),oper,values);
		};
		if (negate) return result.negate();
		return result;
	}
}
