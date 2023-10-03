package com.gme.hom.testService.models;

/*
 * A sample model
 */
public class Test {
	
	private int counter;
	
	public Test(){
		counter = 0;
	}
	
	public int getCounter() {
		return counter;
	}
	
	public int incCounter() {
		return counter++;
	}
	
	public String getClasspath() {
		return System.getProperty("java.clas.path");
	}
}
