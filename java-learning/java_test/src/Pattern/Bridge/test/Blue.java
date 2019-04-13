package Pattern.Bridge.test;

public class Blue implements Color
{
	public void bepaint(String penType,String name)
	{

		System.out.println(penType +  "画"+ name + "." + "使用蓝色");
	}
}