package br.com.sonikro.coliseum.providers;

import java.io.IOException;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;

@Provider
public class TransactionFilter implements ContainerRequestFilter, ContainerResponseFilter{
	
	private static Logger logger = Logger.getLogger(TransactionFilter.class);
	@Inject
	private EntityManager entityManager;
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		logger.info("Opening Transaction");
		entityManager.getTransaction().begin();
	}
	
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		
		if(responseContext.getStatus()!=500)
		{
			try {
				entityManager.flush();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
	}

}
