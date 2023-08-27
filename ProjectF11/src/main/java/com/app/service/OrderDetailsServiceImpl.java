package com.app.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.OrderDetailsDto;
import com.app.dto.Top10product;
import com.app.entities.OrderDetails;
import com.app.entities.Orders;
import com.app.entities.Product;
import com.app.respository.OrderDetailRepo;
import com.app.respository.OrderRepo;
import com.app.respository.ProductRepo;


@Service
@Transactional
public class OrderDetailsServiceImpl implements OrderDetailsService {

	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private OrderDetailRepo orderDetailsRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private ModelMapper mapper;
		
	@Override
	public Orders findProduct(Long productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDetails addOrderDetails(OrderDetailsDto dto) {
		Orders order=orderRepo.findById(dto.getOrderId()).orElseThrow(()->new ResourceNotFoundException(""));
		
		System.out.println("order in orderdtls---"+order);
		System.out.println("productId----"+dto.getProductId());
		Product product=productRepo.findById(dto.getProductId()).orElseThrow(()->new ResourceNotFoundException(""));
		System.out.println("product in orderdtls---"+product);
		OrderDetails orderDetails = mapper.map(dto, OrderDetails.class);
		Long pq = dto.getQuantity();
		product.setPq(product.getPq()-pq);//quantity updated
		orderDetails.setOrders(order);
		orderDetails.setProduct(product);
		productRepo.save(product);
		orderDetails.setAmount(orderDetails.getQuantity()* orderDetails.getProduct().getProductPrice());
			System.out.println("orderdetails---------"+orderDetails);
			orderDetailsRepo.save(orderDetails);
			getOrderDetailsByOrderId(dto.getOrderId());
			
			
		return orderDetails;
	}
	
	@Override
	public List<OrderDetails> getOrderDetailsByOrderId(Long orderId){
		updateOrderTotal(orderId);	
		return orderDetailsRepo.findOrderDetailsByOrderId(orderId);
	}

	@Override
	public void updateOrderTotal(Long orderId) {
		
		Orders order=orderRepo.findByOrderId(orderId);
		//directly putting the total of all the product fetched using the sum() and groupby query.
		order.setTotal(orderDetailsRepo.findTotalSumOfProducts(orderId));
		orderRepo.save(order);
	}

	@Override
	public void deletByOdId(Long orderId) {
		orderDetailsRepo.deleteByOdId(orderId);
		
	}

//	@Override
//	public List<Object[]> find10products() {
//		return orderDetailsRepo.findTop10Products();
//	}

}
