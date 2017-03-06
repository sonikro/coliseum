package br.com.sonikro.coliseum.resources;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.sonikro.coliseum.dao.GenericDAO;
import br.com.sonikro.coliseum.entity.Server;

	
@Path("test")
public class TestResource {
	@Inject
	private GenericDAO<Server> dao;
	
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public String test() {
    	Server server = new Server();
		server.setIp("sonikro.com");
		server.setHostname("Community Six @ Sonikro Solutions");
		server.setMaintenance_mode(false);
		server.setNumber_of_slots(16);
		server.setPort(27016);
		server.setRcon_password("@sssix@");
		server.setServer_password("sssix");
		server.setSourcetv_password("sstv");
		server.setSourcetv_port(27021);
		
		dao.insert(server);
		return "Success";
    }
}
