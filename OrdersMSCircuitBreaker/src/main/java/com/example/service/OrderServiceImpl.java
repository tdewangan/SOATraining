package com.example.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.OrdersClient;
import com.example.model.Order;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrdersClient orderClient;
	
	@Override
	@HystrixCommand(fallbackMethod="findAllFallback")
	public List<Order> findAll() {
		return orderClient.findAll();
	}
	
	public List<Order> findAllFallback() {
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1, "Fallback order", new Date(), true));
		return orders;
	}

}
