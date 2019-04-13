package Data.Structures.Stack;

import java.util.Scanner;

public class StackX {
	public static void main(String[] args) {
		//������ͨջ��
//		Stack s = new Stack(10);
//		for(int i = 0; i < 10; i++) {
//			s.push(i);
//		}
//		
//		while(!s.isEmpty()) {
//			System.out.println(s.pop());
//		}
		
		//���鵥������
//		System.out.println("������һ�����ʣ�");
//		Scanner sc = new Scanner(System.in);
//		reverseWord rw = new reverseWord(sc.nextLine());
//		sc.close();
//		while(!rw.isEmpty()) {
//			System.out.print(rw.pop());
//		}
		
		//����ָ���ƥ��
		String str = "c{csd}c[]s{([])}d";
		BracketCheck bc = new BracketCheck(str);
		System.out.println(bc.checkBracket());
	}
}

class Stack{
	private int maxSize;
	private int top;
	private long[] stackArray;
	
	public Stack(int size) {
		maxSize = size;
		stackArray = new long[maxSize];
		top = -1;
	}
	
	public void push(long value) {
		this.stackArray[++this.top] = value;
	}
	
	public long pop() {
		return this.stackArray[this.top--];
	}
	
	public long peek() {
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

//��������
class reverseWord{
	private int maxSize;
	private int top;
	private char[] stackArray;
	
	public reverseWord(String str) {
		this.stackArray = str.toCharArray();
		this.maxSize = stackArray.length;
		this.top = maxSize - 1;
	}
	
	public void push(char value) {
		this.stackArray[++this.top] = value;
	}
	
	public char pop() {
		return this.stackArray[this.top--];
	}
	
	public char peek() {
		return this.stackArray[this.top];
	}
	
	public boolean isEmpty() {
		return (this.top == -1);
	}
	
	public boolean isFull() {
		return (this.maxSize == ++this.top);
	}
}


//�ָ���ƥ��
class charStack{
	private int maxSize;
	private int top;
	private char[] stackArray;
	
	public charStack(int l) {
		this.maxSize = l;
		this.stackArray = new char[l];
		this.top = - 1;
	}
	
	public void push(char value) {
		this.stackArray[++this.top] = value;
	}
	
	public char pop() {
		return this.stackArray[this.top--];
	}
	
	public char peek() {
		return this.stackArray[this.top];
	}
	
	public boolean isEmpty() {
		return (this.top == -1);
	}
	
	public boolean isFull() {
		return (this.maxSize == ++this.top);
	}
}

class BracketCheck{
	private char[] charArray;
	private charStack stack;
	
	public BracketCheck(String s) {
		stack = new charStack(s.length());
		charArray = s.toCharArray();
	}
	
	public boolean checkBracket() {
		for(char c : charArray) {
			switch (c) {
			case '{':
				stack.push(c);
				break;
			case '(' :
				stack.push(c);
				break;
			case '[': 
				stack.push(c);
				break;
			case ')':
				if(!stack.isEmpty() && stack.peek() == '(') {
					stack.pop();
				}else {
					stack.push(c);
				}
				break;
			case ']':
				if(!stack.isEmpty() && stack.peek() == '[') {
					stack.pop();
				}else {
					stack.push(c);
				}
				break;
			case '}':
				if(!stack.isEmpty() && stack.peek() == '{') {
					stack.pop();
				}else {
					stack.push(c);
				}
				break;
			default:
				break;
			}
		}
		return stack.isEmpty();
	}
}
	