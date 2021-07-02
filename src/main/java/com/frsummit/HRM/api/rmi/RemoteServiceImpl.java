package com.frsummit.HRM.api.rmi;

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

public class RemoteServiceImpl extends UnicastRemoteObject implements RemoteServiceInterface {

	private static final long serialVersionUID = 21L;

	@SuppressWarnings("rawtypes")
	private static final HashMap<String, Class> PRIMITIVE_TYPES = new HashMap<String, Class>();
	private static final String RMI_PACKAGE_NAME = "com.frsummit.HRM.api.rmi.model";

	private HashMap<String, Object> models;
	private HashMap<String, Method> methods;

	public RemoteServiceImpl() throws RemoteException {
		super();
		this.models = new HashMap<String, Object>();
		this.methods = new HashMap<String, Method>();

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
	public Object exec(String daoClassName, String methodName, String[] classNames, Object[] params, Class returnType)
			throws RemoteException {
		Object result = null;
		Class daoClass;
		Object daoInstance;
		Method method;

		String absoluteMethodName = daoClassName + "." + methodName;
		String methodKey = absoluteMethodName;
		String daoInstanceKey = daoClassName;

		System.out.println("Log + Initialisation des classes");
		Class[] classes = new Class[classNames.length];
		String className;
		for (int i = 0, l = classNames.length; i < l; i++) {
			className = classNames[i];
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
			if (this.models.containsKey(daoInstanceKey)) {
				System.out.println("Log + Récupération de l'instance à partir de la HashMap");
				daoInstance = this.models.get(daoInstanceKey);
				System.out.println("Log - Récupération de l'instance à partir de la HashMap terminée");
				if (this.methods.containsKey(methodKey)) {
					System.out.println("Log + Récupération de la méthode à partir de la HashMap");
					method = this.methods.get(methodKey);
					System.out.println("Log + Récupération de la méthode à partir de la HashMap terminée");
				} else {
					System.out.println("Log + Création et enregistrement de l'objet méthode (Nouvelle méthode)");
					daoClass = Class.forName(daoClassName);
					method = daoClass.getMethod(methodName, classes);
					this.methods.put(absoluteMethodName, method);
					System.out.println("Log - Création et enregistrement de l'objet méthode terminée");
				}
				System.out.println("Log - Récupération à partir de la HashMap terminée");
			} else {
				System.out.println("Log + Création d'une instance");
				daoClass = Class.forName(daoClassName);
				/**
				 * @TODO Par rapport à l'instanciation des classes du modèle: Est t-elle
				 *       toujours nécessaire ? Comment peut t-elle s'effectuer ? Que faire dans
				 *       le cas de méthodes statiques ? Que faire des cas particuliers ?
				 */
				daoInstance = Factory.getInstance(daoClass);
				method = daoClass.getMethod(methodName, classes);

				// Here try to use a design pattern
				this.models.put(daoClassName, daoInstance);
				this.methods.put(daoClassName + "." + methodName, method);
				System.out.println("Log - Création de l'instance terminée");
			}
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

	public Object fitObject(Object obj, Class type) throws ClassNotFoundException, NoSuchMethodException,
			InvocationTargetException, InstantiationException, IllegalAccessException {
		// System.out.println(type);
		if (obj == null || PRIMITIVE_TYPES.containsKey(type)) {

			// System.out.println("CASE 1 - Primitive type");

			return obj;
		} else if (Collection.class.isAssignableFrom(type)) { // A collection

			// System.out.println("CASE 2 - Collection type");

			List resultList = new ArrayList();
			Iterable collection = (Iterable) obj;
			for (Object item : collection) {
				String simpleClassName = item.getClass().getSimpleName();
				String targetClassName = RMI_PACKAGE_NAME + "." + simpleClassName;
				try {
					Class apiClass = Class.forName(targetClassName);
					Constructor constructor = apiClass.getConstructor();
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

	public void duplicate(Class type, Object obj, Object copy) {
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
