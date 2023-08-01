package com.capstone.capstoneorder;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class OrderDAORepository implements OrderDAO {
	@Autowired
	JdbcTemplate jt;
	@Autowired
	DataSource ds;
	@Autowired
	OrderJPA jpa;
	@Override
	public boolean postOrder(Order order) throws SQLException {
		// TODO Auto-generated method stub
		String query = "insert into orders values (?,?,?,?,?,?,?)";
		int i = jt.update(query, new Object[] {order.getO_id(), order.getU_id(), order.getP_id(), order.getQuantity(),order.getDate(), order.getO_price(), order.getD_id()});
		if(i>0)
			return true;
		else
			return false;
	}

	@Override
	public Order getById(int o_id) throws SQLException {
		Connection con = ds.getConnection();
		String query = "select * from orders where o_id = " + o_id;
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		Order o = new Order();
		while(rs.next())
		{
			o.setO_id(rs.getInt(1));
			o.setU_id(rs.getInt(2));
			o.setP_id(rs.getInt(3));
			o.setQuantity(rs.getInt(4));
			o.setDate(rs.getDate(5));
			o.setO_price(rs.getDouble(6));
			o.setD_id(rs.getInt(7));
		}
		return o;
	}

	@Override
	public List<Order> getAllByDate(Date o_date) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = ds.getConnection();
		String query = "select * from orders where o_date = '" + o_date.toString() + "'";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		ArrayList<Order> orders = new ArrayList<Order>();
		Order o = new Order();
		while(rs.next())
		{
			o.setO_id(rs.getInt(1));
			o.setU_id(rs.getInt(2));
			o.setP_id(rs.getInt(3));
			o.setQuantity(rs.getInt(4));
			o.setDate(rs.getDate(5));
			o.setO_price(rs.getDouble(6));
			o.setD_id(rs.getInt(7));
			orders.add(o);
		}
		return orders;
		//return jpa.findAllByDate(o_date);
	}

	@Override
	public List<Order> getAllByUser(int u_id) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = ds.getConnection();
		String query = "select * from orders where u_id = " + u_id;
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		ArrayList<Order> orders = new ArrayList<Order>();
		while(rs.next())
		{
			Order o = new Order();
			o.setO_id(rs.getInt(1));
			o.setU_id(rs.getInt(2));
			o.setP_id(rs.getInt(3));
			o.setQuantity(rs.getInt(4));
			o.setDate(rs.getDate(5));
			o.setO_price(rs.getDouble(6));
			o.setD_id(rs.getInt(7));
			orders.add(o);
		}
		return orders;
	}

	@Override
	public Order getByDate(Date date) throws SQLException {
		// TODO Auto-generated method stub
		return jpa.findByDate(date);
	}

}
