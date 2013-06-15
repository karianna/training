package org.jclarity.training;

public class ClassObjectExamples {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		ClassObjectExamples clo = new ClassObjectExamples();
		clo.run();
	}

	private void testObj(Object obj, String className) {
		try {
			Class<?> clazz = Class.forName(className);
			System.out.println("Class objects are equal: "+ (obj.getClass() == clazz));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static class MyClassLoader extends ClassLoader {
		
	}
	
	private void run() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		testObj("foo", "java.lang.String");
		testObj("foo", "java.lang.Integer");
		testObj(2, "java.lang.Integer"); // autoboxing

		ClassLoader cl = new MyClassLoader();
		Class<?> clz = cl.loadClass("jpmc.training.slides.TWRExamples");
		testObj(clz.newInstance(), "jpmc.training.slides.TWRExamples");
	}

}
