package com.app.service;

import java.util.List;

import com.app.dto.OrderDetailsDto;
import com.app.dto.Top10product;
import com.app.entities.OrderDetails;
import com.app.entities.Orders;

public interface OrderDetailsService {
	
	Orders findProduct(Long productId); 
	
	OrderDetails addOrderDetails(OrderDetailsDto dto);
		
	List<OrderDetails> getOrderDetailsByOrderId(Long orderId);
	
	void updateOrderTotal(Long orderId);
	
	void deletByOdId(Long orderId);
	
//	List<Object[]> find10products();
	
	
}
