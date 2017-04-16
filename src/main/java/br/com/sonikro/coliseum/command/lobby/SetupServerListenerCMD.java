package br.com.sonikro.coliseum.command.lobby;


import java.net.DatagramPacket;

import org.jboss.logging.Logger;

import br.com.sonikro.UDPListener.PacketHandler;
import br.com.sonikro.coliseum.command.JobCommand;
import br.com.sonikro.coliseum.dao.UDPPortDAO;
import br.com.sonikro.coliseum.entity.Lobby;
import br.com.sonikro.coliseum.entity.UDPPort;
import br.com.sonikro.coliseum.serverlogging.ServerLogger;
import br.com.sonikro.coliseum.serverlogging.ServerLoggerBuilder;
import br.com.sonikro.command.CmdResultVar;
import br.com.sonikro.command.CmdStarterVar;

public class SetupServerListenerCMD extends JobCommand implements PacketHandler{

	private static Logger logger = Logger.getLogger(SetupServerListenerCMD.class);
	
	@CmdStarterVar @CmdResultVar(name="lobby")
	private Lobby mLobby;
	
	private ServerLogger mServerLogger;
	
	private UDPPortDAO mPortDAO;
	
	@Override
	public void execute() throws Exception {

		mPortDAO = new UDPPortDAO(getEntityManager());
		
		mServerLogger = ServerLoggerBuilder.newLogger()
										   .logServer(mLobby.getServer())
										   .atPort(getAvailablePort())
										   .withHandler(this)
										   .build();
		mServerLogger.startLogging();
		
		releasePort();
	}

	private void releasePort() {
		UDPPort port = mServerLogger.getmUDPPort();
		port.release();
		
	}

	private UDPPort getAvailablePort() {
		return mPortDAO.getFreePort();
	}

	@Override
	public void handlePacket(DatagramPacket packet) {
		// To-Do
		
	}

	@Override
	public boolean isTerminatorPacket(DatagramPacket packet) {
		String string = new String(packet.getData());
		string = string.trim();
		if(string.contains("match") && string.contains("end"))
		{
			return true;
		}
		return false;
	}


}
