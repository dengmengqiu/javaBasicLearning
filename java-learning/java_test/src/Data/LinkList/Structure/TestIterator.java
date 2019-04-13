package Data.LinkList.Structure;

public class TestIterator {
	public static void main(String[] args) {
		LinkList theList = new LinkList();
		
		theList.insertFirst(22, 1.1);
		theList.insertFirst(33, 2.2);
		theList.insertFirst(44, 3.3);
		theList.insertFirst(55, 4.4);
		
		theList.displayList();
		
		ListIterator iterator= theList.getIterator();
		while(iterator.hasNextLink()) {
			iterator.nextLink().display();
		}
	}
}

class ListIterator{
	private Link cur;
	private Link pre;
	private LinkList ourList;
	
	public ListIterator(LinkList list) {
		ourList = list;
		reset();
	}
	
	public void reset() {
		cur = ourList.getFirst();
		pre = null;
	}
	
	public boolean atEnd() {
		return cur.next == null;
	}
	
	public Link getCurrent() {
		return cur;
	}
	
	public void insertAfter(int id, double dd) {
		Link newLink = new Link(id, dd);
		if(ourList.isEmpty()) {
			ourList.insertFirst(id, dd);
		}else{
			newLink.next = cur.next;
			cur.next = newLink;
			nextLink();
		}
	}
	
	public void  insertBefore(int id, double dd) {
		Link newLink = new Link(id, dd);
		if(ourList.isEmpty()) {
			ourList.insertFirst(id, dd);
		}else{
			if(pre != null) {
				pre.next = newLink;
			}
			newLink.next = cur;
			cur = newLink;
			nextLink();
		}
	}
	
	public Link deleteCurrent() {
		Link temp = cur;
		if(pre == null) {
			temp = ourList.deleteFirst();
			reset();
		}else {
			pre.next = cur.next;
			if(atEnd()) {
				reset();
			}else {
				cur = cur.next;
			}
		}
		return temp;
	}
	
	public boolean hasNextLink() {
		return cur != null;
	}
	
	public Link nextLink() {
		pre = cur;
		cur = cur.next;
		return pre;
	}
}