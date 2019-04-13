package Pattern.Bridge.test;

public class MiddlePen extends Pen
{
	public void draw(String name)
	{
		String penType="中笔";
		this.color.bepaint(penType,name);			
	}	
}