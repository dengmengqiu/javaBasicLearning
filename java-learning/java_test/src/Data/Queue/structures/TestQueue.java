package Data.Queue.structures;

public class TestQueue {
	public static void main(String[] args) {
		Queue que = new Queue(10);
		for(int i = 1; i <= 5; i++) {
			que.insert(i);
		}	
		
		while(!que.isFull()) {
			que.insert(0);
		}
		
		System.out.println("cwdcaw");
		
		while(!que.isEmpty()) {
			for(int i = 0; i < que.size(); i++) {
				System.out.println(que.remove());
			}
		}
	}
}

class Queue{
	private int maxSize;
	private long[] queArray;
	private int front;
	private int rear;
	private int num;
	
	public Queue(int s) {
		maxSize = s + 1;
		queArray = new long[maxSize];
		front = 0;
		rear = -1;
	}
	
	public void insert(long j) {
		if(rear == maxSize - 1) 
			rear = -1 ;
		queArray[++rear] = j;	
	}
	
	public long remove() {
		long temp = queArray[front++];
		if(front == maxSize)
			front = 0;
		return temp;
	}
	
	public long peekFront() {
		return queArray[front];
	}
	
	public boolean isEmpty() {
		return (rear == (front + maxSize - 1))|| front == (rear + 1);
	}
	public boolean isFull() {
		return (rear + 2) == front  || (front + maxSize - 2) == rear;
	}
	
	public int size() {
		return front <= rear ? rear - front + 1 : maxSize - (front - rear) + 1;
	}
}
