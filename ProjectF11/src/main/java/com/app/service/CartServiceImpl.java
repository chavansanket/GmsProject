package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.CartRespDTO;
import com.app.entities.Cart;
import com.app.entities.Customer;
import com.app.entities.Product;
import com.app.respository.CartRepo;
import com.app.respository.CustomerRepo;
import com.app.respository.ProductRepo;

@Service
@Transactional
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepo cartRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	private Cart cart;
	
	@Autowired
	private ProductRepo productRepo;
	
	@Override
	public List<CartRespDTO> getAllCarts(Long customID) {
		
		List<Cart> cartList = cartRepo.findByCustomer(customID);
				
		return cartList.stream() // Stream<Emp>
				.map(cart -> mapper.map(cart,CartRespDTO.class)) // Stream<DTO>
				.collect(Collectors.toList());
	}

	@Override
	public String addToCart(CartRespDTO dto) {
//		Customer customer = customerRepo.findById(dto.getCustomerId().
//				orElseThrow(() -> new ResourceNotFoundException("Invalid cat Id !!"));

		Customer customer=customerRepo.findById(dto.getCustomerId()).
				orElseThrow(() -> new ResourceNotFoundException("Invalid cat Id !!"));
		
		
		
		Product product=productRepo.findById(dto.getProductId()).
				orElseThrow(() -> new ResourceNotFoundException("Invalid cat Id !!"));
		
		
		 cart = mapper.map(dto, Cart.class);

			cart.setCustomer(customer);
			
			cart.setProduct(product);
		
			cartRepo.save(cart);
		
		//dto.setCustomerId(customer.getCustomerId());
		//dto.setProductId(product.getProduct());
		//dto.setProductId(product.getProductId());              //error
		
	
//			cartRepo.save(cart);	 
				 


		return "product added";

	}

}
