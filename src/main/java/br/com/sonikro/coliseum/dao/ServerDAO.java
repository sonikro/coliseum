package br.com.sonikro.coliseum.dao;

import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.sonikro.coliseum.entity.Server;
import br.com.sonikro.coliseum.enumerators.ServerStatus;

/**
 * @author sonikro
 *
 */
public class ServerDAO extends GenericDAO {
	protected GenericDAO<Server> mGenericDAO;
	
	public ServerDAO(GenericDAO<Server> prototype) {
		super(Server.class);
		mGenericDAO = prototype;
		this.manager = mGenericDAO.manager;
		
	}
	
	public Server findAvailableServer(Integer slots_needed)
	{
		Query query = this.manager.createQuery("select s from "+Server.class.getSimpleName()+""
				+ " s where status = :status"
				+ "     and number_of_slots >= :number_of_slots", Server.class);
		query.setParameter("status", ServerStatus.AVAILABLE);
		query.setParameter("number_of_slots", slots_needed);
		query.setLockMode(LockModeType.NONE);
		
		Server server = (Server) query.getSingleResult();
		if(server == null)
		{
			throw new RuntimeException("No available servers with "+slots_needed+" slots.");
		}
		return server;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

}
