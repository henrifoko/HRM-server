package com.frsummit.HRM.api.server;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.frsummit.HRM.api.server.beanfactory.BeanFactoryImpl;

public class RemoteServiceImpl extends UnicastRemoteObject implements RemoteService {

	private static final long serialVersionUID = 21L;

	private static final HashMap<String, Class<?>> PRIMITIVE_TYPES = new HashMap<String, Class<?>>();
	private static final String RMI_PACKAGE_NAME = "com.frsummit.HRM.api.rmi.entity";

	public RemoteServiceImpl() throws RemoteException {
		super();
		PRIMITIVE_TYPES.put("int", int.class);
		PRIMITIVE_TYPES.put("byte", byte.class);
		PRIMITIVE_TYPES.put("long", long.class);
		PRIMITIVE_TYPES.put("char", char.class);
		PRIMITIVE_TYPES.put("void", void.class);
		PRIMITIVE_TYPES.put("short", short.class);
		PRIMITIVE_TYPES.put("float", float.class);
		PRIMITIVE_TYPES.put("double", double.class);
		PRIMITIVE_TYPES.put("boolean", boolean.class);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object exec(String daoClassName, String methodName, String[] paramTypes, Object[] params, Class returnType)
			throws RemoteException {
		BeanFactoryImpl beanFactory = new BeanFactoryImpl();

		Object result = null;
		Class daoClass;
		Object daoInstance;
		Method method;

		String absoluteMethodName = daoClassName + "." + methodName;
		String methodKey = absoluteMethodName;
		String daoInstanceKey = daoClassName;

		System.out.println("Log + Initialisation des classes");
		Class[] classes = new Class[paramTypes.length];

		String className;
		for (int i = 0, l = paramTypes.length; i < l; i++) {
			className = paramTypes[i];
			try {
				if (PRIMITIVE_TYPES.containsKey(className)) {
					classes[i] = PRIMITIVE_TYPES.get(className);
				} else {
					classes[i] = Class.forName(className);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
		}
		System.out.println("Log - Initialisation des classes terminée");

		try {
			System.out.println("Log + Récupération des instances");
			System.out.println("Log + Création d'une instance");
			daoClass = Class.forName(daoClassName);
			daoInstance = beanFactory.getInstance(daoClass);
			method = daoClass.getMethod(methodName, classes);
			System.out.println("Log - Création de l'instance terminée");
			System.out.println("Log - Récupération des instances terminée");

			System.out.println("Log + Invocation de la méthode");
			result = method.invoke(daoInstance, params);
			System.out.println("Log - Invocation de la méthode terminée");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		// System.out.println(result);
		try {
			return fitObject(result, returnType);
		} catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException
				| IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Object fitObject(Object obj, Class<?> type) throws ClassNotFoundException, NoSuchMethodException,
			InvocationTargetException, InstantiationException, IllegalAccessException {
		if (obj == null || PRIMITIVE_TYPES.containsKey(type.getClass())
				|| (type.getName().charAt(0) == '[' && type.getName().length() == 2)) {

			return obj;
		} else if (Collection.class.isAssignableFrom(type)) { // A collection

			// System.out.println("CASE 2 - Collection type");

			List<Object> resultList = new ArrayList<>();
			Iterable<Object> collection = (Iterable<Object>) obj;
			for (Object item : collection) {
				String simpleClassName = item.getClass().getSimpleName();
				String targetClassName = RMI_PACKAGE_NAME + "." + simpleClassName;
				try {
					Class<?> apiClass = Class.forName(targetClassName);
					Constructor<?> constructor = apiClass.getConstructor();
					Object resultItem = constructor.newInstance();
					duplicate(resultItem.getClass(), item, resultItem);
					resultList.add(resultItem);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					throw e;
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
					throw e;
				} catch (InvocationTargetException e) {
					e.printStackTrace();
					throw e;
				} catch (InstantiationException e) {
					e.printStackTrace();
					throw e;
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					throw e;
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					throw e;
				}
			}
			return resultList;
		} else { // An object model

			// System.out.println("CASE 3 - Model type");

			Object result = type.getConstructor().newInstance();
			duplicate(type, obj, result);
			return result;
		}
	}

	public void duplicate(Class<?> type, Object obj, Object copy) {
		Field[] fields = type.getDeclaredFields();
		// System.out.println("Source type : " + type);
		// System.out.println("Field's length : " + fields.length);
		// System.out.println("obj class : " + obj.getClass());
		// System.out.println("copy class : " + copy.getClass());
		String getter, setter, name, trail;
		Method method;
		Object tmp;
		// int index = 0;
		for (Field f : fields) {
			name = f.getName();
			trail = (name.charAt(0) + "").toUpperCase() + name.substring(1);
			getter = "get" + trail;
			setter = "set" + trail;
			try {
				method = obj.getClass().getDeclaredMethod(getter);
				// System.out.println(getter);
				tmp = method.invoke(obj);
				// System.out.println(tmp);
				method = copy.getClass().getDeclaredMethod(setter, f.getType());
				// System.out.println(setter);
				method.invoke(copy, tmp);
				// System.out.println("------ field " + index + " -----");
				// index++;
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
