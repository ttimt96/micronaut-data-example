package com.example.model

import javax.persistence.Cacheable
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.NotNull

@Entity
@Cacheable
class Period {
	
	@Id
	@GeneratedValue
	Integer id
	
	@NotNull
	String name
}
