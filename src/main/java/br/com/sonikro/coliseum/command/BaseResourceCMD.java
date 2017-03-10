package br.com.sonikro.coliseum.command;

import br.com.sonikro.coliseum.dao.GenericDAO;
import br.com.sonikro.command.BaseCommand;
import br.com.sonikro.command.CmdResultVar;
import br.com.sonikro.command.CmdStarterVar;
import br.com.sonikro.command.ICommandListener;

public abstract class BaseResourceCMD<Type> extends BaseCommand{
	@CmdStarterVar @CmdResultVar(name="genericDAO")
	protected GenericDAO<Type> mDAO;
	
	public BaseResourceCMD(ICommandListener listener, Object... starterObjects) {
		super(listener, starterObjects);
	}

	

}
