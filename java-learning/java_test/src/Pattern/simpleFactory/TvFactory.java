package Pattern.simpleFactory;

public class TvFactory {
    public static TV productTv(String brand) throws Exception {
        if(brand.equals("Haier")){
            System.out.println("生产了一台Haier电视机");
            return new Haier();
        }else if(brand.equals("Hisense")){
            System.out.println("生产了一台Hisense电视机");
            return new Haier();
        }else{
            throw new Exception("工厂无法生产指定品牌电视机");
        }
    }
}
