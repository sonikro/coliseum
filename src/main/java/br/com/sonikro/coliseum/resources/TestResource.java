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
    	
		return "Success";
    }
}
