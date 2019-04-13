package Data.sort.low;

//ÓÐÐò´æ´¢Êý×é
public class OrderedArray {
	private long[] elems;
	private int len;
	
	public OrderedArray(int len) {
		elems = new long[len];
		this.len = 0;
	}
	
	public int find(long searchElem) {
		if(this.len == 0) {
			return -1;
		}
		int lowerRange = 0;
		int upperRange = this.len - 1;
		int curin;
		
		while(true) {
			curin = (upperRange + lowerRange) / 2;
			if(elems[curin] < searchElem) {
				lowerRange = curin + 1;
			}else if(elems[curin] == searchElem) {
				return curin;
			}else {
				upperRange = curin - 1;
			}
			
			if(lowerRange > upperRange) {
				return -1;
			}
		}
	}
	
	public void insert(long elem) {
		if(this.len == 0) {
			elems[0] = elem;
			this.len += 1;
			return;
		}
		for(int i = this.len; i > 0; i--) {
			if(elems[i-1] > elem) {
				elems[i] = elems[i - 1];
			}else if(elems[i-1] <= elem){
				elems[i] = elem;
				this.len += 1;
				return;
			}
		}
		elems[0] = elem;
		this.len += 1;
	}
	
	public boolean delete(long elem) {
		if(this.len == 0) {
			return false;
		}
		int lowerRange = 0;
		int upperRange = this.len - 1;
		int curin;
		int loc;
		while(true) {
			curin = (upperRange + lowerRange) / 2;
			if(elems[curin] < elem) {
				lowerRange = curin + 1;
			}else if(elems[curin] == elem) {
				loc = curin;
				for(int i = loc; i < this.len - 1; i++) {
					elems[i] = elems[i + 1];
				}
				this.len--;
				return true;
			}else {
				upperRange = curin - 1;
			}
			
			if(lowerRange > upperRange) {
				return false;
			}
		}
	}
	
	public void display() {
		for(int i = 0; i < this.len; i++) {
			System.out.print(elems[i] + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] argas) {
		OrderedArray arr = new OrderedArray(10);
		for(int i = 10; i >= 1; i--) {
			arr.insert(i);
		}
		arr.display();
		
		System.out.println(arr.find(11));
		arr.delete(3);
		arr.display();
		
		arr.delete(5);
		arr.display();
		
		arr.delete(7);
		arr.display();
		
		arr.insert(12);
		arr.display();
		
		arr.insert(12);
		arr.display();

		arr.insert(45);
		arr.display();
	}
}
