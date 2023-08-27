package com.app.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HomeProdRespoDTO {
	@JsonProperty(access = Access.READ_ONLY) //used during serialization
	private Long productId;
	
	private String productName;  //PID	PNAME	PRATE	PEXPDATE	PMANFACT	CATID
	
	private String productDesc;

	
	private LocalDate productMfgDate;
	
	private LocalDate productExpDate;
	
	
	private double productPrice ;
	
	private Long qty;

	public HomeProdRespoDTO(Long productId, String productName, String productDesc, LocalDate productMfgDate,
			LocalDate productExpDate, double productPrice,Long qty) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDesc = productDesc;
		this.productMfgDate = productMfgDate;
		this.productExpDate = productExpDate;
		this.productPrice = productPrice;
		this.qty = qty;
		
	}

	


	
	
	
}
