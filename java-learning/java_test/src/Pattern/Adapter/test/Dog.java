package Pattern.Adapter.test;

public class Dog implements Animal{
	public void wang()
	{

		System.out.println("汪汪汪");
	}
	
	public void run()
	{

		System.out.println("咻咻咻");
	}

	@Override
	public void cry() {
		this.wang();
	}

	@Override
	public void move() {
		this.run();
	}
}