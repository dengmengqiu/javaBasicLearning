package Data.LinkList.Structure;

public class TestDoublyLinkList {
	public static void main(String[] args) {
		DoublyLinkList theList = new DoublyLinkList();
		
		theList.insertFirst(11);
		theList.displayBackward();
		theList.insertLast(22);
		theList.displayBackward();
		theList.insertAfter(22, 33);
		theList.displayBackward();
		if(theList.isEmpty()) {
			theList.deleteFirst();
		}
	}
}

class DoublyLink{
	private double dData;
	public DoublyLink next;
	public DoublyLink previous;
	
	public DoublyLink(double dd) {
		dData = dd;
	}
	
	public double getdData() {
		return dData;
	}
	public void setdData(long dData) {
		this.dData = dData;
	}
	
	public void display() {
		System.out.println("{this: " + dData + "}");
	}
}

class DoublyLinkList{
	private DoublyLink first;
	private DoublyLink last;
	
	public DoublyLinkList() {
		first = null;
		last = null;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public void insertFirst(double dd) {
		DoublyLink newLink = new DoublyLink(dd);
		if(isEmpty()) {
			last = newLink;
		}else {
			first.previous = newLink;
		}
		newLink.next = first;
		first = newLink;
	}
	
	public void insertLast(double dd) {
		DoublyLink newLink = new DoublyLink(dd);
		if(isEmpty()) {
			first = newLink;
		}else {
			last.next = newLink;
			newLink.previous = last;
		}
		last = newLink;
	}
	
	public DoublyLink deleteFirst() {
		DoublyLink temp = first;
		if(last == first) {
			last = null;
		}
		first = first.next;
		first.previous = null;
		return temp;
	}
	
	public DoublyLink deleteLast() {
		DoublyLink temp = last;
		if(last == first) {
			first = null;
		}else {
			last.previous.next = null;
		}
		last = last.previous;
		return temp;
	}
	
	public boolean insertAfter(double key, double dd) {
		DoublyLink cur = first;
		if(cur == null) {
			return false;
		}
		while(cur.getdData() != key) {
			cur = cur.next;
			if(cur == null) 
				return false;
		}
		DoublyLink newLink = new DoublyLink(dd);
		if(cur != last) {
			newLink.next = cur.next;
			cur.next.previous = newLink;
		}else {
			last = newLink;
		}
		cur.next = newLink;
		newLink.previous = cur;
		return true;
	}
	
	public DoublyLink deleteKey(double dd) {
		DoublyLink cur = first;
		if(cur == null) {
			return null;
		}
		
		while(cur.getdData() != dd) {
			cur = cur.next;
			if(cur == null) {
				return null;
			}		
		}
		
		DoublyLink temp = cur;
		if(cur == first) {
			first = cur.next;
			first.previous = null;
		}else {
			cur.previous.next = cur.next;
		}
		if(cur == last) {
			last = last.previous;
			last.next = null;
		}else {
			cur.next.previous = cur.previous;
		}
		return temp;
	}
	
	public void displayForward() {
		System.out.println("List(first-->last): ");
		DoublyLink cur = first;
		while(cur != null) {
			cur.display();
			cur = cur.next;
		}
		System.out.println();
	}
	
	public void displayBackward() {
		System.out.println("List(last-->first): ");
		DoublyLink cur = last;
		while(cur != null) {
			cur.display();
			cur = cur.previous;
		}
		System.out.println();
	}
}