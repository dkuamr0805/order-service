package com.ecomapp.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomapp.orderservice.dto.OrderRequestDto;
import com.ecomapp.orderservice.dto.OrderResponseDto;
import com.ecomapp.orderservice.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/orders")
public class OrdersController {

	@Autowired
	private OrderService orderService;

	@PostMapping
	public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto requestDto) {
		log.info("Request body {}", requestDto);

		OrderResponseDto orderResponseDto = orderService.createOrder(requestDto);

		return new ResponseEntity<OrderResponseDto>(orderResponseDto, HttpStatus.CREATED);
	}

}
