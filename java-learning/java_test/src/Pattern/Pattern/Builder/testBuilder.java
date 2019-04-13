package Pattern.Pattern.Builder;

public class testBuilder {
    public static void main(String[] args){

    }
}


class Director{
    private PeppaBuilder builder;

    public Director(PeppaBuilder builder){
        this.builder = builder;
    }

    public void setBuilder(PeppaBuilder builder){
        this.builder = builder;
    }

    public void constructor(){

    }
}

class PeppaBuilder{

}
