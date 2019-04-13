package Pattern.Tempareture.demo;

import Pattern.Bridge.test.XMLUtilPen;

public class Client
{
    public static void main(String a[])
    {
        LocTemparature temparature;
        Panel pan;

        temparature = (LocTemparature) XMLUtilTemparature.getBean("China");
        pan = new rectPanel();

        pan.setTemparature(temparature.getTemparature());
        pan.displayTemparature();
    }
}
