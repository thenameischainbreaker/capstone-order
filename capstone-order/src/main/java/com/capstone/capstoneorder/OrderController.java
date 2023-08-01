package com.capstone.capstoneorder;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	OrderDAO repo;
	
	@PostMapping("/post")
	public String postOrder(@RequestBody Order order) throws SQLException
	{
		if(repo.postOrder(order))
			return "Order Posted";
		else
			return "Order Not Posted";
	}
	
	@GetMapping("/idGet/{id}")
	public Order getById(@PathVariable int id) throws SQLException
	{
		return repo.getById(id);
	}
	
	@GetMapping("/dateAllGet")
	public List<Order> getAllByDate(@RequestBody Order order) throws SQLException
	{
		return repo.getAllByDate(order.getDate());
	}
	
	@GetMapping("/userAllGet/{id}")
	public List<Order> getByUser(@PathVariable int id) throws SQLException
	{
		return repo.getAllByUser(id);
	}
	
	@GetMapping("/dateGet")
	public Order getByDate(@RequestBody Order order) throws SQLException
	{
		return repo.getByDate(order.getDate());
	}
}
