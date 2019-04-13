package Data.Structures.Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//解析算术表达式
public class AnlyArtExpress {
	public static void main(String[] args) {
		String input = null, output;
		//得到输入表达受
		while(true) {
			System.out.println("Enter infix: ");
			System.out.flush();
			try {
				input = getString();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(input.equals(null)) {
				break;
			}
			
			//得到后缀表达式
			InToPost theTrans = new InToPost(input);
			output = theTrans.doTrans();
			System.out.println("Postfix is " + output + '\n');
			
			//计算
			ParseFix p = new ParseFix(output);
			System.out.println(p.doParse());
		}	
	}
	
	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
}

//存储字符的栈
class CharStk{
	private int maxSize;
	private int top;
	private char[] stackArray;
	
	public CharStk(int size) {
		maxSize = size;
		stackArray = new char[maxSize];
		top = -1;
	}
	
	public void push(char c) {
		this.stackArray[++this.top] = c;
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
	
	public char peekN(int n) {
		return stackArray[n];
	}
	
	public int size() {
		return top + 1;
	}
	
	public void displayStack(String s) {
		System.out.print(s);
		System.out.print("Stack (bottom-->top):");
		for(int j = 0; j < size(); j++) {
			System.out.print(peekN(j) + ' ');
		}
		System.out.println("");
	}
}

//计算后继表达式
class InToPost{
	private CharStk theStack;
	private String input;
	private String output = "";
	
	public InToPost(String in) {
		input = in;
		theStack = new CharStk(in.length());
	}
	
	public String doTrans() {
		for(int j = 0; j < input.length(); j++) {
			char ch = input.charAt(j);
			switch(ch) {
				case '+':
				case '-':
					gotOper(ch, 1);
				case '*':
				case '/':
					gotOper(ch, 2);
					break;
				case '(':
					theStack.push(ch);
					break;
				case ')':
					gotParen(ch);
					break;
				default:
					output = output + ch;
					break;
			}
		}
		while(!theStack.isEmpty()) {
			output += theStack.pop();
		}
		return output;
	}
	
	public void gotOper(char opThis, int prec1) {
		while(!theStack.isEmpty()) {
			char opTop = theStack.pop();
			int prec2;
			if(opTop == '(') {
				theStack.push(opTop);
				break;
			}
			if(opTop == '+' || opTop == '-') {
				prec2 = 1;
			}else {
				prec2 = 2;
			}
			if(prec1 > prec2) {
				theStack.push(opTop);
				theStack.push(opThis);
				return;
			}else {
				output += opTop;
			}
		}
		theStack.push(opThis);
	}
	
	public void gotParen(char ch) {
		char opTop1 = 0;
		if(!theStack.isEmpty()) {
			opTop1 = theStack.pop();
		}
		while(opTop1 != '(' && !theStack.isEmpty()) {
			char opTop2 = theStack.pop();
			if(opTop2 == '(') {
				output += opTop1;
				return;
			}
			if(gotPre(opTop1) >= gotPre(opTop2)) {
				output += opTop1;
				opTop1 = opTop2;
			}else {
				output += opTop2;
			}
		}	

		//原书中算法 但没有考虑括号中有多项加减的问题
//		while(!theStack.isEmpty()){
//			char chx = theStack.pop();
//			if(chx == '(') {
//				break;
//			}else {
//				output += chx;
//			}
//		}
	}
	
	public int gotPre(char ch) {
		switch(ch) {
		case '+':
		case '-':
			return 1;
		case '*':
		case '/':
			return 2;
		default:
			output = output + ch;
			break;
		}
		return 0;
	}
}

//使用后继表达式进行计算
class ParseFix{
	private Stack stack;
	private String profix;
	
	public ParseFix(String fix) {
		stack = new Stack(fix.length());
		profix = fix;
	}
	
	public long doParse() {
		long count = 0;
		for(int i = 0; i < profix.length(); i++) {
			char ch = profix.charAt(i);
			if('0' <= ch && ch <= '9') {
				stack.push((int)(ch-'0'));
			}else {
				stack.display();
				long num1 = 1;
				long num2 = 1;
				if(!stack.isSurplusOne()) {
					num2 = stack.pop();
					num1 = stack.pop();
					System.out.print(num1 + " ");
					System.out.print(num2);
					System.out.println();
					System.out.println(ch);
					switch(ch) {
					case '+':
						count = num1 + num2;
						break;
					case '-':
						count = num1 - num2;
						stack.push(count);
						break;
					case '*':
						count = num1 * num2;
						break;
					case '/':
						count = num1 / num2;
						break;
					default:
						break;
					}
					stack.push(count);
				}else {
					return count;
				}
			}
		}
		return count;
	}
}
