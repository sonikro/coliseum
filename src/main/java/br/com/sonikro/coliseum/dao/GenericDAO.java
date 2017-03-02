package br.com.sonikro.coliseum.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class GenericDAO<Type>{
	@PersistenceContext(name="coliseum")
	protected EntityManager manager;
	private Class<?> objectClass;;

	public GenericDAO(EntityManager manager, Class<Type> entityClass)
	{
		this.manager = manager;
		this.objectClass = entityClass;
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
	
	public Type find(Integer id)
	{
		return (Type) manager.find(objectClass, id);
	}
	
	public List<Type> list()
	{
		return (List<Type>) this.manager.createQuery("select c from "+ objectClass.getName()+"  c", objectClass).getResultList();
	}
}
