package com.capstone.capstoneorder.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.capstone.capstoneorder.Order;
import com.capstone.capstoneorder.dto.Discount;
import com.capstone.capstoneorder.dto.Product;
import com.capstone.capstoneorder.dto.Stock;
import com.capstone.capstoneorder.dto.UserRole;
import com.capstone.capstoneorder.repository.OrderRepository;


@Service
public class OrderServiceImpl implements OrderService {
@Autowired
OrderRepository jpa;
@Value("${gateway-host}")
private String gatewayHost;
	
	@Override
	public Order findByDate(Date date) {
		// TODO Auto-generated method stub
		return jpa.findByDate(date);
	}

	@Override
	public List<Order> findAllByDate(Date date) {
		// TODO Auto-generated method stub
		return jpa.findAllByDate(date);
	}

	@Override
	public List<Order> findAllByUser(int id) {
		// TODO Auto-generated method stub
		return jpa.findByUserId(id);
	}

	@Override
	public List<Order> findAllByProduct(int id) {
		// TODO Auto-generated method stub
		return jpa.findByProductId(id);
	}

	@Override
	public List<Order> findByDuration(Date date1, Date date2) {
		// TODO Auto-generated method stub
		return jpa.findByDateBetween(date1, date2);
	}

	@Override
	public Order findById(int id) {
		// TODO Auto-generated method stub
		return jpa.findById(id).get();
	}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean postOrder(List<Order> pending_orders, int userId) {
		
		//getBalance from UserService
		  RestTemplate restTemplate = new RestTemplate(); 
		  String url = "http://" + gatewayHost + "/user/getBalanceById?id=" + userId;
		  Integer user_balance = restTemplate.getForObject(url, Integer.class);
		
		
		  //check if userIds of discount in pending order match discount
		  
		  
		  //get all prices for all productIds and calculate Total Price
	/*	  List<Integer> pending_orders_productIds = new ArrayList()<Integer>();
		  for(Order order : pending_orders) {
			  pending_orders_productIds.add(order.getProductId());
		  }
				
		  
		  String url2 = "http://" + gatewayHost + "/product/getAllByIds";
		  List<Product> product_list = restTemplate.exchange(
			        url2,
			        HttpMethod.POST,
			        new HttpEntity<>(pending_orders_productIds),
			        new ParameterizedTypeReference<List<Product>>() {}).getBody();

		 
				  Map<Integer, Product> productMap = product_list.stream()
				      .collect(Collectors.toMap(Product::getP_id, Function.identity()));

		//get All Discounts by User
				  String url3 = "http://" + gatewayHost + "/discount/findAllByUserId?userId=" + userId;
				  List<Discount> discount_list = restTemplate.exchange(
					        url3,
					        HttpMethod.GET,
					        new HttpEntity<>(),
					        new ParameterizedTypeReference<List<Discount>>() {}).getBody();	  
	
			
				  
				  
				 
				  Map<Integer, Discount> discountMap = discount_list.stream()
				      .collect(Collectors.toMap(Discount::getD_id, Function.identity()));
				  
				  
				  
			if(pending_orders.length!= product_list.size())
			  throw new NoSuchElementException();*/
		  RestTemplate restTemplateUrl = new RestTemplate(); 
		  final String urlDiscount = "http://" + gatewayHost + "/discount/getById/";
		  
		  RestTemplate restTemplateProduct = new RestTemplate(); 
		 final  String urlProduct = "http://" + gatewayHost + "/product/getById/";
		
		  RestTemplate restTemplateStock = new RestTemplate(); 
		  final String urlStock = "http://" + gatewayHost + "/stock/get/";
		  //are quantities in stock
		  RestTemplate restTemplateStockSub = new RestTemplate(); 
		  final String urlStockSub = "http://" + gatewayHost + "/stock/sub/";
		
			
			double total_price = 0;
		  for(Order order : pending_orders) {
			
			
			   
			  Discount discount = restTemplate.getForObject(urlDiscount + order.getD_id(), Discount.class);
			System.out.println(urlDiscount + order.getD_id());
			
			  Product product = restTemplate.getForObject(urlProduct + order.getProductId(), Product.class);
			  total_price = total_price + product.getP_price() * order.getQuantity() * (1 - discount.getDiscount());
			  
			  //all other data to each order
			  order.setuserId(userId);
			  order.setO_id(0);
			  order.setDate(new java.sql.Date(new java.util.Date().getTime()));
			  order.setO_price(product.getP_price());
			  
			  
			  //save the order
		  }
		  //update all stock
	if (user_balance>=total_price) {
		for(Order order : pending_orders) {
			 
			 int stock =  restTemplate.getForObject(urlStock + order.getProductId(), Integer.class);
			  if(stock < order.getQuantity())
				  return false;
		}
		for(Order order : pending_orders) {
			Stock stock = new Stock (order.getProductId(), order.getQuantity());
			restTemplate.put(urlStockSub, stock);
		}
		System.out.println(pending_orders);
		
		jpa.saveAll(pending_orders);
	    return true;	
	}
	else 
	{
		return false;
		 
			 
    }
		
		  
		  //if balance is >= totalPrice
		
		
		//create loop through user orderList and saveNewOrders, u_id, p_id, o_quantity, o_date, o_price, d_id, delete discount
		
		//debit price
	}
	
	
	
	

}
