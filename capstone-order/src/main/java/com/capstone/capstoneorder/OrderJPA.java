package com.capstone.capstoneorder;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface OrderJPA extends JpaRepository<Order, Integer> {
	public List<Order> findAllByDate(Date o_date);
	public Order findByDate(Date o_date);
}
