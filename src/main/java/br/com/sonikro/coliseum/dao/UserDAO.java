package br.com.sonikro.coliseum.dao;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.sonikro.coliseum.entity.User;

public class UserDAO extends GenericDAO<User>{
	protected GenericDAO<User> mGenericDAO;
	

	private static final long serialVersionUID = 1L;
	
	public UserDAO(GenericDAO<User> genericDAO) {
		super(User.class);
		mGenericDAO = genericDAO;
		this.manager = genericDAO.manager;
	}
	
	public User findBySteamId(String steam_id)
	{
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> from = query.from(User.class);

		
		TypedQuery<User> typedQuery = manager.createQuery(
				query.select(from).where(builder.equal(from.get("steam_id"), steam_id))
				);

		return typedQuery.getSingleResult();
	}

}
