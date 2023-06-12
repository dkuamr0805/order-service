package com.ecomapp.orderservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecomapp.orderservice.config.AppConfigProperties;
import com.ecomapp.orderservice.dto.LineItemRequestDto;
import com.ecomapp.orderservice.dto.OrderRequestDto;
import com.ecomapp.orderservice.dto.OrderResponseDto;
import com.ecomapp.orderservice.dto.ReserveInventoryReqDto;
import com.ecomapp.orderservice.dto.ReserveInventoryResDto;
import com.ecomapp.orderservice.entity.LineItemEntity;
import com.ecomapp.orderservice.entity.OrderEntity;
import com.ecomapp.orderservice.repository.OrderRepository;
import com.ecomapp.orderservice.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private AppConfigProperties appConfigProperties;

	@Override
	public OrderResponseDto createOrder(OrderRequestDto requestDto) {
		// Reserve inventory
		ReserveInventoryReqDto reserveInventoryReqDto = ReserveInventoryReqDto.builder()
				.lineItems(requestDto.getLineItems()).build();
		String inventoryReservationUrl = appConfigProperties.getInventoryServiceUrl() + "/api/v1/inventory/reserve";
		
		restTemplate.postForObject(inventoryReservationUrl, reserveInventoryReqDto, ReserveInventoryResDto.class);

		//Create Order if inventory is reserved
		OrderEntity orderEntity = convertOrderReqDtoToEntity(requestDto);

		orderRepository.save(orderEntity);
		
		return OrderResponseDto.builder()
				.orderId(orderEntity.getId())
				.message("Order created successfully")
				.build();
	}

	private OrderEntity convertOrderReqDtoToEntity(OrderRequestDto requestDto) {
		List<LineItemEntity> lineItems = new ArrayList<>();

		OrderEntity orderEntity = OrderEntity.builder()
				.userId(requestDto.getUserId())
				.lineItems(lineItems)
				.build();
		
		for (LineItemRequestDto dto : requestDto.getLineItems()) {
			lineItems.add(LineItemEntity.builder()
					.productCode(dto.getProductCode())
					.quantity(dto.getQuantity())
					.order(orderEntity)
					.build());
		}

		return orderEntity;
	}

}
