package com.ecomapp.orderservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponseDto {

	private Long orderId;

	private String message;

}
