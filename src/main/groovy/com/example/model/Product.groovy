package com.example.model


import io.micronaut.data.annotation.Where

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Cacheable
@Where("@.enabled = true")
class Product {
	@Id
	@GeneratedValue
	Integer id
	
	@NotNull
	String name
	
	@ManyToOne(fetch = FetchType.LAZY)
	StockingPoint stockingPoint
	
	Boolean enabled
}
