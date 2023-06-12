package com.ecomapp.orderservice.aop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ecomapp.orderservice.dto.OrderResponseDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<OrderResponseDto> handleInventoryExceptions(HttpClientErrorException ex) throws Exception {
		log.error("Exception {}", ex);
		OrderResponseDto orderResponseDto = OrderResponseDto.builder()
				.message("Insufficient stock")
				.orderId(null)
				.build();
		return new ResponseEntity<OrderResponseDto>(orderResponseDto, ex.getStatusCode());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<OrderResponseDto> handleExceptions(Exception ex) {
		return new ResponseEntity<>(getResponseDto(ex), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private OrderResponseDto getResponseDto(Exception ex) {
		log.error("Exception {}", ex);
		return OrderResponseDto.builder()
				.message(ex.getMessage())
				.orderId(null)
				.build();
	}
}
