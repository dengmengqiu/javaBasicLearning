package Data.sort.low;

public class BubbleSort {
	public static void main(String[] args) {
		BubbleArray a = new BubbleArray(10);
		a.insert(-1);
		a.insert(-3);
		a.insert(-2);
		a.insert(4);
		a.insert(-4);
		a.insert(1);
		a.display();
		a.bubblesort();
		a.display();
	}
}

class BubbleArray{
	private int[] arr;
	private int len;
	
	public BubbleArray(int len) {
		arr = new int[len];
		this.len = 0;
	}
	
	public void insert(int value) {
		this.arr[this.len] = value;
		this.len++;
	}
	
	public void display() {
		for(int i = 0; i < this.len; i++) {
			System.out.print(this.arr[i] + " ");
		}
		System.out.println();
	}
	
	public void bubblesort() {
		for(int i = this.len; i > 1; i--) {
			for(int j = 0; j < i; j++ ) {
				if(this.arr[j] > this.arr[j + 1]) {
					swap(j, j + 1);
				}
			}
		}
	}
	
	public void swap(int i, int j) {
		int temp = arr[j];
		arr[j] = arr[i];
		arr[i] = temp;
	}
}
