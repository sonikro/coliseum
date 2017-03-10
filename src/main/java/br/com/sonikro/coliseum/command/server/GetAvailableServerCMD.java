package br.com.sonikro.coliseum.command.server;


import br.com.sonikro.coliseum.command.BaseResourceCMD;
import br.com.sonikro.coliseum.dao.ServerDAO;
import br.com.sonikro.coliseum.entity.Server;
import br.com.sonikro.command.CmdResultVar;
import br.com.sonikro.command.CmdStarterVar;

public class GetAvailableServerCMD extends BaseResourceCMD<Server>{
	

	@CmdStarterVar
	protected ServerDAO mServerDAO;
	@CmdStarterVar
	protected Integer mSlots_Needed;
	@CmdResultVar(name="server")
	protected Server mServer;
	
	public GetAvailableServerCMD(br.com.sonikro.command.ICommandListener listener, Object... starterObjects) {
		super(listener, starterObjects);
	}

	
	@Override
	public void execute() throws Exception {
		try {
			mServer = mServerDAO.findAvailableServer(mSlots_Needed);
		} catch (Exception e) {
			throw new Exception("No available servers right now");
		}
		
	}

}
