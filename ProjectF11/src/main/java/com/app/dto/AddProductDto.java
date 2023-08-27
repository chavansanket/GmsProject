package com.app.dto;

import java.time.LocalDate;


import javax.validation.constraints.NotBlank;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddProductDto {
	@JsonProperty(access = Access.READ_ONLY) // used during serialization
	private Long productId;
	
	@NotBlank
	private String productName;  
	
	private String productDesc;
	
//	@NotBlank
	private LocalDate productMfgDate;
//	@NotBlank
	private LocalDate productExpDate;
	//@NotBlank
	private double productPrice ;

	private Long catId;
	
}
