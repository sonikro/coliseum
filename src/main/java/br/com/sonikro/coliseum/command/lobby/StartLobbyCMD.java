package br.com.sonikro.coliseum.command.lobby;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.ScheduleBuilder;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import br.com.sonikro.coliseum.command.BaseResourceCMD;
import br.com.sonikro.coliseum.connections.RCONConnection;
import br.com.sonikro.coliseum.entity.Lobby;
import br.com.sonikro.coliseum.entity.Server;
import br.com.sonikro.coliseum.enumerators.LobbyStatus;
import br.com.sonikro.coliseum.jobs.ExecuteCommandJOB;
import br.com.sonikro.coliseum.jobs.SimpleJobManager;
import br.com.sonikro.command.BaseCommand;
import br.com.sonikro.command.CmdResultVar;
import br.com.sonikro.command.CmdStarterVar;
import br.com.sonikro.command.CommandBuilder;

public class StartLobbyCMD extends BaseResourceCMD<Lobby>{
	@CmdStarterVar @CmdResultVar(name="lobby")
	private Lobby mLobby;
	
	private CommandBuilder cmdBuilder;
	@Override
	public void execute() throws Exception {
		cmdBuilder = new CommandBuilder(mListener);
		
		BaseCommand setupServer = cmdBuilder.setCommandClass(SetupLobbyServerCMD.class)
											.initializeWith(mLobby)
											.build();
		
		setupServer.dispatch(); //Setup Server
		
		dispatchServerListeningJOB();

	}
	private void dispatchServerListeningJOB() throws Exception {


		BaseCommand setupServerListener = cmdBuilder.setCommandClass(SetupServerListenerCMD.class)
													.initializeWith(mLobby)
													.build();
		
		SimpleJobManager jobManager = new SimpleJobManager();
		
		jobManager.run(setupServerListener);
		
	}
	


}
