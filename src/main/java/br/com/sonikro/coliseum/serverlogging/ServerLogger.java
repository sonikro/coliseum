package br.com.sonikro.coliseum.serverlogging;

import br.com.sonikro.UDPListener.PacketHandler;
import br.com.sonikro.UDPListener.UDPListener;
import br.com.sonikro.UDPListener.UDPListenerBuilder;
import br.com.sonikro.coliseum.entity.Server;
import br.com.sonikro.coliseum.entity.UDPPort;

public class ServerLogger{
	private static final Integer MAX_PACKET_SIZE = 255;
	
	
	private Server mServer;
	private UDPListener mUDPListener;
	private PacketHandler mHandler;
	private UDPPort mUDPPort;
	public ServerLogger(Server server, PacketHandler handler, UDPPort udpPort)
	{
		this.mServer = server;
		this.mHandler = handler;
		this.mUDPPort = udpPort;
	}
	
	public void startLogging() throws Exception
	{
		buildUDPListener();
		mUDPListener.syncListen();
	}

	

	private void buildUDPListener() {
		mUDPListener = UDPListenerBuilder.newListener()
										 .atPort(this.mUDPPort.getPort())
										 .packetSize(MAX_PACKET_SIZE)
										 .preferIPV4()
										 .withPacketHandler(mHandler)
										 .build();
		this.mUDPPort.assignToServer(mServer);		
	}

	public UDPPort getmUDPPort() {
		return this.mUDPPort;
	}
	
	

}
