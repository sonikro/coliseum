package br.com.sonikro.coliseum.util;

import java.lang.annotation.Annotation;

public class ReflectionTool {
	public static Annotation getRecursiveAnnotation(Class searchClass, Class annotationClass)
	{
		Annotation annotation = searchClass.getAnnotation(annotationClass);
		if(annotation == null && searchClass.getSuperclass() != null)
		{
			annotation = getRecursiveAnnotation(searchClass.getSuperclass(), annotationClass);
		}
		return annotation;
	}
}
