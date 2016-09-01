package com.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Order;
import com.example.repository.OrdersRepository;


@RestController
@ConfigurationProperties(prefix="newConfig")
@RefreshScope
public class OrderAPI {
	private String data;
	@Autowired	
	private OrdersRepository manufacturerRepository;

	@RequestMapping(value = "/api/order", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Order> add(@RequestBody Order manufacturer) {
		manufacturerRepository.save(manufacturer);
		return new ResponseEntity<Order>(manufacturer, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/api/order", method = RequestMethod.GET)
	public ResponseEntity<List<Order>> findAll() {
		List<Order> orders=new ArrayList<Order>();
		orders.add(new Order(1, data, new Date(), true));
		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/order", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Order> updatePathParam(@RequestParam("id") Integer id, @RequestBody Order order) {
		Order manufacturerOne = manufacturerRepository.findOne(id);
		if (manufacturerOne != null) {
			manufacturerOne.setId(id);
			manufacturerOne.setActive(order.getActive());
			manufacturerOne.setOrderDate(order.getOrderDate());
			manufacturerOne.setName(order.getName());
			manufacturerRepository.save(order);
		}
		return new ResponseEntity<Order>(order, HttpStatus.CREATED);
	}
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
