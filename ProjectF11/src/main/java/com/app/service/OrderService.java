package com.app.service;


import com.app.entities.Orders;

public interface OrderService {
	void addOrder(Orders o);
	
	String deleteOrderId(Long orderId);
}
