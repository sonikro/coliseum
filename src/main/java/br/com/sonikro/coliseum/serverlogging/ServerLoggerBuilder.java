package br.com.sonikro.coliseum.serverlogging;

import br.com.sonikro.UDPListener.PacketHandler;
import br.com.sonikro.coliseum.entity.Server;
import br.com.sonikro.coliseum.entity.UDPPort;

public class ServerLoggerBuilder {
	private Server mServer;
	private UDPPort mUDPPort;
	private PacketHandler mHandler;
	
	public static ServerLoggerBuilder newLogger()
	{
		return new ServerLoggerBuilder();
	}
	
	public ServerLoggerBuilder logServer(Server server)
	{
		this.mServer = server;
		return this;
	}
	
	public ServerLoggerBuilder atPort(UDPPort udpPort)
	{
		this.mUDPPort = udpPort;
		return this;
	}
	
	public ServerLoggerBuilder withHandler(PacketHandler handler)
	{
		this.mHandler = handler;
		return this;
	}
	
	public ServerLogger build()
	{
		ServerLogger logger = new ServerLogger(mServer, mHandler, mUDPPort);
		return logger;
	}
}
