package Pattern.Bridge.test;

public class SmallPen extends Pen
{
	public void draw(String name)
	{
		String penType="细笔";
		this.color.bepaint(penType,name);
		this.fillStyle.bepaint(penType, name);
	}	
}