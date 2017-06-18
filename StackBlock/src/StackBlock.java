import CITS2200.*;

/**
 * Write a description of class StackBlock1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StackBlock 
{
    private int item = -1;
    Object [] StackBlock;
    /**
     * Create an empty stack of size s
     * @param s size of stack
     */
    public StackBlock(int s) {
        StackBlock = new Object [s];
    }
    
    /**
     * @return true iff the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        if(item == -1) return true;
        return false;
    }
    
    /**
     * 
     * @return true iff the stack is full, false otherwise
     */
    public boolean isFull() {
        if(item + 1 == StackBlock.length)return true;
        return false;
    }
    
   /*public void push1(Object o) throws Overflow{
        int temp = 0;
        if(!isFull()) {
            if(!isEmpty()) {
                for(int i = StackBlock.length - 1; i < 1; i--) {
                    StackBlock[i] = StackBlock[i - 1];
                } StackBlock[0] = o;
                item++;
            } else {
                StackBlock[0] = o;
                item++;
            }
        } else {
            throw new Overflow("Stack is full");
        }
   }
   */
   /**
    * push Object o onto the top of the stack,
    * or throw an Overflow exception
    * if the stack is full
    * @param o
    * @throws Overflow
    */
   public void push(Object o) {
       if(!isFull()) {
           item++;
           StackBlock[item] = o;
        } 
    }
/**
 * @return remove and return the Object on the top of the stack
 * or throw an Underflow exception if the stack is empty
 * @throws Underflow
 */
   public Object pop() throws Underflow{
       
       if(!isEmpty()){
           Object o = StackBlock[item];
           item--;
           return o;
       }else {
            throw new Underflow("Stack is Empty");
        }
    }
   /**
    * @return the Object on top of the stack, 
    * or throw an Underflow exception
    * @throws Underflow
    */
   public void examine() throws Underflow{
       if(!isEmpty()) System.out.println(StackBlock[item]);
      throw new Underflow("Stack is empty");
    }
    
   /*public Object pop1() throws Underflow{
       if(!isEmpty()){
           Object o = StackBlock[0];
           for(int i = 0; i < StackBlock.length - 2; i++) {
               StackBlock[i] = StackBlock[i+1];
            }item--;
            StackBlock[StackBlock.length -1] = null;
            return o;
        } else {
            throw new Underflow("Stack is empty");
        }
    }
    */
   public static void main(String[] args){
	   StackBlock s = new StackBlock(0);
	   s.push("a");
	   s.push("b");
	   s.examine();
   }
}
