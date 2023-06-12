package com.ecomapp.orderservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ConfigurationProperties
public class AppConfigProperties {

	private String inventoryServiceUrl;
	private String hello;
}
