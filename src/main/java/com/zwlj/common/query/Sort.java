package com.zwlj.common.query;

import java.util.LinkedHashMap;
import java.util.Map;

public class Sort {

	public static final Integer ORDER_ASC = 1; //升序
	public static final Integer ORDER_DES = 0; //降序

	private Map<String, Integer> orders = new LinkedHashMap<String, Integer>();

	public Sort() {
	}
	
	public Sort(String key, Integer order) {
	    this.orders.put(key, order);
	}

	public Sort(String key, Order order) {
		this.orders.put(key, order.code);
	}

	public Sort on(String key, Order order) {
		this.orders.put(key, order.code);
		return this;
	}
	
	

	public Map<String, Integer> getOrders() {
		return orders;
	}

	public void setOrders(Map<String, Integer> orders) {
		this.orders = orders;
	}




	public enum  Order{
		ASC(1),
		DES(0);
		
		public int code;
		Order(int code){
			this.code = code;
		}
	}
}
