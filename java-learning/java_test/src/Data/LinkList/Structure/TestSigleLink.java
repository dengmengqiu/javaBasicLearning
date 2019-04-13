package Data.LinkList.Structure;

import java.util.Iterator;

public class TestSigleLink {
	public static void main(String[] args) {
//		LinkList theList = new LinkList();
//
//		theList.insertFirst(22, 1.1);
//		theList.insertFirst(33, 2.2);
//		theList.insertFirst(44, 3.3);
//		theList.insertFirst(55, 4.4);
//
//		theList.displayList();
		
//		while(!theList.isEmpty()) {
//			Link delete = theList.deleteFirst();
//			System.out.println("delete: ");
//			delete.display();
//		}
		
//		theList.find(22).display();
//		theList.delete(22).display();
//		System.out.println(theList.find(22));

		long start = System.currentTimeMillis();
		System.out.println(1888_888_88 / 22222);
		System.out.println(System.currentTimeMillis() - start);

		long start1 = System.currentTimeMillis();
		System.out.println(1888_888_88 & 22222);
		System.out.print(System.currentTimeMillis() - start1);
	}
}


class Link{
	private int iData;
	private double dData;
	public Link next;
	
	public Link(int id, double dd) {
		iData = id;
		dData = dd;
	}
	
	public void display() {
		System.out.println("{" + iData+ "," + dData + "}");
	}

	public int getiData() {
		return iData;
	}

	public void setiData(int iData) {
		this.iData = iData;
	}

	public double getdData() {
		return dData;
	}

	public void setdData(double dData) {
		this.dData = dData;
	}
}

class LinkList{
	private Link first;
	
	public LinkList() {
		first = null;
	}
	
	public Link find(int key) {
		Link curLink = first;
		while(curLink.getiData() != key) {
			if(curLink.next != null) {
				curLink = curLink.next;
			}else {
				return null;
			}
		}
		return curLink;
	}
	
	public Link delete(int key) {
		Link curLink = first;
		Link previous = first;
		while(curLink.getiData() != key) {
			if(curLink.next != null) {
				previous = curLink;
				curLink = curLink.next;
			}else {
				return null;
			}
		}
		Link temp =  curLink;
		previous.next = curLink.next;
		return temp;
	}
	
	public boolean isEmpty() {
		return (first == null);
	}
	
	public void insertFirst(int id, double dd) {
		Link newLink = new Link(id, dd);
		newLink.next = first;
		first = newLink;
	}
	
	public Link deleteFirst() {
		Link temp = first;
		first = first.next;
		return temp;
	}
	
	public void displayList() {
		System.out.println("List (first-->last): ");
		Link current = first;
		while(current != null) {
			current.display();
			current = current.next;
		}
		System.out.println("");
	}
	
	public Link getFirst() {
		return first;
	}
	
	public ListIterator getIterator() {
		return new ListIterator(this);
	}
}