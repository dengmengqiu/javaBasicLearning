package Pattern.Bridge.test;

import Pattern.Adapter.test.XMLUtil;

public class Client
{
	public static void main(String a[])
	{
		Color color;
		FillStyle fillStyle;
		Pen pen;
		
		color = (Color)XMLUtilPen.getBean("color");
		fillStyle = (FillStyle) XMLUtilPen.getBean("fillstyle");
		pen=(Pen)XMLUtilPen.getBean("pen");
		
		pen.setColor(color);
		pen.setFillStyle(fillStyle);
		pen.draw("矩形");
	}
}