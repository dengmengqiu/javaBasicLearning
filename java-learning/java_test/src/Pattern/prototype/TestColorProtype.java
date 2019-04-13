package Pattern.prototype;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Hashtable;

public class TestColorProtype {
    public static void main(String[] args){
        colorManger cm = new colorManger();
        System.out.print(cm.get("A"));
    }
}

class colorManger{
    Hashtable<String, colorPrototype> table;

    public colorManger(){
        table = new Hashtable<>();
        colorA colorA = new colorA(1);
        colorB colorB= new colorB(1, "我就是我是不一样的颜色");

        this.add("A", colorA);
        this.add("B", colorB);
    }

    public void add(String str, colorPrototype ctype){
        table.put(str, ctype);
    }

    public colorPrototype get(String key){
        colorPrototype ctype = table.get(key);
        return ctype;
    }
}


interface colorPrototype{
    public colorPrototype clone();
}

class colorA implements  colorPrototype{
    String meColor = "A";
    Integer alpha;

    public colorA(Integer alp){
        this.alpha = alp;
    }

    @Override
    public colorA clone(){
        Integer a = alpha;
        return new colorA(a);
    }

    @Override
    public String toString(){
        return "我是颜色A; " + " 我的透明度是：" + alpha;
    }
}

class colorB implements  colorPrototype{
    String meColor = "B";
    Integer alpha;
    String otherInfo;

    public colorB(int alp, String otherInfo){
        this.alpha = alp;
        this.otherInfo = otherInfo;
    }

    @Override
    public colorB clone(){
        Integer a = alpha;
        return new colorB(a, this.otherInfo);
    }

    @Override
    public String toString(){
        return "我是颜色B; " + " 我的透明度是：" + alpha
                + "; 我的其他信息： " + otherInfo;
    }
}