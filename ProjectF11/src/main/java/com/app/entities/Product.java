//adminside products(inventory)

package com.app.entities;

import java.time.LocalDate;
import java.util.ArrayList;

import com.app.entities.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;

import antlr.collections.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity 
@Table(name = "Products") 
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"product","carts"})
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_id")
	private Long productId;
	
	@JsonIgnore
	@OneToOne (fetch = FetchType.LAZY)//mandatory , o.w hib throws MappingExc
	@JoinColumn(name="vendor_product_id")//optional : to specify name of FK col
	//optional BUT reco : to use shared PK between Emp n Address
	private VendorProducts vendorProduct;                                        //excluded in ToString
	
	
	
	
	@Column(name="product_price")
	private double productPrice ;
	

	
	
	@Column(name="product_quantity")
	private Long pq ;
	
	
	@JsonIgnore
//	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)//MERGE : NEW n INTERESTING !!!!!
//	@JoinColumn(name = "sub_cat_id") // Optional BUT reco , to specify the name of FK col.
//	private Category subcategory;
	
	
	
	
	@OneToMany(mappedBy = "product",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private java.util.List<Cart> carts = new ArrayList<>();	
	
	public void addCart(Cart c) {
		carts.add(c);
		c.setProduct(this);
	}
	
	public void removeCart(Cart c) {
		carts.remove(c);
		c.setProduct(null);
	}
	
	
//	@ManyToMany(cascade = CascadeType.PERSIST) // mandatory!
//	@JoinTable(name="order_products",
//	joinColumns = @JoinColumn(name="product_id"),
//	inverseJoinColumns = @JoinColumn(name="order_id")
//	)
//	private Set<Orders> orders=new HashSet<>();

}
