package com.cjd.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cjd.pojo.OrderItem;

public interface OrderItemDao extends JpaRepository<OrderItem, String>{

}
