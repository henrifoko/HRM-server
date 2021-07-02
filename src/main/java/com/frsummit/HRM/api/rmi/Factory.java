package com.frsummit.HRM.api.rmi;

public class Factory {

	/**
	 * @author hfoko
	 * 
	 *         This is the description of the method
	 * @param name
	 * @param type
	 * @return
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public static Object getInstance(Class type) throws BeanNotFoundException {
//		String[] tmp = packageClassName.split("\\.");
//		String className = tmp[tmp.length - 1];
//		String className = type.getSimpleName();
//		String beanName = (className.charAt(0) + "").toLowerCase() + className.substring(1);
//
//		System.out.println(beanName);

//		try {
//			Object bean = ServiceWrapper.getService(beanName);
//			return bean;
//		} catch (BeanNotFoundException e) {
//			throw e;
//		}
		Object bean = ContextWrapper.getContext().getBean(type);

		return bean;
	}
}
