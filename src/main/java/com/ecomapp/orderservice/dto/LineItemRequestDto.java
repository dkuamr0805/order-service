package com.ecomapp.orderservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LineItemRequestDto {
	private String productCode;
	private Integer quantity;
}
