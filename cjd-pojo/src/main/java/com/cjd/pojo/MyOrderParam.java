package com.cjd.pojo;

import java.io.Serializable;
import java.util.List;

public class MyOrderParam implements Serializable{

	private int paymentType;
	private String payment;
	private List<OrderItem> orderItems;
	private OrderShipping orderShipping;
	
	public int getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(int paymentType) {
		this.paymentType = paymentType;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public OrderShipping getOrderShipping() {
		return orderShipping;
	}
	public void setOrderShipping(OrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}
	@Override
	public String toString() {
		return "MyOrderParam [paymentType=" + paymentType + ", payment=" + payment + ", orderItems=" + orderItems
				+ ", orderShipping=" + orderShipping + "]";
	}
	
}
