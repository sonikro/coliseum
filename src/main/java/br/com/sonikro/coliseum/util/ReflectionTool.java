package br.com.sonikro.coliseum.util;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

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
	
	public static String getMethodVerb(Method method) {
		Class [] verbs = {GET.class, POST.class, PUT.class, DELETE.class};
		for(Class verb : verbs)
		{
			if(method.isAnnotationPresent(verb))
			{
				return verb.getSimpleName();
			}
		}
		return null;
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
	
	 public static List<Class> getClasses(String packageName)
	            throws ClassNotFoundException, IOException {
	        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	        assert classLoader != null;
	        String path = packageName.replace('.', '/');
	        Enumeration resources = classLoader.getResources(path);
	        List<File> dirs = new ArrayList();
	        while (resources.hasMoreElements()) {
	            URL resource = (URL) resources.nextElement();
	            dirs.add(new File(resource.getFile()));
	        }
	        ArrayList classes = new ArrayList();
	        for (File directory : dirs) {
	            classes.addAll(findClasses(directory, packageName));
	        }
	        return classes;
	    }
	 
	 private static List findClasses(File directory, String packageName) throws ClassNotFoundException {
	        List classes = new ArrayList();
	        if (!directory.exists()) {
	            return classes;
	        }
	        File[] files = directory.listFiles();
	        for (File file : files) {
	            if (file.isDirectory()) {
	                assert !file.getName().contains(".");
	                classes.addAll(findClasses(file, packageName + "." + file.getName()));
	            } else if (file.getName().endsWith(".class")) {
	                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
	            }
	        }
	        return classes;
	    }
}
