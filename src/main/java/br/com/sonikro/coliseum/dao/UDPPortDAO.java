package br.com.sonikro.coliseum.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import br.com.sonikro.coliseum.entity.UDPPort;

public class UDPPortDAO extends GenericDAO<UDPPort>{

	private static final long serialVersionUID = 1L;
	
	private GenericDAO<UDPPort> mGenericDAO;
	
	public UDPPortDAO(EntityManager manager)
	{
		super(manager, UDPPort.class);
	}
	
	public UDPPortDAO(GenericDAO<UDPPort> prototype)
	{
		super(UDPPort.class);
		mGenericDAO = prototype;
		this.manager = mGenericDAO.manager;
	}
	
	public void releasePort(Integer port)
	{
		UDPPort udpPort = mGenericDAO.find(port);
		udpPort.release();
	}
	
	public UDPPort getFreePort()
	{
		Query query = this.manager.createQuery("select p from "+UDPPort.class.getSimpleName()+" where p.in_use = :in_use", UDPPort.class);
		query.setParameter("in_use", false);
		UDPPort udpPort = null;
		try {
			udpPort = (UDPPort) query.getSingleResult();
		} catch (Exception e) {
			throw new RuntimeException("No available UDP Ports to listen to server");
		}
		return udpPort;
	}

}
