package br.com.sonikro.coliseum.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.apache.commons.lang3.ArrayUtils;

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
	
	public static Field[] getRecursiveFields(Class objectClass)
	{
		Field[] fields = objectClass.getDeclaredFields();
		if(objectClass.getSuperclass() != null)
		{
			Field[] superFields = getRecursiveFields(objectClass.getSuperclass());
			return ArrayUtils.addAll(fields, superFields);
		}
		
		return fields;
	}
}
