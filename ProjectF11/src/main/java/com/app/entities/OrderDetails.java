package com.app.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity 
@Table(name = "Orders_details") 
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude= {"product","orders"})

public class OrderDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "od_id")
	private Long odId;
	
	@ManyToOne
	@JoinColumn(name="o_id")
	private Orders orders;
	
	@ManyToOne
	@JoinColumn(name="p_id")
	private Product product;
		
	private Long quantity;
	
	private double amount;
}
