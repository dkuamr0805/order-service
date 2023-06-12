package com.ecomapp.orderservice.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderRequestDto {
	private Long userId;
	private List<LineItemRequestDto> lineItems;
}
