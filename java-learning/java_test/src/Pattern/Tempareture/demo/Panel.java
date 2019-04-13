package Pattern.Tempareture.demo;

public abstract class Panel {
    protected float temparature;

    public void setTemparature(float temparature) {
        this.temparature = temparature;
    }
    abstract void displayTemparature();
}
