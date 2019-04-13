package Pattern.Adapter.test;

public class AnimalAdapter implements Robot{
    private Animal imitateAnimal;

    public AnimalAdapter(String animal)throws Exception{
        Class c = Class.forName(animal);
        this.imitateAnimal = (Animal) c.newInstance();
    }

    @Override
    public void cry() {
        this.imitateAnimal.cry();
    }

    @Override
    public void move() {
        this.imitateAnimal.move();
    }
}
