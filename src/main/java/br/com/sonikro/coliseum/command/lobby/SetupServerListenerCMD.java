package br.com.sonikro.coliseum.command.lobby;


import java.net.DatagramPacket;

import org.jboss.logging.Logger;

import br.com.sonikro.UDPListener.PacketHandler;
import br.com.sonikro.coliseum.command.JobCommand;
import br.com.sonikro.coliseum.dao.GenericDAO;
import br.com.sonikro.coliseum.dao.UDPPortDAO;
import br.com.sonikro.coliseum.entity.Lobby;
import br.com.sonikro.coliseum.entity.Server;
import br.com.sonikro.coliseum.entity.UDPPort;
import br.com.sonikro.coliseum.enumerators.LobbyStatus;
import br.com.sonikro.coliseum.serverlogging.ServerLogger;
import br.com.sonikro.coliseum.serverlogging.ServerLoggerBuilder;
import br.com.sonikro.command.BaseCommand;
import br.com.sonikro.command.CmdResultVar;
import br.com.sonikro.command.CmdStarterVar;
import br.com.sonikro.command.CommandBuilder;

public class SetupServerListenerCMD extends JobCommand implements PacketHandler{

	private static Logger logger = Logger.getLogger(SetupServerListenerCMD.class);
	
	@CmdStarterVar @CmdResultVar(name="lobby")
	private Lobby mLobby;
	
	private ServerLogger mServerLogger;
	
	private UDPPortDAO mPortDAO;
	
	private Server mServer;
	
	@Override
	public void execute() throws Exception {
		logger.info("Setting up ServerListener for lobby "+mLobby.getId());
		
		
		mLobby = getEntityManager().find(Lobby.class, mLobby.getId());
		mServer = mLobby.getServer();
		
		mLobby.setStatus(LobbyStatus.IN_PROGRESS);
		
		mPortDAO = new UDPPortDAO(getEntityManager());
		
		mServerLogger = ServerLoggerBuilder.newLogger()
										   .logServer(mLobby.getServer())
										   .atPort(getAvailablePort())
										   .withHandler(this)	
										   .build();

		getEntityManager().flush();
		mServerLogger.startLogging();
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
		String string = new String(packet.getData());
		if(string.contains("World triggered \"Round_Start\""))
		{
			logger.info("MATCH STARTED AT LOBBY "+mLobby.getId());
			
			mLobby.setStatus(LobbyStatus.IN_PROGRESS);
		}
	}

	@Override
	public boolean isTerminatorPacket(DatagramPacket packet) {
		String string = new String(packet.getData());
		string = string.trim();
		if(string.contains("World triggered \"Game_Over\"") || string.contains("sonikro end match")) 
		{
			return true;
		}
		return false;
	}

	private void finishLobby() throws Exception {
		CommandBuilder builder = new CommandBuilder(mListener);

		BaseCommand finishLobby = builder.setCommandClass(FinishLobbyCMD.class)
										 .initializeWith(mLobby,new GenericDAO<Lobby>(getEntityManager()))
										 .build();
		
		finishLobby.dispatch();

		
	}
	
	@Override
	public void rollback(Exception exception) {
		super.rollback(exception);
		releasePort();
	}
	
	@Override
	public void onSuccess() {
		super.onSuccess();
		releasePort();
	}

	@Override
	public void onEndOfConnection() {
		try {
			finishLobby();
		} catch (Exception e) {
			logger.error("Error Finishing Lobby",e);
		}
	}


}
