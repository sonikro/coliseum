package br.com.sonikro.coliseum.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.sonikro.coliseum.entity.Tier;
import br.com.sonikro.coliseum.security.Secure;

@Path("tier") @Secure(authenticator="BASIC_AUTH")
public class TierResource extends BaseResource{
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Tier> getAllTiers()
	{
		return mTierDAO.list();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{tierId}")
	public Tier getTier(@PathParam("tierId") Long id)
	{
		return mTierDAO.find(id);
	}
	
	@POST
	public void createTier(Tier tier)
	{
		mTierDAO.insert(tier);
	}
	
	@PUT
	public void updateTier(Tier tier)
	{
		mTierDAO.update(tier);
	}
	
	
}
