package br.com.sonikro.coliseum.resources;

import org.jboss.logging.Logger;

import br.com.sonikro.command.ICommand;
import br.com.sonikro.command.ICommandListener;

public class BaseResource implements ICommandListener{
	protected static Logger logger = Logger.getLogger(BaseResource.class);
	@Override
	public void onCommand(ICommand command) {
		try {
			command.execute();
			command.onSuccess();
		} catch (Exception e) {
			command.rollback(e);
		}
	}

}
