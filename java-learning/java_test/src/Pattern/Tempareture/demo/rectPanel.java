package Pattern.Tempareture.demo;

public class rectPanel extends Panel {
    String name = "矩形表盘";
    @Override
    void displayTemparature() {
       System.out.print(this.name + "现在显示温度" + this.temparature);
    }
}
