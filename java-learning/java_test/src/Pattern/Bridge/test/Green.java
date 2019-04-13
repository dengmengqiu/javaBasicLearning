package Pattern.Bridge.test;

public class Green implements Color
{
	public void bepaint(String penType,String name)
	{
		System.out.println(penType + "使用颜色"+ name + ".");
	}
}