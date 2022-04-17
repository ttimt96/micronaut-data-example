package com.example.DTO


import com.example.model.StockingPoint
import io.micronaut.core.annotation.Introspected
import io.micronaut.core.annotation.Nullable

@Introspected
class ProductDTO {
	
	String name
	
	@Nullable
	StockingPoint stockingPoint
}
