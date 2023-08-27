package com.app.dto;


import com.app.entities.VendorProducts;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartRespDTO {
	
	@JsonProperty(access = Access.READ_ONLY) //used during serialization

	private Long cartId;
	
	private Long customerId;
	
	private Long productId;
	
	private int qty;
}