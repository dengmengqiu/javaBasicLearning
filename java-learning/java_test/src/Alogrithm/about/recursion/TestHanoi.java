package Alogrithm.about.recursion;


public class TestHanoi {
	public static void main(String[] args) {
		Hanoi h = new Hanoi(10);
		h.doHanoi();
	}
}

class Hanoi{
	private Stack stack1;//Ô´Ëþ
	private Stack stack2;
	private Stack stack4;//Ä¿±êËþ
	private int n;
	
	public Hanoi(int n) {
		this.stack1 = new Stack(n + 1);
		this.stack2 = new Stack(n + 1);
		this.stack4 = new Stack(n + 1);
		this.n = n;
	}
	
	public void doHanoi() {
		for(int i = n; i >= 1; i--) {
			stack1.push(i);
		}
		move(n, stack1, stack2, stack4);
		for(int i = 0; i < n; i++) {
			System.out.println(stack4.pop());
		}
	}	
	
	public void move(int n, Stack from, Stack inter, Stack to) {
		Stack sfrom = from, sinter = null, sto = to;
		if(from == stack1) {
			if(to == stack2) {
				sinter = stack4;
			}else {
				sinter = stack2;
			}
		}else if(from == stack2) {
			if(to == stack1) {
				sinter = stack4;
			}else {
				sinter = stack1;
			}
		}else if(from == stack4) {
			if(to == stack1) {
				sinter = stack2;
			}else {
				sinter = stack1;
			}
		}
		if(n <= 0) {
			return;
		}
		if(n == 1) {
			sto.push(sfrom.pop());
			return;
		}
		if(n == 2) {
			sinter.push(sfrom.pop());
			sto.push(sfrom.pop());
			sto.push(sinter.pop());
			return;
		}
		if(n == 3) {
			sto.push(sfrom.pop());
			sinter.push(sfrom.pop());
			sinter.push(sto.pop());
			sto.push(sfrom.pop());
			sfrom.push(sinter.pop());
			sto.push(sinter.pop());
			sto.push(sfrom.pop());
			return;
		}
		move(n-1, sfrom, inter, inter);
		to.push(from.pop());
		move(n-1, inter, inter, to);
	}
}

class Stack{
	private int maxSize;
	private int top;
	private int[] stackArray;
	
	public Stack(int size) {
		maxSize = size;
		stackArray = new int[maxSize];
		top = -1;
	}
	
	public void push(int value) {
		++this.top;
		this.stackArray[this.top] = value;
	}
	
	public int pop() {
		return this.stackArray[this.top--];
	}
	
	public int peek() {
		if(!isEmpty()) {
			return this.stackArray[this.top];	
		}
		return 0;
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