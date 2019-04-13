package Pattern.Bridge.test;

public class Dot implements FillStyle{

    @Override
    public void bepaint(String penType, String name) {
        System.out.println(penType + "使用风格点");
    }
}
