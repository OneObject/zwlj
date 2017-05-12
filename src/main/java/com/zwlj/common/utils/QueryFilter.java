package com.zwlj.common.utils;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class QueryFilter implements Serializable {

	private static final long serialVersionUID = -8764718383999381151L;

	/**
	 * Property string representing the root entity of the search. This is just the empty string ("").
	 */
	public static final String ROOT_ENTITY = "";
	
	/**
	 * The name of the property to QueryFilter on. It may be nested. Examples:
	 * <code>"name", "dateOfBirth", "employee.age", "employee.spouse.job.title"</code>
	 */
	protected String property;

	/**
	 * The value to compare the property with. Should be of a compatible type
	 * with the property. Examples: <code>"Fred", new Date(), 45</code>
	 */
	protected Object value;

	/**
	 * The type of comparison to do between the property and the value. The
	 * options are limited to the integer constants on this class:
	 * 
	 * <code>OP_EQAUL, OP_NOT_EQUAL, OP_LESS_THAN, OP_GREATER_THAN, LESS_OR_EQUAL, OP_GREATER_OR_EQUAL, OP_IN, OP_NOT_IN, OP_LIKE, OP_ILIKE, OP_NULL, OP_NOT_NULL, OP_EMPTY, OP_NOT_EMPTY, OP_SOME, OP_ALL, OP_NONE, OP_AND, OP_OR, OP_NOT</code>
	 * .
	 */
	protected int operator;

	public QueryFilter() {

	}

	public QueryFilter(String property, Object value, int operator) {
		this.property = property;
		this.value = value;
		this.operator = operator;
	}

	public QueryFilter(String property, Object value) {
		this.property = property;
		this.value = value;
		this.operator = OP_EQUAL;
	}

	public static final int OP_EQUAL = 0;
	public static final int OP_NOT_EQUAL = 1;
	public static final int OP_LESS_THAN = 2;
	public static final int OP_GREATER_THAN = 3;
	public static final int OP_LESS_OR_EQUAL = 4;
	public static final int OP_GREATER_OR_EQUAL = 5;
	public static final int OP_LIKE = 6;
	public static final int OP_ILIKE = 7;
	public static final int OP_IN = 8;
	public static final int OP_NOT_IN = 9;
	public static final int OP_NULL = 10;
	public static final int OP_NOT_NULL = 11;
	public static final int OP_EMPTY = 12;
	public static final int OP_NOT_EMPTY = 13;
	public static final int OP_AND = 100;
	public static final int OP_OR = 101;
	public static final int OP_NOT = 102;
	public static final int OP_SOME = 200;
	public static final int OP_ALL = 201;
	public static final int OP_NONE = 202;
	public static final int OP_CUSTOM = 999;

	/**
	 * Create a new QueryFilter using the == operator.
	 */
	public static QueryFilter equal(String property, Object value) {
		return new QueryFilter(property, value, OP_EQUAL);
	}

	/**
	 * Create a new QueryFilter using the < operator.
	 */
	public static QueryFilter lessThan(String property, Object value) {
		return new QueryFilter(property, value, OP_LESS_THAN);
	}

	/**
	 * Create a new QueryFilter using the > operator.
	 */
	public static QueryFilter greaterThan(String property, Object value) {
		return new QueryFilter(property, value, OP_GREATER_THAN);
	}

	/**
	 * Create a new QueryFilter using the <= operator.
	 */
	public static QueryFilter lessOrEqual(String property, Object value) {
		return new QueryFilter(property, value, OP_LESS_OR_EQUAL);
	}

	/**
	 * Create a new QueryFilter using the >= operator.
	 */
	public static QueryFilter greaterOrEqual(String property, Object value) {
		return new QueryFilter(property, value, OP_GREATER_OR_EQUAL);
	}

	/**
	 * Create a new QueryFilter using the IN operator.
	 * 
	 * <p>
	 * This takes a variable number of parameters. Any number of values can be
	 * specified.
	 */
	public static QueryFilter in(String property, Collection<?> value) {
		return new QueryFilter(property, value, OP_IN);
	}

	/**
	 * Create a new QueryFilter using the IN operator.
	 * 
	 * <p>
	 * This takes a variable number of parameters. Any number of values can be
	 * specified.
	 */
	public static QueryFilter in(String property, Object... value) {
		return new QueryFilter(property, value, OP_IN);
	}

	/**
	 * Create a new QueryFilter using the NOT IN operator.
	 * 
	 * <p>
	 * This takes a variable number of parameters. Any number of values can be
	 * specified.
	 */
	public static QueryFilter notIn(String property, Collection<?> value) {
		return new QueryFilter(property, value, OP_NOT_IN);
	}

	/**
	 * Create a new QueryFilter using the NOT IN operator.
	 * 
	 * <p>
	 * This takes a variable number of parameters. Any number of values can be
	 * specified.
	 */
	public static QueryFilter notIn(String property, Object... value) {
		return new QueryFilter(property, value, OP_NOT_IN);
	}

	/**
	 * Create a new QueryFilter using the LIKE operator.
	 */
	public static QueryFilter like(String property, String value) {
		return new QueryFilter(property, value, OP_LIKE);
	}

	/**
	 * Create a new QueryFilter using the ILIKE operator.
	 */
	public static QueryFilter ilike(String property, String value) {
		return new QueryFilter(property, value, OP_ILIKE);
	}

	/**
	 * Create a new QueryFilter using the != operator.
	 */
	public static QueryFilter notEqual(String property, Object value) {
		return new QueryFilter(property, value, OP_NOT_EQUAL);
	}

	/**
	 * Create a new QueryFilter using the IS NULL operator.
	 */
	public static QueryFilter isNull(String property) {
		return new QueryFilter(property, true, OP_NULL);
	}

	/**
	 * Create a new QueryFilter using the IS NOT NULL operator.
	 */
	public static QueryFilter isNotNull(String property) {
		return new QueryFilter(property, true, OP_NOT_NULL);
	}

	/**
	 * Create a new QueryFilter using the IS EMPTY operator.
	 */
	public static QueryFilter isEmpty(String property) {
		return new QueryFilter(property, true, OP_EMPTY);
	}

	/**
	 * Create a new QueryFilter using the IS NOT EMPTY operator.
	 */
	public static QueryFilter isNotEmpty(String property) {
		return new QueryFilter(property, true, OP_NOT_EMPTY);
	}

	
	


	/**
	 * <p>Create a new QueryFilter using a custom JPQL/HQL expression. This can be
	 * any valid where-clause type expression. Reference properties by wrapping
	 * them with curly braces ({}).
	 * 
	 * <p>Here are some examples:
	 * <pre>
	 * // Referencing a property in a custom expression
	 * QueryFilter.custom("{serialNumber} like '%4780%'");
	 * // comparing two properties
	 * QueryFilter.custom("{parent.spotCount} > {spotCount} + 4");
	 * // A constant
	 * QueryFilter.custom("1 = 1");
	 * // A function
	 * QueryFilter.custom("{dueDate} > current_date()");
	 * // A subquery
	 * QueryFilter.custom("{id} in (select pc.cat_id from popular_cats pc where pc.color = 'blue')");
	 * </pre>
	 * 
	 * @param expression JPQL/HQL where-clause expression
	 */
	public static QueryFilter custom(String expression) {
		return new QueryFilter(expression, null, OP_CUSTOM);
	}
	
	/**
	 * <p>Create a new QueryFilter using a custom JPQL/HQL expression. This can be
	 * any valid where-clause type expression. Reference properties by wrapping
	 * them with curly braces ({}). The expression can also contain place
	 * holders for the QueryFilter values; these are indicated by JPQL-style
	 * positional parameters (i.e. a question mark (?) followed by a number
	 * indicating the parameter order, starting with one).
	 * 
	 * <p>Here are some examples:
	 * <pre>
	 * // Referencing a property in a custom expression
	 * QueryFilter.custom("{serialNumber} like ?1", "%4780%");
	 * // comparing two properties
	 * QueryFilter.custom("{parent.spotCount} + ?1 > {spotCount} + ?2", 0, 4);
	 * // A constant
	 * QueryFilter.custom("?1 = ?2", 1, 1);
	 * // A function
	 * QueryFilter.custom("?1 > current_date()", someDate);
	 * // A subquery
	 * QueryFilter.custom("{id} in (select pc.cat_id from popular_cats pc where pc.color = ?1)", "blue");
	 * </pre>
	 * 
	 * @param expression JPQL/HQL where-clause expression
	 * @param values one or more values to fill in the numbered placeholders in
	 * 		  the expression
	 */
	public static QueryFilter custom(String expression, Object... values) {
		return new QueryFilter(expression, values, OP_CUSTOM);
	}

	/**
	 * <p>Create a new QueryFilter using a custom JPQL/HQL expression. This can be
	 * any valid where-clause type expression. Reference properties by wrapping
	 * them with curly braces ({}). The expression can also contain place
	 * holders for the QueryFilter values; these are indicated by JPQL-style
	 * positional parameters (i.e. a question mark (?) followed by a number
	 * indicating the parameter order, starting with one).
	 * 
	 * <p>Here are some examples:
	 * <pre>
	 * // Referencing a property in a custom expression
	 * QueryFilter.custom("{serialNumber} like ?1", Collections.singleton("%4780%"));
	 * // comparing two properties
	 * QueryFilter.custom("{parent.spotCount} + ?1 > {spotCount} + ?2", Arrays.asList(0, 4));
	 * // A constant
	 * QueryFilter.custom("?1 = ?2", Arrays.asList(1, 1));
	 * // A function
	 * QueryFilter.custom("?1 > current_date()", Collections.singleton(someDate));
	 * // A subquery
	 * QueryFilter.custom("{id} in (select pc.cat_id from popular_cats pc where pc.color = ?1)", Collections.singleton("blue"));
	 * </pre>
	 * 
	 * @param expression JPQL/HQL where-clause expression
	 * @param values one or more values to fill in the numbered placeholders in
	 * 		  the expression
	 */
	/*public static QueryFilter custom(String expression, Collection<?> values) {
		return new QueryFilter(expression, values, OP_CUSTOM);
	}*/

	/**
	 * Used with OP_OR and OP_AND QueryFilters. These QueryFilters take a collection of
	 * QueryFilters as their value. This method adds a QueryFilter to that list.
	 */
	

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public int getOperator() {
		return operator;
	}

	public void setOperator(int operator) {
		this.operator = operator;
	}
	
	/**
	 * Returns the value as a List, converting if necessary. If the value is a
	 * List, it will be returned directly. If it is any other Collection type or
	 * if it is an Array, an ArrayList will be returned with all the same
	 * elements. If the value is any other non-null value, a List containing
	 * just that one value will be returned. If it is <code>null</code>,
	 * <code>null</code> will be returned.
	 */
	public List<?> getValuesAsList() {
		if (value == null) {
			return null;
		} else if (value instanceof List<?>) {
			return (List<?>) value;
		} else if (value instanceof Collection<?>) {
			return new ArrayList<Object>((Collection<?>) value);
		} else if (value.getClass().isArray()) {
			ArrayList<Object> list = new ArrayList<Object>(Array.getLength(value));
			for (int i = 0; i < Array.getLength(value); i++) {
				list.add(Array.get(value, i));
			}
			return list;
		} else {
			return Collections.singletonList(value);
		}
	}
	
	/**
	 * Returns the value as a Collection, converting if necessary. If the value
	 * is a Collection, it will be returned directly. If it is an Array, an
	 * ArrayList will be returned with all the same elements. If the value is
	 * any other non-null value, a Set containing just that one value will be
	 * returned. If it is <code>null</code>, <code>null</code> will be returned.
	 * 
	 * @return
	 */
	public Collection<?> getValuesAsCollection() {
		if (value == null) {
			return null;
		} else if (value instanceof Collection<?>) {
			return (Collection<?>) value;
		} else if (value.getClass().isArray()) {
			ArrayList<Object> list = new ArrayList<Object>(Array.getLength(value));
			for (int i = 0; i < Array.getLength(value); i++) {
				list.add(Array.get(value, i));
			}
			return list;
		} else {
			return Collections.singleton(value);
		}
	}

	/**
	 * @return true if the operator should have a single value specified.
	 * 
	 *         <p>
	 *         <code>EQUAL, NOT_EQUAL, LESS_THAN, LESS_OR_EQUAL, GREATER_THAN, GREATER_OR_EQUAL, LIKE, ILIKE</code>
	 */
	public boolean isTakesSingleValue() {
		return operator <= 7;
	}

	/**
	 * @return true if the operator should have a list of values specified.
	 * 
	 *         <p>
	 *         <code>IN, NOT_IN</code>
	 */
	public boolean isTakesListOfValues() {
		return operator == OP_IN || operator == OP_NOT_IN;
	}

	/**
	 * @return true if the operator does not require a value to be specified.
	 * 
	 *         <p>
	 *         <code>NULL, NOT_NULL, EMPTY, NOT_EMPTY</code>
	 */
	public boolean isTakesNoValue() {
		return (operator >= 10 && operator <= 13) || operator == OP_CUSTOM;
	}

	/**
	 * @return true if the operator should have a single QueryFilter specified for
	 *         the value.
	 * 
	 *         <p>
	 *         <code>NOT, ALL, SOME, NONE</code>
	 */
	public boolean isTakesSingleSubQueryFilter() {
		return operator == OP_NOT || (operator >= 200 && operator < 300);
	}

	/**
	 * @return true if the operator should have a list of QueryFilters specified for
	 *         the value.
	 * 
	 *         <p>
	 *         <code>AND, OR</code>
	 */
	public boolean isTakesListOfSubQueryFilters() {
		return operator == OP_AND || operator == OP_OR;
	}

	/**
	 * @return true if the operator does not require a property to be specified.
	 * 
	 *         <p>
	 *         <code>AND, OR, NOT</code>
	 */
	public boolean isTakesNoProperty() {
		return operator >= 100 && operator <= 102;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + operator;
		result = prime * result + ((property == null) ? 0 : property.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QueryFilter other = (QueryFilter) obj;
		if (operator != other.operator)
			return false;
		if (property == null) {
			if (other.property != null)
				return false;
		} else if (!property.equals(other.property))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String toString() {
		switch (operator) {
		case QueryFilter.OP_IN:
			return "`" + property + "` in (" + InternalUtil.paramDisplayString(value) + ")";
		case QueryFilter.OP_NOT_IN:
			return "`" + property + "` not in (" + InternalUtil.paramDisplayString(value) + ")";
		case QueryFilter.OP_EQUAL:
			return "`" + property + "` = " + InternalUtil.paramDisplayString(value);
		case QueryFilter.OP_NOT_EQUAL:
			return "`" + property + "` != " + InternalUtil.paramDisplayString(value);
		case QueryFilter.OP_GREATER_THAN:
			return "`" + property + "` > " + InternalUtil.paramDisplayString(value);
		case QueryFilter.OP_LESS_THAN:
			return "`" + property + "` < " + InternalUtil.paramDisplayString(value);
		case QueryFilter.OP_GREATER_OR_EQUAL:
			return "`" + property + "` >= " + InternalUtil.paramDisplayString(value);
		case QueryFilter.OP_LESS_OR_EQUAL:
			return "`" + property + "` <= " + InternalUtil.paramDisplayString(value);
		case QueryFilter.OP_LIKE:
			return "`" + property + "` LIKE " + InternalUtil.paramDisplayString(value);
		case QueryFilter.OP_ILIKE:
			return "`" + property + "` ILIKE " + InternalUtil.paramDisplayString(value);
		case QueryFilter.OP_NULL:
			return "`" + property + "` IS NULL";
		case QueryFilter.OP_NOT_NULL:
			return "`" + property + "` IS NOT NULL";
		case QueryFilter.OP_EMPTY:
			return "`" + property + "` IS EMPTY";
		case QueryFilter.OP_NOT_EMPTY:
			return "`" + property + "` IS NOT EMPTY";
		case QueryFilter.OP_AND:
		case QueryFilter.OP_OR:
			if (!(value instanceof List)) {
				return (operator == QueryFilter.OP_AND ? "AND: " : "OR: ") + "**INVALID VALUE - NOT A LIST: (" + value
						+ ") **";
			}

			String op = operator == QueryFilter.OP_AND ? " and " : " or ";

			StringBuilder sb = new StringBuilder("(");
			boolean first = true;
			for (Object o : ((List) value)) {
				if (first) {
					first = false;
				} else {
					sb.append(op);
				}
				if (o instanceof QueryFilter) {
					sb.append(o.toString());
				} else {
					sb.append("**INVALID VALUE - NOT A QueryFilter: (" + o + ") **");
				}
			}
			if (first)
				return (operator == QueryFilter.OP_AND ? "AND: " : "OR: ") + "**EMPTY LIST**";

			sb.append(")");
			return sb.toString();
		case QueryFilter.OP_NOT:
			if (!(value instanceof QueryFilter)) {
				return "NOT: **INVALID VALUE - NOT A QueryFilter: (" + value + ") **";
			}
			return "not " + value.toString();
		case QueryFilter.OP_SOME:
			if (!(value instanceof QueryFilter)) {
				return "SOME: **INVALID VALUE - NOT A QueryFilter: (" + value + ") **";
			}
			return "some `" + property + "` {" + value.toString() + "}";
		case QueryFilter.OP_ALL:
			if (!(value instanceof QueryFilter)) {
				return "ALL: **INVALID VALUE - NOT A QueryFilter: (" + value + ") **";
			}
			return "all `" + property + "` {" + value.toString() + "}";
		case QueryFilter.OP_NONE:
			if (!(value instanceof QueryFilter)) {
				return "NONE: **INVALID VALUE - NOT A QueryFilter: (" + value + ") **";
			}
			return "none `" + property + "` {" + value.toString() + "}";
		case QueryFilter.OP_CUSTOM:
			if (value == null || (value instanceof Collection && ((Collection) value).isEmpty()) || (value.getClass().isArray() && Array.getLength(value) == 0)) {
				return "CUSTOM[" + property + "]";
			} else {
				StringBuilder sb2 = new StringBuilder();
				sb2.append("CUSTOM[").append(property).append("]values(");
				boolean first2 = true;
				if (value instanceof Collection) {
					if (first2) {
						first2 = false;
					} else {
						sb2.append(',');
					}
					for (Object o : (Collection) value) {
						sb2.append(o);
					}
				} else if (value.getClass().isArray()) {
					if (first2) {
						first2 = false;
					} else {
						sb2.append(',');
					}
					for (int i = 0; i < Array.getLength(value); i++) {
						sb2.append(Array.get(value, i));
					}
				} else {
					sb2.append(value);
				}
				sb2.append(")");
			}
		default:
			return "**INVALID OPERATOR: (" + operator + ") - VALUE: " + InternalUtil.paramDisplayString(value) + " **";
		}
	}

	
	static class InternalUtil{
		/**
		 * <p>
		 * Return an instance of the given class type that has the given value. For
		 * example, if type is <code>Long</code> and <code>Integer</code> type with
		 * the value 13 is passed in, a new instance of <code>Long</code> will be
		 * returned with the value 13.
		 * 
		 * <p>
		 * If the value is already of the correct type, it is simply returned.
		 * 
		 * @throws ClassCastException
		 *             if the value cannot be converted to the given type.
		 */
		public static Object convertIfNeeded(Object value, Class<?> type) throws ClassCastException {
			
			// Since we're returning an object, we will never be able to return a primitive value.
			// We will return the boxed type instead.
			if (type.isPrimitive()) {
				if (boolean.class.equals(type)) {
					type = Boolean.class;
				} else if (char.class.equals(type)) {
					type = Character.class;
				} else if (byte.class.equals(type)) {
					type = Byte.class;
				} else if (short.class.equals(type)) {
					type = Short.class;
				} else if (int.class.equals(type)) {
					type = Integer.class;
				} else if (long.class.equals(type)) {
					type = Long.class;
				} else if (float.class.equals(type)) {
					type = Float.class;
				} else if (double.class.equals(type)) {
					type = Double.class;
				}
			}
			
			if (value == null)
				return null;
			if (type.isInstance(value))
				return value;
			
			

			if (String.class.equals(type)) {
				return value.toString();
			} else if (Number.class.isAssignableFrom(type)) {
				// the desired type is a number
				if (value instanceof Number) {
					// the value is also a number of some kind. do a conversion
					// to the correct number type.
					Number num = (Number) value;

					if (type.equals(Double.class)) {
						return new Double(num.doubleValue());
					} else if (type.equals(Float.class)) {
						return new Float(num.floatValue());
					} else if (type.equals(Long.class)) {
						return new Long(num.longValue());
					} else if (type.equals(Integer.class)) {
						return new Integer(num.intValue());
					} else if (type.equals(Short.class)) {
						return new Short(num.shortValue());
					} else {
						try {
							return type.getConstructor(String.class).newInstance(value.toString());
						} catch (IllegalArgumentException e) {
						} catch (SecurityException e) {
						} catch (InstantiationException e) {
						} catch (IllegalAccessException e) {
						} catch (InvocationTargetException e) {
						} catch (NoSuchMethodException e) {
						}
					}
				} else if (value instanceof String) {
					//the value is a String. attempt to parse the string
					try {
						if (type.equals(Double.class)) {
							return Double.parseDouble((String) value);
						} else if (type.equals(Float.class)) {
							return Float.parseFloat((String) value);
						} else if (type.equals(Long.class)) {
							return Long.parseLong((String) value);
						} else if (type.equals(Integer.class)) {
							return Integer.parseInt((String) value);
						} else if (type.equals(Short.class)) {
							return Short.parseShort((String) value);
						} else if (type.equals(Byte.class)) {
							return Byte.parseByte((String) value);
						}
					} catch (NumberFormatException ex) {
						//fall through to the error thrown below
					}
				}
			} else if (Class.class.equals(type)) {
				try {
					return Class.forName(value.toString());
				} catch (ClassNotFoundException e) {
					throw new ClassCastException("Unable to convert value " + value.toString() + " to type Class");
				}
			}

			throw new ClassCastException("Unable to convert value of type " + value.getClass().getName() + " to type "
					+ type.getName());
		}

		public static String paramDisplayString(Object val) {
			if (val == null) {
				return "null";
			} else if (val instanceof String) {
				return "\"" + val + "\"";
			} else if (val instanceof Collection) {
				StringBuilder sb = new StringBuilder();
				sb.append(val.getClass().getSimpleName());
				sb.append(" {");
				boolean first = true;
				for (Object o : (Collection<?>) val) {
					if (first) {
						first = false;
					} else {
						sb.append(", ");
					}
					sb.append(paramDisplayString(o));
				}
				sb.append("}");
				return sb.toString();
			} else if (val instanceof Object[]) {
				StringBuilder sb = new StringBuilder();
				sb.append(val.getClass().getComponentType().getSimpleName());
				sb.append("[] {");
				boolean first = true;
				for (Object o : (Object[]) val) {
					if (first) {
						first = false;
					} else {
						sb.append(", ");
					}
					sb.append(paramDisplayString(o));
				}
				sb.append("}");
				return sb.toString();
			} else {
				return val.toString();
			}
		}
	}
	
}
