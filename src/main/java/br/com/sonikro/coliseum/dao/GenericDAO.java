package br.com.sonikro.coliseum.dao;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.jboss.logging.Logger;

@Named("genericDAO")
public class GenericDAO<Type> implements Serializable{
	private static Logger logger = Logger.getLogger(GenericDAO.class);
	@Inject
	protected EntityManager manager;

	private Class<?> objectClass;
	
	public GenericDAO(EntityManager em)
	{
		this.manager = em;
	}
	
	public GenericDAO(Class<Type> objectClass)
	{
		logger.info("Instantiating GenericDAO class constructor");
		this.objectClass = objectClass;
		//this.manager = manager;
		//this.objectClass = (Class<Type>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public void setObjectClass(Class objectClass)
	{
		this.objectClass = objectClass;
	}
	
	public GenericDAO(EntityManager em, Class<Type> objectClass)
	{
		this(objectClass);
		this.manager = em;
	}
	
	public void insert(Type persistentObject)
	{

		manager.persist(persistentObject);
	}
	
	public void update(Type persistentObject)
	{
		persistentObject = manager.merge(persistentObject);
	}
	
	public void delete(Type persistentObject)
	{
		manager.remove(persistentObject);
	}
	
	public Type find(Object id)
	{
		logger.info("GenericDAO<"+objectClass.getName()+"> -> FIND : "+id);
		return (Type) manager.find(objectClass, id);
	}
	
	public List<Type> list()
	{
		return (List<Type>) this.manager.createQuery("select c from "+ objectClass.getSimpleName()+"  c", objectClass).getResultList();
	}
}
