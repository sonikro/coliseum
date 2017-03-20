package br.com.sonikro.coliseum.lobbybuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.jboss.logging.Logger;

import br.com.sonikro.coliseum.command.lobby.StartLobbyCMD;
import br.com.sonikro.coliseum.entity.Lobby;
import br.com.sonikro.coliseum.entity.LobbyTeam;
import br.com.sonikro.coliseum.lobbybuilder.stepfinders.ILobbyStepFinder;
import br.com.sonikro.coliseum.lobbybuilder.stepfinders.LobbyStepFinder;
import br.com.sonikro.coliseum.lobbybuilder.stepfinders.TeamLeaderSF;
import br.com.sonikro.coliseum.util.ReflectionTool;

public class LobbyBuilder {
	private static Logger logger = Logger.getLogger(LobbyBuilder.class);
	
	private Lobby mLobby;
	private static List<LobbyStepFinderModel> mStepFinders;
	
	{
		List<ILobbyStepFinder> mListOfFinders = new ArrayList<>();
		mStepFinders = new ArrayList<>();
		
		try {
			List<Class> stepFinderClasses = ReflectionTool.getClasses("br.com.sonikro.coliseum.lobbybuilder.stepfinders");
			for (Class class1 : stepFinderClasses) {
				if(class1.isAnnotationPresent(LobbyStepFinder.class))
				{
					LobbyStepFinder finderNotation = (LobbyStepFinder) ReflectionTool.getRecursiveAnnotation(class1, LobbyStepFinder.class);
					LobbyStepFinderModel finderModel = new LobbyStepFinderModel();
					finderModel.setFinder((ILobbyStepFinder) class1.newInstance());
					finderModel.setSequence(finderNotation.step_sequence());
					finderModel.setStep_code(finderNotation.step_code());
					mStepFinders.add(finderModel);
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		mStepFinders.sort((f1, f2) -> f1.getSequence() - f2.getSequence());
		logger.info("LobbyStepFinders loaded : "+mStepFinders);

	}
	
	public LobbyBuilder(Lobby lobby)
	{
		mLobby = lobby;
	}
	
	public boolean isLobbyReady() throws Exception
	{
		return (findCurrentStep() == null ? true : false);
	}
	
	public LobbyBuilderStep getStep() throws Exception
	{
		LobbyBuilderStep step = new LobbyBuilderStep();
		
		if(isLobbyReady())
		{
			step.setDesription("Lobby is ready");
			step.setActionPath("/lobby/"+mLobby.getId()+"/start");
			step.setActionVerb("POST");
		}
		else
		{
			step = findCurrentStep();
		}
		
		
		return step;
		
	}

	private LobbyBuilderStep findCurrentStep() throws Exception {
		Iterator<LobbyStepFinderModel> iterator = mStepFinders.iterator();
		while(iterator.hasNext())
		{
			LobbyStepFinderModel finder = iterator.next();
			LobbyBuilderStep step = finder.getFinder().findStep(mLobby);
			if(step != null)
			{
				step.setStep_code(finder.getStep_code());
				return step;
			}
		}
		return null;
	}
}
