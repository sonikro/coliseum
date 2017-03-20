package br.com.sonikro.coliseum.lobbybuilder;

import br.com.sonikro.coliseum.lobbybuilder.stepfinders.ILobbyStepFinder;

public class LobbyStepFinderModel {
	private int sequence;
	private String step_code;
	private ILobbyStepFinder finder;
	
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public String getStep_code() {
		return step_code;
	}
	public void setStep_code(String step_code) {
		this.step_code = step_code;
	}
	public ILobbyStepFinder getFinder() {
		return finder;
	}
	public void setFinder(ILobbyStepFinder finder) {
		this.finder = finder;
	}
	
	@Override
	public String toString() {
		return getFinder().getClass().toGenericString();
	}
	
	
}
