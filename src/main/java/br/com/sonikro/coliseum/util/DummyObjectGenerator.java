package br.com.sonikro.coliseum.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import org.jboss.logging.Logger;

public class DummyObjectGenerator {
	private Logger logger = Logger.getLogger(DummyObjectGenerator.class);
	
	private static Map<Class,Object> objectMap = new HashMap<Class, Object>();
	
	static{
		objectMap.put(Integer.class, Integer.valueOf(1));
		objectMap.put(Float.class, Float.valueOf(1));
		objectMap.put(Double.class, Double.valueOf(1));
		objectMap.put(Long.class, Long.valueOf(1));
		objectMap.put(Boolean.class, Boolean.valueOf(true));
		objectMap.put(BigDecimal.class, BigDecimal.valueOf(1));
		objectMap.put(String.class, "string");
	}
	
	public Object generateDummy(Class objectClass) throws Exception
	{
		logger.info("Generating Dummy Object for "+objectClass.getName());
		Field[] fields = ReflectionTool.getRecursiveFields(objectClass);
		Object dummy ;
		try {
			dummy = objectClass.newInstance();;
		} catch (Exception e) {
			return objectClass;
		}
		
		
		for(Field field : fields)
		{
			if(!field.isAnnotationPresent(XmlTransient.class))
			{
				if(field.isAccessible())
				{
					field.set(dummy, getRandomValueForField(field));
				}
				else
				{
					field.setAccessible(true);
					field.set(dummy, getRandomValueForField(field));
					field.setAccessible(false);
				}
			}
			
		}
		
		return dummy;
	
	}

	private Object getRandomValueForField(Field field) throws Exception {
		Object object = objectMap.get(field.getType());
		if(object==null) //Complex Type, generate new Dummy
		{
			if(field.getType().isInstance(new ArrayList()))
			{
				logger.info("Generating Dummy List of "+field.getType());
				object = new ArrayList();
				ParameterizedType type = (ParameterizedType) field.getGenericType();
				((ArrayList)object).add(generateDummy(type.getActualTypeArguments()[0].getClass()));
			}
			else
			{
				object = generateDummy(field.getType());
			}
			
		}
		return object;
	}
}
