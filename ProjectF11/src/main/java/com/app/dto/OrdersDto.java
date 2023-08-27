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

public class OrdersDto {

	@JsonProperty(access = Access.READ_ONLY) //used during serialization
	private Long orderId;
	
	@NotBlank
	private LocalDate odate;
	
	private Long customerId;
	
	private Long total;	
	
}
