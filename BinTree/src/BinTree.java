import CITS2200.*;
/**
 * Write a description of class BinTree here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BinTree<E> extends BinaryTree<E> {


	// instance variables - replace the example below with your own
	
    public BinTree() {
        super();
    }
    
    public BinTree(E item, BinaryTree b1, BinaryTree b2) {
        super(item, b1, b2);
    }
    
    public boolean equals(Object o) {
    	if(o == null && this.isEmpty()) return true;
    	if(o instanceof BinaryTree) return check((BinaryTree) o, this);
    	return false;
    }
     private boolean check (BinaryTree o1, BinaryTree o2) {
    	 if(o1.isEmpty() && o2.isEmpty()) return true;
    	 if(o1.isEmpty() || o2.isEmpty()) return false;
    	 return o1.getItem().equals(o2.getItem()) && check(o1.getLeft(), o2.getLeft())
    			  && check(o1.getRight(), o2.getRight());
     }
    
    /**public boolean equals (Object o) {
    	if(o == null && this.isEmpty())return true;
    	if ( o instanceof BinaryTree) {
    		BinaryTree<E> b = (BinaryTree<E>) o;
    		if(b.isEmpty() && this.isEmpty())return true;
    		if(b.isEmpty() || this.isEmpty() || b.getItem() != this.getItem()){
    			return false;
    		}else {
    			do {
    				b = b.getLeft(); getLeft();
    				if(b.getItem() != this.getItem()) return false;
    			} while(!b.getLeft().isEmpty() && !this.getLeft().isEmpty());
    		}
    		do {
    			b = b.getRight(); getRight();
    			if(b.getItem() != this.getItem())return false;
    		} while(!b.getLeft().isEmpty() && !this.getLeft().isEmpty());
    		
    		if(b.getLeft().isEmpty() && this.getLeft().isEmpty() &&
    				b.getRight().isEmpty() && this.getRight().isEmpty()) return true;
    	}return false; 
    }*/
    
    
	public Iterator<E> iterator(){
		return new qiterator ();
    }
    
    private class qiterator implements Iterator<E>
    {
    	private QueueIterator q;
    	
    	private qiterator (){
    		q = new QueueIterator();
    		
    	}
    
    public boolean hasNext() {
    	if(getLeft().isEmpty() && getRight().isEmpty())return false;
    	
    	return true;
    }
    
    public E next() throws OutOfBounds{
    	if(q.isEmpty()) preorder(getItem());
    	if(hasNext()){
    		Object o = q.examine();
    		q.dequeue();
    		/**if(!getLeft().isEmpty()){
    			getLeft();
    			q.enqueue(getItem());
    		} else if (!getRight().isEmpty()) {
    			getRight();
    			q.enqueue(getItem());
    		}*/
		
		return (E) o;
    	} else throw new OutOfBounds("no next element");
    	}
    
    public void preorder(E item){
    	if(getItem() == null) return;
    	q.enqueue(getItem());
    	if(!getLeft().isEmpty())preorder(getLeft().getItem());
    	if(!getRight().isEmpty())preorder(getRight().getItem());
    }
    }
    
    private class QueueIterator implements Queue {
    	private Link first;
    	private Link last;
    	
    	public QueueIterator () {
    		first = null;
    		last = null;
    		}
    	
    	public boolean isEmpty() {
    		return first == null;
    	}
    	
    	public Object examine () throws Underflow {
    		if(!isEmpty()) return first.item;
    		else throw new Underflow("examing empty queue");
    	}
    	
    	public Object dequeue () throws Underflow {
    		if(!isEmpty()) {
    			Object o = first.item;
    			first = first.successor;
    			if(isEmpty()) last = null;
    			return o;
    		} else throw new Underflow("dequeuing from empty queue");
    	}
    	
    	public void enqueue (Object a) {
    		if(isEmpty()) {
    			first = new Link (a, null);
    			last = first;
    		} else {
    			last.successor = new Link (a, null);
    			last = last.successor;
    		}
    		}
    	}
 }
    
    

