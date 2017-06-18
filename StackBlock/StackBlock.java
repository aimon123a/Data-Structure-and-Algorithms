import java.util.List;
import java.util.ArrayList;
import CITS2200.*;
public class StackBlock

{
    private List<Integer> stack;
    int s;
    public StackBlock(int s) {
        this.s = s;
        stack = new ArrayList<Integer>(s);
    }

    public boolean isEmpty() {
       if(stack.size() == 0) return true;
       return false;
     }
     
    public boolean isFull() {
        return true;
    }
    
    public void push(int i) throws Overflow{
        if(stack.size() < s){
            stack.add(0,i);
        }else {
            throw new Overflow("Stack overflows");
        }
     }
     
    public int examine() throws Underflow{
        if(!stack.isEmpty()){
           return stack.get(0);
        } else {
           throw new Underflow("Stack underflow");
        }
     }

     public int pop() throws Underflow { 
        if(!stack.isEmpty()){
           int i= stack.get(0);
           stack.remove(0);
           return i;
        } else{
           throw new Underflow("Stack underflow");
        }
    }

     public void display() {
        for(int i = 0;i<stack.size();i++) {
            System.out.println(stack.get(i));
        }
    }

 }
 
 