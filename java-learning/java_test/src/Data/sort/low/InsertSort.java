package Data.sort.low;

public class InsertSort {
	public static void main(String[] args) {
		ArrayIns a = new ArrayIns(10);
		a.insert(-1);
		a.insert(-3);
		a.insert(-2);
		a.insert(4);
		a.insert(-4);
		a.insert(1);
		a.display();
		a.insertSort();
		a.display();
	}
}

class ArrayIns{
	private long[] arr;
	private int len;
	
	public ArrayIns(int l) {
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
	
	public void insertSort() {
		for(int i = 1; i < this.len; i++) {
			if(this.arr[i] <= this.arr[i-1]) {
				continue;
			}
//			long temp = this.arr[i];
//			for(int k = i; k > 0 && this.arr[k-1] > temp; k--) {
//				if(this.arr[k-1] > temp) {
//					this.arr[k] = this.arr[k-1];
//					if((k-1) == 0) {
//						this.arr[0] = temp;
//					}
//				}else {
//					this.arr[k-1] = temp;
//					break;
//				}
//			}
			
			long temp = this.arr[i];
			int k = i;
			while(k > 0 && this.arr[k-1] < temp) {
				this.arr[k] = this.arr[k-1];
				k--;
			}
			this.arr[k] = temp;
		}
	}
	
	public void swap(int i, int j) {
		long temp = arr[j];
		arr[j] = arr[i];
		arr[i] = temp;
	}
}
