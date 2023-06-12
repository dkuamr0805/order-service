package com.ecomapp.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomapp.orderservice.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
