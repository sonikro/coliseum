package br.com.sonikro.coliseum.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.sonikro.coliseum.model.APIClient;

public class APIClientDAO extends GenericDAO<APIClient>{

	public APIClientDAO(EntityManager manager)
	{
		super(manager, APIClient.class);
	}
	
	public APIClient findByHostname(String hostname)
	{
		Query query = this.manager.createQuery("from "+APIClient.class.getName()+ " where hostname = :hostname");
		query.setParameter("hostname", hostname);
		return (APIClient) query.getSingleResult();
	}

}
