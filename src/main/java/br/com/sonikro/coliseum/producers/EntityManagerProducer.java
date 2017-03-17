package br.com.sonikro.coliseum.producers;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

import org.jboss.logging.Logger;

@ApplicationScoped
public class EntityManagerProducer {
	private static Logger logger = Logger.getLogger(EntityManagerProducer.class);
	
	private EntityManagerFactory emf;
	
	public  EntityManagerProducer() {
		logger.info("CDI Producing EntityManagerFactory");
		emf = Persistence.createEntityManagerFactory("coliseum");
	}
	
	@Produces @RequestScoped
	public EntityManager getEntityManager()
	{
		logger.info("CDI Producing Entity Manager");
		EntityManager manager = emf.createEntityManager();
		return manager;
	}
	
	public void disposeEntityManager(@Disposes EntityManager manager)
	{
		logger.info("Disposing Entity Manager");
		manager.close();
	}
}
