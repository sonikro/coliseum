package br.com.sonikro.coliseum.producers;

import java.lang.reflect.ParameterizedType;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.jboss.logging.Logger;

import br.com.sonikro.coliseum.dao.GenericDAO;

public class GenericDAOProducer {
	private static Logger logger = Logger.getLogger(GenericDAOProducer.class);
	
	@Inject
	private Instance<EntityManager> cdiInstanceEM;
	@Produces @Named("genericDAO")
	public <T> GenericDAO<T> produceGenericDAO(InjectionPoint injectionPoint)
	{
		logger.info("CDI Producing GenericDAO");
		ParameterizedType type = (ParameterizedType) injectionPoint.getType();
		Class objectClass = (Class) type.getActualTypeArguments()[0];
		return new GenericDAO<>(cdiInstanceEM.get(),objectClass);
	}
}
