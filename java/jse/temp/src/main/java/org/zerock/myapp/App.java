package org.zerock.myapp;

import com.google.gson.Gson;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ){
    	Gson gson = new Gson();
    	
    	Person person = new Person("Yoseph", 23);
    	
    	String json = gson.toJson(person);
    	System.out.println("json:"+json);
    	
//      gson.fromJson(json, Person.class);
//      gson.<Person>fromJson(json, Person.class);
	    Person personFromJSON=gson.<Person>fromJson(json, Person.class);
	    System.out.println("personFromJSON:"+personFromJSON);
    }
}


class Person{
	private String name;
	private int age;
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	@Override
    public String toString() {
      return String.format("person(name=%s, age= %d)",this.name, this.age);
    }
}


