package org.jclarity.training;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.junit.Test;

public class ReflectionExamples {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		ReflectionExamples rfx = new ReflectionExamples();
		rfx.run();
	}

	private void scanForTestMethods(Class<?> clazz) {
		for (Method m : clazz.getMethods()) {
			for (Annotation a : m.getAnnotations()) {
				if (a.annotationType() == Test.class) {
					System.out.println(m.getName());
				}
			}
		}
	}
	
	private void run() throws ClassNotFoundException {
		scanForTestMethods(Class.forName("org.jclarity.training.SaferCountersTest"));
		scanForTestMethods(Class.forName("java.lang.Integer"));

	}

}
