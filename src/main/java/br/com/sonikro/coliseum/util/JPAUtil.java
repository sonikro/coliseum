package br.com.sonikro.coliseum.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("coliseum");
	
	public EntityManager getEntityManager()
	{
		return emf.createEntityManager();
	}
}
