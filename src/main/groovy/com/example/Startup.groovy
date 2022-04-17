package com.example

import com.example.DTO.ProductDTO
import com.example.model.Product
import com.example.repository.ProductRepository
import groovy.util.logging.Slf4j
import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.context.event.StartupEvent
import io.micronaut.data.model.Pageable
import io.micronaut.data.model.Sort
import jakarta.inject.Inject
import org.hibernate.SessionFactory
import org.hibernate.tool.hbm2ddl.SchemaValidator
import org.hibernate.tool.schema.spi.SchemaManagementException

@Slf4j
class Startup implements ApplicationEventListener<StartupEvent> {
	
	@Inject
	ProductRepository productRepository
	
	@Inject
	SessionFactory sessionFactory
	
	@Override
	void onApplicationEvent(StartupEvent event) {
		try {
			log.info("Starting schema validation")
			new SchemaValidator().validate(new HibernateMetaData(sessionFactory).getMetadata())
			log.info("Schema validation is successful")
		}
		catch (ex) {
			// Do something
			throw new SchemaManagementException(ex.message)
		}
		
		countQuery()
		findByIDQuery()
		findByForeignCriteria()
		findByNestedForeignCriteria()
		findByDTOWithForeignTable()
		findByJPAQL()
		findByNativeSQL()
		findByJPAQLWithDTO()
		listByOrder()
		listByForeignOrder()
		listPagination()
		save()
		update()
		updateByID()
		updateByColumn()
		insert()
		persist()
		exists()
		deleteAll()
		delete()
		
	}
	
	void countQuery() {
		Integer count = productRepository.countByStockingPointPeriodId(2)
		
		log.info("####################################### Count")
		log.info("Count: ${count.toString()}")
	}
	
	void findByIDQuery() {
		Optional<Product> opt = productRepository.findById(5)
		
		log.info("####################################### Find by ID")
		Product p = opt.get()
		log.info(p.id.toString())
		log.info(p.name)
		log.info(p.stockingPoint.name)
	}
	
	void findByForeignCriteria() {
		List<Product> product = productRepository.findByStockingPointCapacityGreaterThan(1)
		
		log.info("####################################### Find by foreign criteria")
		product.forEach(it -> {
			log.info("Product ID: ${it.id.toString()}")
			log.info("Product name: $it.name")
			log.info("SP name: $it.stockingPoint.name")
			log.info("SP capacity: ${it.stockingPoint.capacity.toString()}")
		})
	}
	
	void findByNestedForeignCriteria() {
		List<Product> product = productRepository.findByStockingPointPeriodId(2)
		
		log.info("####################################### Dynamic query with nested search")
		product.forEach(it -> {
			log.info(it.id.toString())
			log.info(it.name)
			log.info(it.stockingPoint.name)
			log.info(it.stockingPoint.period.name)
		})
		
	}
	
	void findByDTOWithForeignTable() {
		List<ProductDTO> productDTO = productRepository.findByStockingPointId(2)
		
		log.info("####################################### DTO")
		productDTO.forEach(it -> {
			log.info(it.name)
			log.info(it.stockingPoint?.id?.toString())
			log.info(it.stockingPoint?.name)
		})
	}
	
	void findByJPAQL() {
		List<Product> product = productRepository.findByJPAQueryLanguage("zxc")
		
		log.info("####################################### JPA-QL")
		product.forEach(it -> {
			log.info(it.id.toString())
			log.info(it.name)
			log.info(it.stockingPoint?.id?.toString())
			log.info(it.stockingPoint?.name)
		})
	}
	
	void findByNativeSQL() {
		List<Product> product = productRepository.findBySQL("zxc")
		
		log.info("####################################### SQL where foreign not available")
		product.forEach(it -> {
			log.info(it.id.toString())
			log.info(it.name)
		})
		
	}
	
	void findByJPAQLWithDTO() {
		List<ProductDTO> productDTO = productRepository.findByJPAQueryLanguageWithDTO(2)
		
		log.info("####################################### JPA-QL with DTO")
		productDTO.forEach(it -> {
			log.info(it?.name)
			log.info(it?.stockingPoint?.id?.toString())
			log.info(it?.stockingPoint?.name)
		})
	}
	
	void listByOrder() {
		List<Product> products = productRepository.listOrderByName()
		
		log.info("####################################### List & Order By")
		products.forEach(it -> {
			log.info("Product name: ${it?.name}")
			log.info("SP ID: ${it?.stockingPoint?.id?.toString()}")
			log.info("SP name: ${it?.stockingPoint?.name}")
		})
	}
	
	void listByForeignOrder() {
		Sort sort = Sort.of(new Sort.Order("stockingPoint.name"))
		List<Product> products = productRepository.list(sort)
		
		log.info("####################################### List & Order By Foreign Column")
		products.forEach(it -> {
			log.info("Product name: ${it?.name}")
			log.info("SP ID: ${it?.stockingPoint?.id?.toString()}")
			log.info("SP name: ${it?.stockingPoint?.name}")
		})
	}
	
	void listPagination() {
		Sort sort = Sort.of(new Sort.Order("stockingPoint.name"))
		// First param - page number (starts from 0)
		// Second param - number of data per page
		List<Product> products = productRepository.list(Pageable.from(1, 3))
		
		log.info("####################################### List with Pagination")
		products.forEach(it -> {
			log.info("Product name: ${it?.name}")
			log.info("SP ID: ${it?.stockingPoint?.id?.toString()}")
			log.info("SP name: ${it?.stockingPoint?.name}")
		})
	}
	
	void save() {
	
	}
	
	void update() {
	
	}
	
	void updateByID() {
	
	}
	
	void updateByColumn() {
	
	}
	
	void insert() {
	
	}
	
	void persist() {
	
	}
	
	void exists() {
	
	}
	
	void deleteAll() {
	
	}
	
	void delete() {
	
	}
}
