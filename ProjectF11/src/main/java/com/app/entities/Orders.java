package com.app.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
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
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity 
@Table(name = "Orders") 
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "customer")
//@AttributeOverrides({
//    @AttributeOverride(name = "id", column = @Column(name = "oid"))
//})
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "oid")
	private Long orderId;
	
	private LocalDate odate;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)//MERGE : NEW n INTERESTING !!!!!
	@JoinColumn(name = "c_id") // Optional BUT reco , to specify the name of FK col.
	private Customer customer;
	
	private double total;
	//--------------------------------------------------------------------------------------------
	@OneToMany(mappedBy = "orders",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
	private List<OrderDetails> listOd= new ArrayList<OrderDetails>();
	
	public void addOrderDetails(OrderDetails od) {
		listOd.add(od);
		od.setOrders(this);
	}
	
	public void removeOrderDetails(OrderDetails od) {
		listOd.remove(od);
		od.setOrders(null);
	}

}