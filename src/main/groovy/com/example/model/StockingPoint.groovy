package com.example.model

import io.micronaut.core.annotation.NonNull

import javax.persistence.Cacheable
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToOne
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
