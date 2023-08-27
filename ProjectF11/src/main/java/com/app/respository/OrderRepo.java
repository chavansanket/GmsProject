package com.app.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Orders;

public interface OrderRepo extends JpaRepository<Orders, Long>{
	Orders findByOrderId(Long orderId);
	
	void deleteByOrderId(Long orderId);
	
}
