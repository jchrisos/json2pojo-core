package com.json2pojo.utils;

import java.lang.reflect.Method;

public class ReflectionUtils {

	@SuppressWarnings("rawtypes")
	public static Object execute(Object object, String methodName, Object... params) {
		
		try {

			Class[] classes = null;
			Object[] paramValues = null;
			
			if (params != null && params.length > 0) {

				classes = new Class[params.length];
				paramValues = new Object[params.length];
				
				for (int i = 0; i < params.length; i++) {
					classes[i] = (Class) params[i].getClass();
					paramValues[i] = params[i];
				}
			}
			
			Method method = object.getClass().getDeclaredMethod(methodName, classes);
			method.setAccessible(true);
			
			return method.invoke(object, paramValues);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}
