package com.ecomapp.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomapp.orderservice.entity.LineItemEntity;

public interface LineItemRepository extends JpaRepository<LineItemEntity, Long> {

}
