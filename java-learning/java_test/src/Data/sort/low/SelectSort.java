package Data.sort.low;

public class SelectSort {
	public static void main(String[] args) {
		ArraySel a = new ArraySel(10);
		a.insert(-1);
		a.insert(-3);
		a.insert(-2);
		a.insert(4);
		a.insert(-4);
		a.insert(1);
		a.display();
		a.selectSort();
		a.display();
	}
}

class ArraySel{
	private long[] arr;
	private int len;
	
	public ArraySel(int l) {
		this.arr = new long[l];
		this.len = 0;
	}
	
	public void insert(long value) {
		this.arr[this.len] = value;
		this.len++;
	}
	
	public void display() {
		for(int  i = 0; i < this.len; i++) {
			System.out.print(this.arr[i] + " ");
		}
		System.out.println();
	}
	
	public void selectSort() {
		for(int i = 0; i < this.len - 1; i++) {
			for(int j = i + 1; j < this.len; j++) {
				if(this.arr[j] < this.arr[i]) {
					swap(i, j);
				}
			}
		}
	}
	
	public void swap(int i, int j) {
		long temp = arr[j];
		arr[j] = arr[i];
		arr[i] = temp;
	}
}

