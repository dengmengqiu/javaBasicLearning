package Pattern.Bridge.test;

public class line  implements FillStyle {
    @java.lang.Override
    public void bepaint(String penType, String name) {
        System.out.println(penType + "使用风格线条");
    }
}
