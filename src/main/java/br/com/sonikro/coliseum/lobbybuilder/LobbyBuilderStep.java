package br.com.sonikro.coliseum.lobbybuilder;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlTransient;

import br.com.sonikro.coliseum.util.ReflectionTool;
import br.com.sonikro.command.BaseCommand;

public class LobbyBuilderStep {

	private String desription;
	private String actionPath;
	private String actionVerb;
	private String step_code;
	
	private Method actionMethod;
	
	private HashMap<String, String> actionKeys = new HashMap<>();
	
	@XmlTransient
	public HashMap<String, String> getActionKeys() {
		return actionKeys;
	}
	public void setActionKeys(HashMap<String, String> actionKeys) {
		this.actionKeys = actionKeys;
	}
	
	@XmlTransient
	public Method getActionMethod() {
		return actionMethod;
	}
	public void setActionMethod(Method actionMethod) {
		this.actionMethod = actionMethod;

		Path path = (Path) actionMethod.getAnnotation(Path.class);
		if(path == null)
		{
			throw new RuntimeException("actionMethod is not a resource method");
		}
		Path resourcePath = actionMethod.getDeclaringClass().getAnnotation(Path.class);
		
		actionPath = resourcePath.value()+path.value();
		actionVerb = ReflectionTool.getMethodVerb(actionMethod);
		
	}
	public String getDesription() {
		return desription;
	}
	public void setDesription(String desription) {
		this.desription = desription;
	}
	public String getActionPath() {
		return actionPath;
	}
	public void setActionPath(String actionPath) {
		this.actionPath = actionPath;
	}
	public String getActionVerb() {
		return actionVerb;
	}
	public void setActionVerb(String actionVerb) {
		this.actionVerb = actionVerb;
	}
	public String getStep_code() {
		return step_code;
	}
	public void setStep_code(String step_code) {
		this.step_code = step_code;
	}

	
	public void buildActionPathWithKeys()
	{
		for (Map.Entry<String, String> entry : getActionKeys().entrySet()) 
		{
			try {
				this.actionPath = this.actionPath.replace(entry.getKey(), entry.getValue());
			} catch (Exception e) {
				
			}
			
		}
	}
	
	
}
