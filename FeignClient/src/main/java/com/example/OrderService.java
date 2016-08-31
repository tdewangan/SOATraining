package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Order;

@RestController
public class OrderService {

	@Autowired
	private OrderClient orderClient;
	
	@RequestMapping("/")
	@ResponseBody
	public List<Order> findAll() {
		return orderClient.findAll();
	}
	
	@RequestMapping("/{id}")
	@ResponseBody
	public Order findOne(@PathVariable Integer id) {
		List<Order> orders = orderClient.findAll();
		for(Order order: orders){
			if(order.getId() == id){
				return order;
			}
		}
		return null;
	}
}
