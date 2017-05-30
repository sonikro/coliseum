package br.com.sonikro.coliseum.resources;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.sonikro.coliseum.entity.Map;
import br.com.sonikro.coliseum.security.Secure;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 
@Path("map") @Secure(authenticator="BASIC_AUTH") @Api
public class MapResource extends BaseResource{
	
	@ApiOperation(tags="map", value="Insert new Map")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Map createMap(Map map)
	{
		mMapDAO.insert(map);
		return map;
	}
	
	@ApiOperation(tags="map", value="Update existing map")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Map updateMap(Map map)
	{
		mMapDAO.update(map);
		return map;
	}
	
	@ApiOperation(tags="map", value="Delete Map")
	@DELETE
	@Path("/{mapId}")
	public void deleteMap(@PathParam("mapId") Long mapId)
	{
		Map map = mMapDAO.find(mapId);
		mMapDAO.delete(map);
	}
	
	@ApiOperation(tags="map", value="Get specific map")
	@GET
	@Path("/{mapId}")
	public Map getMap(@PathParam("mapId") Long mapId)
	{
		return mMapDAO.find(mapId);
	}
	
	@ApiOperation(tags="map", value="List all maps")
	@GET
	public List<Map> getAllMaps()
	{
		return mMapDAO.list();
	}
}
