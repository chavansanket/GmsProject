package com.app.controller;

import javax.persistence.criteria.Order;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.OrdersDto;
import com.app.entities.Customer;
import com.app.entities.Orders;
import com.app.respository.CustomerRepo;
import com.app.respository.OrderRepo;
import com.app.service.OrderService;

import lombok.Delegate;

@RestController
@RequestMapping("/ordercontroller")
@Validated
public class OrderController {
		
	@Autowired
	private OrderService ser;
	
	@Autowired
	private OrderRepo repo;
	
	@Autowired
	private CustomerRepo custRepo;
	
	
	@Autowired 
	private ModelMapper mapper;
	
	@PostMapping
	public void insertOrder(@RequestBody OrdersDto dto)
	{	
		Customer customer=custRepo.findByCustomerId(dto.getCustomerId());
		Orders o=mapper.map(dto, Orders.class);
		o.setCustomer(customer);
		ser.addOrder(o);
	}
	
	@GetMapping("/{orderId}")
	public ResponseEntity<?> searchOrders(@PathVariable Long orderId)
	{
		return ResponseEntity.status(HttpStatus.FOUND).body(repo.findByOrderId(orderId));

	}
	
	@DeleteMapping("/{orderId}")
	public void  deleteOrderId(@PathVariable Long orderId){
		ser.deleteOrderId(orderId);
	}
}
