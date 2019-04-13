package reflect;

import java.lang.reflect.Field;

@MyAnnnotation(value = "person")
public class Person extends Creature<String> implements Comparable, Myinterface{
	public String name;
	private int age;

	public Person(String name) {
		super();
		this.name = name;
	}

	public Person(int age) {
		super();
		this.age = age;
	}

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	public Person() {
		// TODO Auto-generated constructor stub
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void show() {
		System.out.println("Œ“ «" + name);
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	} 
	
	class pet{}
}