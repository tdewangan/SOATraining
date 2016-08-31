package com.example;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.model.Order;

@RestController
public class ConsumerService {
	@Autowired
	private DiscoveryClient client;

	@RequestMapping("/orders")
	public List<Order> getData() {
		List<ServiceInstance> list = client.getInstances("order-microservice");
		if (!list.isEmpty()) {
			URI uri = list.get(0).getUri();
			if (uri != null) {
				return (new RestTemplate()).getForObject(uri, List.class);
			}
		}
		return null;
	}

	@RequestMapping(value = "/ordersPost", method = RequestMethod.POST)
	public ResponseEntity<String> postData() {
		List<ServiceInstance> list = client.getInstances("order-microservice");
		if (!list.isEmpty()) {
			URI uri = list.get(0).getUri();
			if (uri != null) {
				Order order2 = new Order(2, "Order2", new Date(), true);
				Order order3 = new Order(3, "Order3", new Date(), true);
				List<Order> orderList = new ArrayList<>();
				orderList.add(order2);
				orderList.add(order3);
				return (new RestTemplate()).postForEntity(uri.toString().concat("/api/order"), order3, String.class);
			}
		}
		return null;
	}
}
