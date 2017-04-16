package br.com.sonikro.coliseum.command;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.sonikro.command.BaseCommand;
import br.com.sonikro.command.CmdStarterVar;

public abstract class JobCommand extends BaseCommand{
	@CmdStarterVar
	private EntityManagerFactory mEMF;
	@CmdStarterVar
	private EntityManager mEntityManager;
	
	protected EntityManager getEntityManager()
	{
		if(mEntityManager==null)
		{
			mEntityManager = mEMF.createEntityManager();
		}
		return mEntityManager;
	}
	
	public void disposeEntityManager()
	{
		if(mEntityManager!=null)
		{
			mEntityManager.close();
		}
	}
}
