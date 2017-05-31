package br.com.sonikro.coliseum.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.sonikro.coliseum.entity.Lobby;
import br.com.sonikro.coliseum.enumerators.LobbyStatus;

public class LobbyDAO extends GenericDAO<Lobby> {
	
	protected GenericDAO<Lobby> mGenericDAO;
	
	public LobbyDAO(Class<Lobby> objectClass) {
		super(objectClass);
	}

	public LobbyDAO(EntityManager em, Class<Lobby> objectClass) {
		super(em, objectClass);
	}

	public LobbyDAO(EntityManager em) {
		super(em);
	}
	
	public LobbyDAO(GenericDAO<Lobby> prototype)
	{
		super(Lobby.class);
		mGenericDAO = prototype;
		this.manager = mGenericDAO.manager;		
	}
	
	public List<Lobby> getListOpenLobbies()
	{
		Query query = this.manager.createQuery("select l from "+Lobby.class.getSimpleName()+" l where"
				+ " status = :status", Lobby.class);
		
		query.setParameter("status", LobbyStatus.BUILDING);
		
		return query.getResultList();
		
	}
	
}
