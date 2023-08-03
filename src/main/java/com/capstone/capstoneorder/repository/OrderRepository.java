package com.capstone.capstoneorder.repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.capstoneorder.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	public List<Order> findAllByDate(Date date);
	public Order findByDate(Date date);
	public List<Order> findByUserId(int id);
	public List<Order> findByProductId(int id);
	public List<Order> findByDateBetween(Date date1, Date date2);
	
}
