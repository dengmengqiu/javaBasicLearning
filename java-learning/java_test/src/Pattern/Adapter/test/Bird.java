package Pattern.Adapter.test;

public class Bird implements Animal{
	public void tweedle()
	{

		System.out.println("叽叽喳喳");
	}
	
	public void fly()

	{
		System.out.println("噗噗噗");
	}

	@Override
	public void cry() {
		this.tweedle();
	}

	@Override
	public void move() {
		this.fly();
	}
}