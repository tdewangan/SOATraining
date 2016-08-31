package com.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Order;

@Repository
public interface OrdersRepository extends MongoRepository<Order, Integer> {

}
