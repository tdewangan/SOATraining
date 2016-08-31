package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableDiscoveryClient
//Change loadbalancing algorithm to ping
@RibbonClient(configuration = CustomLoadBalancerConfiguration.class, name="lb1")
public class OrderLoadBalancerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderLoadBalancerApplication.class, args);
	}
}
