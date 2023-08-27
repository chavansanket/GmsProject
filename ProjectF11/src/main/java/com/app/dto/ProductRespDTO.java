package com.app.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class ProductRespDTO {
	
//	private Long productId;
	private Long vendorProductId;
	
	
//	private String productName;  
//	
//	private String productDesc;
	
	
//	private LocalDate productMfgDate;
//	
//	private LocalDate productExpDate;
	
	private Long pq ;
	
	private double productPrice ;
	
}
