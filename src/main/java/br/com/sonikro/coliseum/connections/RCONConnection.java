package br.com.sonikro.coliseum.connections;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

import org.jboss.logging.Logger;

import com.ibasco.agql.protocols.valve.source.query.SourceRconAuthStatus;
import com.ibasco.agql.protocols.valve.source.query.SourceRconMessenger;
import com.ibasco.agql.protocols.valve.source.query.client.SourceRconClient;
import com.ibasco.agql.protocols.valve.source.query.exceptions.RconNotYetAuthException;
import com.ibasco.agql.protocols.valve.source.query.logger.SourceLogEntry;
import com.ibasco.agql.protocols.valve.source.query.logger.SourceLogListenService;

import br.com.sonikro.coliseum.entity.ServerCFG;

public class RCONConnection {
	
	private static Logger logger = Logger.getLogger(RCONConnection.class);
	
	private String ip;
	private Integer port;
	private String rcon_password;
	
	private SourceRconAuthStatus authStatus;
	private SourceRconClient rconClient;
	private InetSocketAddress socketAddress;
	
	public RCONConnection(String ip, Integer port, String rcon_password)
	{
		setIp(ip);
		setPort(port);
		setRcon_password(rcon_password);
		socketAddress = new InetSocketAddress(getIp(), getPort());
	}
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}

	public void setRcon_password(String rcon_password) {
		this.rcon_password = rcon_password;
	}
	
	public void open() throws Exception
	{
		rconClient = new SourceRconClient();
		authStatus = rconClient.authenticate(socketAddress,this.rcon_password).join();
		if(!authStatus.isAuthenticated())
		{
			logger.error("RCON Authentication Failed at "+getIp()+ ":"+getPort());
			logger.error("RCON Auth Error Reason: "+authStatus.getReason());
			throw new Exception(authStatus.getReason());
			
		}
	}
	
	public void close() throws IOException
	{
		rconClient.close();
	}
	
	public String executeCommandSequence(List<String> commands) throws Exception
	{
		StringBuilder sb = new StringBuilder();
		for (String string : commands) {
			sb.append(string);
			sb.append(";");
		}
		return executeCmd(sb.toString());
	}
	
	public String executeCmd(String cmd) throws RconNotYetAuthException, Exception
	{
		CompletableFuture<String> execute = rconClient.execute(socketAddress, cmd);
		logger.info("Rcon Executing CMD:"+cmd);
		String result = execute.get(120,TimeUnit.SECONDS);
		logger.info("RCON Result : "+result);
		return result;

	}
	
	public String executeCfg(ServerCFG cfg) throws Exception
	{
		CompletableFuture<String> execute = rconClient.execute(socketAddress, cfg.toString());
		logger.info("Rcon Executing CFG:"+cfg.getName());
		String result = execute.get(120,TimeUnit.SECONDS);
		logger.info("RCON Result : "+result);
		return result;
	}
	
	public void executeCmdAsync(String cmd, BiConsumer<String, Throwable> callBack) throws RconNotYetAuthException
	{
		rconClient.execute(socketAddress, cmd).whenComplete(callBack);
	}
	
	public void test() throws InterruptedException
	{
		System.setProperty("java.net.preferIPv4Stack" , "true");
		SourceLogListenService logListener = new SourceLogListenService(new InetSocketAddress(45679));
		logListener.setLogEventCallback(RCONConnection::processLogData);
		logListener.listen();
		
	}
	
	public static void processLogData(SourceLogEntry message) {
	    logger.info("Got Data : {}"+ message);
	}

	
}
