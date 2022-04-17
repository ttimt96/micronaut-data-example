package com.example.DTO

import com.example.model.Product
import com.example.model.StockingPoint
import groovy.transform.MapConstructor
import io.micronaut.core.annotation.Introspected
import io.micronaut.core.annotation.Nullable

@Introspected
class ProductDTO {
	
	String name
	
	@Nullable
	StockingPoint stockingPoint
}
