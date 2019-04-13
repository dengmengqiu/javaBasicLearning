package Pattern.Bridge.test;

public abstract class Pen
{
	protected Color color;
	protected FillStyle fillStyle;
	public void setColor(Color color)
	{
		this.color=color;
	}
	public void setFillStyle(FillStyle fillStyle){this.fillStyle = fillStyle;}
	public abstract void draw(String name);
} 