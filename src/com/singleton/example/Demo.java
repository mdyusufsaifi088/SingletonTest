package com.singleton.example;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


class Singleton implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;

	private Singleton() {
	}

	public static Singleton getObj() {
		return Singleton.Holder.obj;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new RuntimeException("clone not suported");
	}
	
	private Object readResolve() {
		throw new RuntimeException("serialization not suported");
	}
	
	
	private static class Holder{
		private  static final Singleton obj=new Singleton();
	}
}

public class Demo {

	public static void main(String[] args) throws Exception {
		/* cloning testing
		  Singleton obj1 = Singleton.getObj(); 
		  Singleton obj2 = (Singleton) obj1.clone();
		  System.out.println(obj1.hashCode()); 
		  System.out.println(obj2.hashCode());
		 */

		/*serialization testing
		Singleton obj1 = Singleton.getObj();
		ObjectOutput out = new ObjectOutputStream(new FileOutputStream("file.text"));
		out.writeObject(obj1);
		out.close();

		// deserialize from file to object
		ObjectInput in = new ObjectInputStream(new FileInputStream("file.text"));
		Singleton obj2 = (Singleton) in.readObject();
		in.close();
		
		System.out.println(obj1.hashCode()); 
		System.out.println(obj2.hashCode());
		*/
		
		/* reflection testing
		Constructor constructor = Singleton.class.getDeclaredConstructors()[0];
		constructor.setAccessible(true);
		
		Singleton obj1 = Singleton.getObj();
		Singleton obj2 = (Singleton) constructor.newInstance();
		
		System.out.println(obj1.hashCode()); 
		System.out.println(obj2.hashCode());
		*/
		
		
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		Future<Singleton> obj1 = executorService.submit(Singleton::getObj);
		Future<Singleton> obj2 = executorService.submit(Singleton::getObj);
		
		System.out.println(obj1.get().hashCode()); 
		System.out.println(obj2.get().hashCode());
		
	}
}
