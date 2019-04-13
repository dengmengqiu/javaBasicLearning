package Alogrithm.about.recursion;

public class testEmininateRecursive {
	static int theNum = 13;
	static int theAnswer = 0;
	static int codePart = 1;
	static Param thisParam = null;
	static ParamStack stk = new ParamStack(1000);
	
	public static void main(String[] args) {
		while(!step()) {}
		System.out.println(theAnswer);
	}
	
	public static boolean step() {
		switch(codePart) {
		case 1:
			thisParam = new Param(theNum, 6);
			stk.push(thisParam);
			codePart = 2;
			break;
		case 2:
			thisParam = stk.peek();
			if(thisParam.num == 1) {
				theAnswer = 1;
				codePart = 5;
			}else {
				codePart = 3;
			}
			break;
		case 3:
			stk.push(new Param(thisParam.num - 1, 4));
			codePart = 2;
			break;
		case 4:
			thisParam = stk.peek();
			theAnswer = theAnswer + thisParam.num;
			codePart = 5;
			break;
		case 5:
			thisParam = stk.peek();
			codePart = thisParam.reAddress;
			stk.pop();
			break;
		case 6:
			return true;
		}
		return false;
	}
}

class Param{
	public Param(int thenum, int i) {
		num = thenum;
		reAddress = i;
	}
	public int num;
	public int reAddress;
}

class ParamStack{
	private int maxSize;
	private int top;
	private Param[] stackArray;
	
	public ParamStack(int size) {
		maxSize = size;
		stackArray = new Param[maxSize];
		top = -1;
	}
	
	public void push(Param value) {
		this.stackArray[++this.top] = value;
	}
	
	public Param pop(){
		return this.stackArray[this.top--];
	}
	
	public Param peek() {
		return this.stackArray[this.top];
	}
	
	public boolean isEmpty() {
		return (this.top == -1);
	}
	
	public boolean isFull() {
		return (this.maxSize == ++this.top);
	}
	
	public void display() {
		for(int i = 0; i <= top; i++) {
			System.out.print(stackArray[i] + "   ");
		}
		System.out.println();
	}
	
	public boolean isSurplusOne() {
		return top == 0;
	}
}