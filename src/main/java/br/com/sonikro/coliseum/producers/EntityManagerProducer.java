package br.com.sonikro.coliseum.producers;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.jboss.logging.Logger;


public class EntityManagerProducer {
	private static Logger logger = Logger.getLogger(EntityManagerProducer.class);
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("coliseum");
	
	@Produces @RequestScoped
	public EntityManager getEntityManager()
	{
		logger.info("CDI Producing Entity Manager");
		return emf.createEntityManager();
	}
	
	public void disposeEntityManager(@Disposes EntityManager manager)
	{
		manager.close();
	}
}
