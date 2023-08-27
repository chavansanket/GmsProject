
package com.app.service;

import java.util.List;

import com.app.dto.CartRespDTO;

public interface CartService {
	
	List<CartRespDTO> getAllCarts(Long customID);
	
	public String addToCart(CartRespDTO dto);
	
}
