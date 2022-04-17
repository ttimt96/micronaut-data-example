package com.example.Controller

import com.example.HibernateMetaData
import groovy.util.logging.Slf4j
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import jakarta.inject.Inject
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.tool.hbm2ddl.SchemaValidator

import javax.persistence.EntityManager
import javax.transaction.Transactional

@Slf4j
@Controller("/")
class TestController {
	
	@Inject
	Session session
	
	@Inject
	EntityManager entityManager
	
	@Inject
	SessionFactory sessionFactory
	
	@Get
	void get() {
		log.info("here")
		def b = entityManager.unwrap(SessionFactory)
		def a = session.getSessionFactory()
		new SchemaValidator().validate(new HibernateMetaData(session.getSessionFactory()).getMetadata())
		
	}
}
