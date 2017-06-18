import CITS2200.*;
/**
 * Write a description of class DequeCyclic here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DequeCyclic implements Deque<Object>
{
    private Object [] deque;
    private int s;
    private int items;
    private int left;
    private int right;
    public DequeCyclic (int s) {
        this.s = s;
        deque = new Object [s];
        items = 0;
        left = 0;
        right = 0;
    }
    
    public boolean isEmpty() {
        if(items == 0)return true;
        return false;
    }
    
    public boolean isFull() {
        if(items == s)return true;
        return false;
    }
    
    public void pushLeft(Object c) throws Overflow {
        if(isEmpty()) {
            deque[left] = c;
            items++;
        } else if(!isFull()) {
        	left = left - 1;
        	if(left == -1) left = s - 1;
        	deque[left] = c;
            items++;
        }else { throw new Overflow("Deque is Full");
        }
    }
    
    public void pushRight(Object c) throws Overflow {
        if(isEmpty()) {
            deque[right] = c;
            items++;
        }else if(!isFull()) {
            right = right + 1;
            if(right == s) right = 0;
            deque[right] = c;
            items++;
        } else { throw new Overflow("Deque is Full");
        }
    }
    
    public Object peekLeft() throws Underflow {
        
    	if(!isEmpty()){
    	    return deque[left];
    	   } else {
        throw new Underflow("Deque is empty");
    }
    }
    
    public Object peekRight() throws Underflow {
        
        if(!isEmpty()){
            return deque[right];
        }else {
            throw new Underflow("Deque is empty");
    }
    }
    
    
    public Object popLeft() throws Underflow{
        if(items == 1) {
            Object temp = deque[left];
            left = 0;
            right = 0;
            items--;
            return temp;
        } else if(!isEmpty()) {
            Object temp = deque[left];
            left = left + 1;
            if(left == s) left = 0;
            items--;
            return temp;
        }else {
            throw new Underflow("Deque is empty");
        }
    }
    
    public Object popRight() throws Underflow{
        if(items == 1) {
            Object temp = deque[right];
            left = 0;
            right = 0;
            items--;
            return temp;
        } else if(!isEmpty()) {
            Object temp = deque[right];
            right = right - 1;
            if(right == -1) right = s - 1;
            items--;
            return temp;
        } else {
            throw new Underflow("Deque is empty");
        }
    }
    }

