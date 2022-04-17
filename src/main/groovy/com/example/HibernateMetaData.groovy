package com.example


import jakarta.inject.Singleton
import org.hibernate.SessionFactory
import org.hibernate.boot.Metadata
import org.hibernate.engine.spi.SessionFactoryImplementor
import org.hibernate.integrator.spi.Integrator
import org.hibernate.integrator.spi.IntegratorService
import org.hibernate.service.ServiceRegistry

@Singleton
class HibernateMetaData {
	
	SessionFactory sessionFactory
	
	final Metadata metadata
	
	HibernateMetaData(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory
		this.metadata = getMetadataInternal()
	}
	
	private Metadata getMetadataInternal() {
		Metadata metadata = null
		
		ServiceRegistry bootstrapServiceRegistry = ((SessionFactoryImplementor) sessionFactory).getServiceRegistry().getParentServiceRegistry()
		Iterable<Integrator> integrators = bootstrapServiceRegistry.getService(IntegratorService.class).getIntegrators()
		
		for (Integrator integrator : integrators) {
			if (integrator instanceof MetadataIntegrator) {
				metadata = ((MetadataIntegrator) integrator).getMetadata();
			}
		}
		
		return metadata
	}
}
