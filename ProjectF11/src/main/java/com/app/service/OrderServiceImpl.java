package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.CartRespDTO;
import com.app.entities.Cart;
import com.app.entities.OrderDetails;
import com.app.entities.Orders;
import com.app.respository.CartRepo;
import com.app.respository.OrderDetailRepo;
import com.app.respository.OrderRepo;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepo repo;
	
	@Autowired
	private CartRepo cartreop;
	
	@Autowired
	private OrderDetailRepo orderdetailsrepo;
	
	
	@Override
	public void addOrder(Orders o) {
		// TODO Auto-generated method stub
		 Orders neworder=repo.save(o);
		// System.out.println("newly creted order have following details:\n"+neworder.toString());
		 System.out.println("newly creted order have following customer details:\n"+neworder.getCustomer());
		// System.out.println("newly creted order have following services/products:\n"+neworder.);
	       List<Cart> cartlist= cartreop.findByCustomer(neworder.getCustomer().getCustomerId());
	       System.out.println(cartlist);
			double amount=0;
	       for(int i=0;i<cartlist.size();i++)
	       {
	    	   //amount=0L;
	       OrderDetails orderdetail=new OrderDetails();
	      
	       orderdetail.setProduct(cartlist.get(i).getProduct());
	       orderdetail.setOrders(neworder);
	    //   orderdetail.setQuantity((Long)cartlist.get(i).getQty());
	       orderdetail.setQuantity(Long.valueOf(cartlist.get(i).getQty()));
	       
	       Double Productprice= cartlist.get(i).getProduct().getProductPrice();
	       int Qty=cartlist.get(i).getQty();
	       orderdetail.setAmount(Productprice*Qty);
	       amount+= orderdetail.getAmount();
	       orderdetailsrepo.save(orderdetail);
	       }
//	       Orders neworder1= repo.findByOrderId(neworder.getOrderId());
//	       neworder1.setTotal(amount);
         neworder.setTotal(amount);
	       repo.save(neworder);
	       cartreop.deleteAll(cartlist);
	       
	       
	       
	       
	   
		
	}
//	@Override
//	public void addOrder(Orders o) {
//		// TODO Auto-generated method stub
//		repo.save(o);
//		
//	}
	@Override
	public String deleteOrderId(Long orderId) {
		repo.deleteByOrderId(orderId);
		return "Order deleted!!!!!!!!";
	}

}
