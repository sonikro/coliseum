package br.com.sonikro.coliseum.connections;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;

import org.jboss.logging.Logger;

import com.ibasco.agql.protocols.valve.source.query.SourceRconAuthStatus;
import com.ibasco.agql.protocols.valve.source.query.client.SourceRconClient;
import com.ibasco.agql.protocols.valve.source.query.exceptions.RconNotYetAuthException;

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
	
	public String executeCmd(String cmd) throws RconNotYetAuthException, Exception
	{
		CompletableFuture<String> execute = rconClient.execute(socketAddress, cmd);
		return execute.get();

	}
	
	public void executeCmdAsync(String cmd, BiConsumer<String, Throwable> callBack) throws RconNotYetAuthException
	{
		rconClient.execute(socketAddress, cmd).whenComplete(callBack);
	}
	

	
}
