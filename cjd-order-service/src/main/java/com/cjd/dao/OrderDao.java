package com.cjd.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cjd.pojo.Order;

public interface OrderDao extends JpaRepository<Order, String>{

}
