package com.example.repository

import com.example.DTO.ProductDTO
import com.example.model.Product
import groovy.transform.CompileStatic
import io.micronaut.context.annotation.Executable
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.Join
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.model.Pageable
import io.micronaut.data.model.Sort
import io.micronaut.data.repository.CrudRepository
import jakarta.inject.Inject

import javax.persistence.EntityManager

@Repository
@CompileStatic
abstract class ProductRepository implements CrudRepository<Product, Integer> {
	
	@Inject
	private final EntityManager entityManager
	
	
	/**
	 * Count query
	 * @param id
	 * @return
	 */
	@Executable
	@Join(value = "stockingPoint.period", type = Join.Type.LEFT)
	abstract Integer countByStockingPointPeriodId(Integer id)
	
	/**
	 * Dynamic query with foreign table
	 * @param id
	 * @return
	 */
	@Executable
	@Join(value = "stockingPoint", type = Join.Type.FETCH)
	abstract Optional<Product> findById(Integer id)
	
	/**
	 * Dynamic query with foreign criteria
	 * @param id
	 * @return
	 */
	@Executable
	@Join(value = "stockingPoint", type = Join.Type.FETCH)
	abstract List<Product> findByStockingPointCapacityGreaterThan(Integer capacity)
	
	/**
	 * Dynamic query with nested search
	 * @param id
	 * @return
	 */
	@Executable
	@Join(value = "stockingPoint.period", type = Join.Type.INNER)
	abstract List<Product> findByStockingPointPeriodId(Integer id)
	
	/**
	 * DTO with foreign entity
	 * @param stockingPointId
	 * @return
	 */
	@Executable
	@Join(value = "stockingPoint", type = Join.Type.LEFT)
	abstract List<ProductDTO> findByStockingPointId(Integer stockingPointId)
	
	/**
	 * HQL / JPA Query Language
	 * @param name
	 * @return
	 */
	@Executable
	@Query("SELECT p FROM Product p JOIN FETCH p.stockingPoint where p.name = :name")
	abstract List<Product> findByJPAQueryLanguage(String name)
	
	/**
	 * Native SQL
	 * @param name
	 * @return
	 */
	@Executable
	@Query(value = """SELECT * FROM Product
						WHERE name = :name""", nativeQuery = true)
	abstract List<Product> findBySQL(String name)
	
	/**
	 * JPA-QL with DTO
	 * Always use 'as {propertyName}' of the DTO property name in SELECT
	 * @param stockingPointId
	 * @return
	 */
	@Executable
	@Query("""SELECT p.name AS name,
					 sp AS stockingPoint
				FROM Product p
				JOIN p.stockingPoint sp ON p.stockingPoint.id = :stockingPointId""")
	abstract List<ProductDTO> findByJPAQueryLanguageWithDTO(Integer stockingPointId)
	
	/**
	 * Dynamic query with order by
	 * @param id
	 * @return
	 */
	@Executable
	@Join(value = "stockingPoint", type = Join.Type.FETCH)
	abstract List<Product> listOrderByName()
	
	/**
	 * Dynamic query with foreign table order by
	 * @param id
	 * @return
	 */
	@Executable
	@Join(value = "stockingPoint", type = Join.Type.FETCH)
	abstract List<Product> list(Sort sort)
	
	/**
	 * Dynamic query with pagination
	 * @param id
	 * @return
	 */
	@Executable
	@Join(value = "stockingPoint", type = Join.Type.FETCH)
	abstract List<Product> list(Pageable pageable)
	
	/**
	 * Update/create an object
	 * @param product
	 * @return
	 */
	@Executable
	abstract Product save(Product product)
	
	/**
	 * Explicitly update an object
	 * @param product
	 * @return
	 */
	@Executable
	abstract Product update(Product product)
	
	/**
	 * Update by ID
	 * @param id
	 * @param name
	 * @return
	 */
	@Executable
	abstract Product update(@Id Integer id, String name)
	
	/**
	 * Update by name
	 * @param name
	 */
	@Executable
	abstract void updateByName(String name)
	
	/**
	 * Explicitly insert an object
	 * @param product
	 * @return
	 */
	@Executable
	abstract Product insert(Product product)
	
	/**
	 * 'Persist'
	 * @param name
	 * @return
	 */
	@Executable
	abstract Product persist(String name, Boolean enabled)
	
	/**
	 * Find object exists
	 * @param id
	 * @return
	 */
	@Executable
	abstract Boolean exists(Integer id)
	
	/**
	 * Note: DeleteAll does not cascade!
	 * Use delete() on individual items if requires cascading.
	 */
	@Executable
	abstract void deleteAll()
	
	/**
	 * Delete by a certain property
	 * Synonyms: delete/remove/erase/eliminate
	 * @param name
	 */
	@Executable
	abstract void delete(String name)
}
