package br.com.sonikro.coliseum.resources;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Entity;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;

import br.com.sonikro.coliseum.dao.GenericDAO;
import br.com.sonikro.coliseum.entity.GameClass;
import br.com.sonikro.coliseum.entity.GameType;
import br.com.sonikro.coliseum.entity.Lobby;
import br.com.sonikro.coliseum.entity.LobbyUser;
import br.com.sonikro.coliseum.entity.Map;
import br.com.sonikro.coliseum.entity.Server;
import br.com.sonikro.coliseum.entity.Tier;
import br.com.sonikro.coliseum.entity.User;
import br.com.sonikro.coliseum.resources.model.ResourceHelp;
import br.com.sonikro.coliseum.resources.model.ResourceParameter;
import br.com.sonikro.coliseum.util.DummyObjectGenerator;
import br.com.sonikro.coliseum.util.ReflectionTool;
import br.com.sonikro.command.CommandBuilder;
import br.com.sonikro.command.ICommand;
import br.com.sonikro.command.ICommandListener;

public class BaseResource implements ICommandListener{
	protected static Logger logger = Logger.getLogger(BaseResource.class);
	protected CommandBuilder cmdBuilder;


	@Inject
	protected GenericDAO<GameType> mGameTypeDAO;
	@Inject
	protected GenericDAO<Server> mServerDAO;
	@Inject
	protected GenericDAO<Lobby> mLobbyDAO;
	@Inject
	protected GenericDAO<LobbyUser> mLobbyUserDAO;
	@Inject
	protected GenericDAO<User> mUserDAO;
	@Inject
	protected GenericDAO<GameClass> mGameClassDAO;
	@Inject
	protected GenericDAO<Tier> mTierDAO;
	@Inject
	protected GenericDAO<Map> mMapDAO;
	public BaseResource()
	{
		cmdBuilder = new CommandBuilder(this);
	}
	
	
	@Override
	public void onCommand(ICommand command) {
		try {
			command.execute();
			command.onSuccess();
		} catch (Exception e) {
			command.rollback(e);
		}
	}
	
	@GET
	@Path("/help")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<ResourceHelp> resourceHelp() throws Exception
	{
		ArrayList<ResourceHelp> helpList = new ArrayList();
		Method[] declaredMethods = getClass().getDeclaredMethods();
		for(Method method : declaredMethods)
		{
			if(method.isAnnotationPresent(Path.class))
			{
				Path path = method.getAnnotation(Path.class);
				ResourceHelp help = new ResourceHelp();
				help.signature = getMethodParameters(method);
				help.path = path.value();
				help.verb = ReflectionTool.getMethodVerb(method);
				if(help.verb!=null)
				{
					helpList.add(help);
				}
			}
		}
		
		return helpList;
	}

	private Parameter[] getMethodParameters(Method method) throws Exception {
		//ArrayList<Object> methodParameters = new ArrayList<>();
		//DummyObjectGenerator generator = new DummyObjectGenerator();
		Parameter[] parameters = method.getParameters();
		return parameters;
		/*
		for(Parameter parameter : parameters){
			methodParameters.add( generator.generateDummy(parameter.getType()));
		}
		Object objects[] = new Object[methodParameters.size()];
		return methodParameters.toArray(objects);*/
	}

	
	

}
