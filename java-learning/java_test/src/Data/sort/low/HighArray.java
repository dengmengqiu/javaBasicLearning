package Data.sort.low;

//普通数组包装 实现插入、删除、查找
public class HighArray {
	private int[] elems;
	private int length;
	
	public HighArray(int len) {
		elems = new int[len];
		length = 0;
	}
	
	public boolean find(int searchElem) {
		for(int  i = 0; i < length; i++) {
			if(elems[i] == searchElem) {
				return true;
			}
		}
		return false;
	}
	
	public void insert(int i) {
		elems[length] = i;
		length += 1;
	}
	
	public boolean delete(int j) {
		int i;
		for(i = 0; i < length; i++) {
			if(elems[i] == j) 
				break;
		}
		if(i >= length) {
			return false;
		}
		for(int k = i; k < length - 1; k++) {
			elems[k] = elems[k+1];
		}
		length = length - 1;
		return true;
	}
	
	public void display() {
		for(int i = 0; i < length; i++) {
			System.out.print(elems[i] + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		HighArray arr = new HighArray(10);
		for(int i = 1; i <= 10; i++) {
			arr.insert(i);
		}
		arr.display();
		
		arr.delete(1);
		arr.display();
		
		
		System.out.println(arr.find(3));
	}
}
