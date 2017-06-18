import CITS2200.*;


public class PriorityQueueLinked<E> implements PriorityQueue<E>
{
	int count1;
	private class Link {
		E element;
		int priority;
		Link next;
		
		
		public Link(E e, int p, Link n){
			this.element = e;
			this.priority = p;
			this.next = n;
		}
	}
	private Link front;
	private Link l;
	
	public PriorityQueueLinked() {
		front = null;
	}

	@Override
	public E dequeue() throws Underflow {
		if(!isEmpty()) {
			E temp = (E) front.element;
			front = front.next;
			return temp;
		}
		else throw new Underflow("Empty Queue");
	}

	@Override
	public void enqueue(E e, int p) throws IllegalValue {
		if(isEmpty() || p > front.priority){
			front = new Link(e, p, front);
			count1++;
		} else {
			Link l = front;
			while(l.next != null && l.next.priority >= p){
				l = l.next;
			}
			l.next = new Link(e, p, l.next);
			count1++;
		}
		
	}

	@Override
	public E examine() throws Underflow {
		if(!isEmpty()) {
			return (E) front.element;
		}
		else throw new Underflow ("Empty Queue");
	}

	@Override
	public boolean isEmpty() {
		return front == null;
	}

	@Override
	public Iterator<E> iterator() {
		return new qiterator();
	}
	
	private class qiterator implements Iterator<E> {
		private QueueBlock q;
		private qiterator() {
			q = new QueueBlock(count1);
			enqueue();
		}
		
		public boolean hasNext(){
			if(q.isEmpty()) return false;
			return true;
		}
		
		public E next() throws OutOfBounds {
			if(hasNext()){
				return (E) q.dequeue();
			}else {
		     throw new OutOfBounds("no next element");
			}
		}
		
		public void enqueue() {
			l = front;
			while(l != null){
				q.enqueue(l.element);
				l = l.next;
			}
		}
	}
	private class QueueBlock {
		private Object [] items;
		private int first, last;
		
		public QueueBlock(int size){
			items = new Object [size];
			first = 0;
			last = -1;
		}
		public void enqueue (Object a) throws Overflow {
			if(last == items.length - 1) last = -1;
			items[++last] = a;
		}
		
		public Object dequeue()  {
			Object temp = items[first++];
			if(first == items.length) first = 0;
			return temp;
		}
		public boolean isEmpty() {
			return last +1 == first || first+items.length -1 == last;
		}
		
		
	}
	public static void main(String [] args) {
		PriorityQueueLinked p = new PriorityQueueLinked();
		p.enqueue("A", 3);
		p.examine();
		p.iterator();
		p.iterator().next();
	}
	
    
}
