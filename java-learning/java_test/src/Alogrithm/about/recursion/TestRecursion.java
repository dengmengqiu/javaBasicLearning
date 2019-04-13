package Alogrithm.about.recursion;

//测试全排列
public class TestRecursion{
	public static void main(String[] args){
		AnagramApp an = new AnagramApp("abcd");
		an.permutate();
	}
}

//全排列
class AnagramApp{
	private int count;
	private char[] arrChar;
	
	public AnagramApp(String str){
		arrChar = str.toCharArray();
		count = 0;
	}
	
	public void permutate(){
		doAnagram(arrChar.length);
	}
	
	public void doAnagram(int newSize){
		if(newSize == 0) {
			return;
		}
		for(int i = newSize; i > 0; i--){
			doAnagram(newSize -1);
			if(newSize == 1){
				displayAlper();
			}
			rotate(newSize-1);
		}
	}
	
	public void rotate(int size){
		char temp = arrChar[size];
		int i;
		for(i = size; i > 0; i--){
			arrChar[i] = arrChar[i-1];
		}
		arrChar[0] = temp;
	}
	
	public void displayAlper(){
		System.out.print(" "+ (++count) + ": ");
		for(int i = 0; i < arrChar.length; i++){
			System.out.print(arrChar[i] + "  ");
		}
		if(count % 6 == 0){
			System.out.println();
		}
	}
}