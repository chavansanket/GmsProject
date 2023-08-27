//All vendors all their products

//
package com.app.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity 
@Table(name = "Vendor_Products") 
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"vendor","category","subcategory"})
//@EqualsAndHashCode(callSuper = false, doNotUseGetters = true,of = "email")
public class VendorProducts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_id")
	private Long vendorProductId;
	
	@Column(name="product_name",length =20)
	private String productName;  //PID	PNAME	PRATE	PEXPDATE	PMANFACT	CATID
	
	@Column(name="product_desc",length =100)
	private String productDesc;

	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="product_mfgdate")
	private LocalDate productMfgDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="product_expdate")
	private LocalDate productExpDate;
	
	
	@Column(name="product_price")
	private double productPrice ;
	
	@Column(name="product_quantity")
	private int productQuantity ;
	
	@Column(length =30)
	private String pmanufacturer;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)//MERGE : NEW n INTERESTING !!!!!
	@JoinColumn(name = "cat_id") // Optional BUT reco , to specify the name of FK col.
	private Category category;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)//MERGE : NEW n INTERESTING !!!!!
	@JoinColumn(name = "v_id") // Optional BUT reco , to specify the name of FK col.
	private Vendor vendor;
	
	@JsonIgnore
	@OneToOne( mappedBy = "vendorProduct",cascade = CascadeType.ALL,orphanRemoval = true)
	private Product product;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)//MERGE : NEW n INTERESTING !!!!!
	@JoinColumn(name = "sub_cat_id") // Optional BUT reco , to specify the name of FK col.
	private SubCategory subcategory;

	private String img;
	
	
	
//	
//	@OneToMany(mappedBy = "product",
//			cascade = CascadeType.ALL,
//			orphanRemoval = true)
//	private java.util.List<Cart> carts = new ArrayList<>();	
//	
//	public void addCart(Cart c) {
//		carts.add(c);
//		c.setProduct(this);
//	}
//	
//	public void removeCart(Cart c) {
//		carts.remove(c);
//		c.setProduct(null);
//	}
	
	
//	@ManyToMany(cascade = CascadeType.PERSIST) // mandatory!
//	@JoinTable(name="order_products",
//	joinColumns = @JoinColumn(name="product_id"),
//	inverseJoinColumns = @JoinColumn(name="order_id")
//	)
//	private Set<Orders> orders=new HashSet<>();
	
}
