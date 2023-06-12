package com.ecomapp.orderservice.service;

import com.ecomapp.orderservice.dto.OrderRequestDto;
import com.ecomapp.orderservice.dto.OrderResponseDto;

public interface OrderService {

	public OrderResponseDto createOrder(OrderRequestDto requestDto);

}
