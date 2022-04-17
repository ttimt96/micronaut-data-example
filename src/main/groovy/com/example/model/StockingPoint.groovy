package com.example.model

import io.micronaut.core.annotation.NonNull

import javax.persistence.*
import javax.validation.constraints.Size

@Entity
@Cacheable
class StockingPoint {
	@Id
	@GeneratedValue
	Integer id
	
	@Size(max = 15)
	@NonNull
	String name
	
	Integer capacity
	
	@OneToOne(fetch = FetchType.LAZY)
	Period period
}
