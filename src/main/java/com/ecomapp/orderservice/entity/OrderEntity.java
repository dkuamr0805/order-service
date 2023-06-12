package com.ecomapp.orderservice.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long userId;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<LineItemEntity> lineItems;

}
