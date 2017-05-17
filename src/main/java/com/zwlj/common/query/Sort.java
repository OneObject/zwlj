package com.zwlj.common.query;

/**
 * 排序
 */
public class Sort {

	protected String property;
	protected boolean desc = false;

	public Sort(String property) {
		this.property = property;
	}

	public Sort(String property, boolean desc) {
		this.property = property;
		this.desc = desc;
	}

	/**
	 * 升序
	 * @param property java属性
	 * @return 排序
	 */
	public static Sort asc(String property) {
		return new Sort(property);
	}

	/**
	 * 降序
	 * @param property java属性
	 * @return 排序
	 */
	public static Sort desc(String property) {
		return new Sort(property);
	}

	public String getProperty() {
		return property;
	}

	public boolean isDesc() {
		return desc;
	}
}
