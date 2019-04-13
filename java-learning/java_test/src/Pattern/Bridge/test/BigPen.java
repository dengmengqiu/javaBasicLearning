package Pattern.Bridge.test;

public class BigPen extends Pen
{
	public void draw(String name)
	{
		String penType="粗笔";
		this.color.bepaint(penType,name);			
	}	
}