package br.com.sonikro.coliseum.lobbybuilder.stepfinders;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface LobbyStepFinder {
	String step_code() default "";
	int step_sequence() default 0;
}
