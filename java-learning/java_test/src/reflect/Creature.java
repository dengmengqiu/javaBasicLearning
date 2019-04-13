package reflect;

public class Creature<T> {
	public double weight;
	private T i;

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public void breath() {
		System.out.println("ºôÎü");
	}
}
