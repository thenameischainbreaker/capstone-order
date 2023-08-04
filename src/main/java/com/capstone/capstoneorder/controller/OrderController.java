package com.capstone.capstoneorder.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.capstone.capstoneorder.dto.UserRole;
import com.capstone.capstoneorder.Admin;
import com.capstone.capstoneorder.Order;

import com.capstone.capstoneorder.repository.OrderRepository;
import com.capstone.capstoneorder.service.OrderServiceImpl;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = {"https://domainofchain.s3.us-east-2.amazonaws.com","http://localhost:4200/"})
public class OrderController {
	@Autowired
	OrderServiceImpl service;
	@Value("${gateway-host}")
	private String gatewayHost;
	
	@PostMapping("/post")
	public String postOrder(@RequestHeader("googleBearerToken") String headerValue, @RequestBody List<Order> pending_orders) throws SQLException {
		//get userId from bearer token and put into pending order
		try {
			RestTemplate restTemplate = new RestTemplate(); 
			  String url = "http://" + gatewayHost + "/user/getUserRole";
			  UserRole response = restTemplate.postForObject(url, headerValue, UserRole.class);
			 if(!response.isTokenValid())
				 return "Google bearer token not valid";
			  
			  int userId = response.getUserId();
			 
			 for(Order order : pending_orders) {
				 order.setuserId(userId);
			 }
			
			 
			 
			 if (service.postOrder(pending_orders, userId)) {
				 return "Orders committed successfully.";
			 }
			 else {
				 return "Orders not committed.";
			 }
					
						 
				 
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		} 
		catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	
	
		  
	}
	
	

	@GetMapping("/getById/{id}")
	public Order getById(@PathVariable int id) throws SQLException {
		return service.findById(id);
	}

	//check if admin and logged in
	@GetMapping("/getAllByDate")
	public List<Order> getAllByDate(@RequestHeader("googleBearerToken") String headerValue, @RequestParam(name = "date") Date date) {
		
		
		try {
			    RestTemplate restTemplate = new RestTemplate(); 
			  String url = "http://" + gatewayHost + "/user/getUserRole";
		//	  System.out.println("gatewayHost: " +gatewayHost);
			  UserRole response = restTemplate.postForObject(url, headerValue, UserRole.class); 
			if(response.getRole() == com.capstone.capstoneorder.Admin.FALSE || !response.isTokenValid())
				throw new SecurityException();
			return service.findAllByDate(date);
			/*
			 * try { return repo.getAllByDate(date); } catch (SQLException e) {
			 * e.printStackTrace(); return null; } catch (Exception e) {
			 * e.printStackTrace(); return null; }
			 */
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		}

	}

	@GetMapping("/getAllByUser/{id}")
	public List<Order> getByUser(@PathVariable int id) throws SQLException {
		return service.findAllByUser(id);
		// return repo.getAllByUser(id);
	}
	
	//check if admin and token valid
	@GetMapping("/getAllByProduct/{id}")
	public List<Order> getAllByProduct(@RequestHeader("googleBearerToken") String headerValue, @PathVariable int id) {
		
		try {
			    RestTemplate restTemplate = new RestTemplate(); 
			  String url = "http://" + gatewayHost + "/user/getUserRole";
		//	  System.out.println("gatewayHost: " +gatewayHost);
			  UserRole response = restTemplate.postForObject(url, headerValue, UserRole.class); 
			if(response.getRole() == com.capstone.capstoneorder.Admin.FALSE || !response.isTokenValid())
				throw new SecurityException();
			return service.findAllByProduct(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * @GetMapping("/getByDate") public Order getByDate(@RequestParam(name="date")
	 * Date date) {
	 * 
	 * DateTimeFormatter formatter =
	 * DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); LocalDateTime
	 * localDateTime = LocalDateTime.parse(date, formatter);
	 * 
	 * return service.findByDate(date);
	 * 
	 * 
	 * 
	 * }
	 */
	//check if admin and token valid
	@GetMapping("/getAllByDateBetween")
	public List<Order> getAllByDateBetween(@RequestHeader("googleBearerToken") String headerValue, @RequestParam(name = "date1")  Date date1, @RequestParam(name = "date2") Date date2){
		try {
			RestTemplate restTemplate = new RestTemplate(); 
			  String url = "http://" + gatewayHost + "/user/getUserRole";
		//	  System.out.println("gatewayHost: " +gatewayHost);
			  UserRole response = restTemplate.postForObject(url, headerValue, UserRole.class); 
			if(response.getRole() == com.capstone.capstoneorder.Admin.FALSE || !response.isTokenValid())
				throw new SecurityException();
			return service.findByDuration(date1, date2);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}
	

	



}
