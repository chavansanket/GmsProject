package com.app.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.Cart;

public interface CartRepo extends JpaRepository<Cart, Long> {
	@Query("select c from Cart c where c.customer.customerId=:customId")
	List<Cart> findByCustomer(Long customId);
	
//	@Query("delete from Cart c where c.product.productId=:productId")
//	void deleteByCustomer(Long productId);
}
