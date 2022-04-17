package com.example.Controller


import groovy.util.logging.Slf4j
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import jakarta.inject.Inject
import org.hibernate.Session

@Slf4j
@Controller("/")
class TestController {
	
	@Inject
	Session session
	
	@Get
	void get() {
		log.info("here")
	}
}
