package Pattern.simpleFactory;

public class client {
    public static void main(String[] args){
        String brand = XmlUtilTv.getBrandName();
        try {
            TV tv = TvFactory.productTv(brand);
            tv.play();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
