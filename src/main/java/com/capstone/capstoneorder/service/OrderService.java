package com.capstone.capstoneorder.service;

import java.sql.Date;

import java.util.List;

import com.capstone.capstoneorder.Order;



public interface OrderService {

	
	Order findByDate(Date date);
	
	List<Order> findAllByDate(Date date);
	
	List<Order> findAllByUser(int id);
	
	List<Order> findAllByProduct(int id);
	
	List<Order> findByDuration(Date date1, Date date2);
	
	Order findById(int id);
	
	boolean postOrder(List<Order> pending_orders, int userId);
}
