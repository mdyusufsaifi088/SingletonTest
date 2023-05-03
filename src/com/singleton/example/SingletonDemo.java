package com.singleton.example;

public class SingletonDemo {
	
	
	public SingletonDemo() {
		
	}
	
	
	public SingletonDemo getInstance(){
          return new SingletonDemo();
	}

}
