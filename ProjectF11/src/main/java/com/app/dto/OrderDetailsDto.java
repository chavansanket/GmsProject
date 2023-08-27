package com.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class OrderDetailsDto {
	//private Long orderDetailsId;

	//@JsonProperty(access = Access.READ_ONLY) //used during serialization
	private Long orderId;
	
	
	private Long productId;
	
	private Long amount;
	
	private Long quantity;
	
}
