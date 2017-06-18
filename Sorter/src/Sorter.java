import java.util.Arrays;

import CITS2200.Sort;

/**
* A class to give a comaprative view of common sorting algorithms.
* The maintains a private static variable that counts the number 
* of array assignments that are performed (as an approximate measure
* of the complexity of the algorithm.
* @author Tim French. 
**/

public class Sorter implements Sort
{
   private int count;
   private long[] numbers;
   /**
	* Returns the number of array assignment operations 
	* performed by this class since the count variable was rest.
	* @return the number of assignments
	**/
	public int getCount(){
		return count;
	}	
		
	/**
	*Resets the counter variable to 0
	**/
	public void reset(){
		count = 0;
	}
	
	/**
	* Executes the insertion sort algorithm sorting the argument array.
	* There is no return as the parameter is to be mutated.
	* @param a the array of long integers to be sorted
	**/
	public void insertionSort(long[] a){
    		//insert your code here
    		for(int i = 1; i<a.length; i++) {
    			//set temporary varible to the index of i in array a
    		    long temp = a[i];
    		    int j;
    		    for(j = i - 1; j>= 0 && temp < a[j]; j--) {
    		    	//if j is larger than i, make i to j
    		        a[j+1] = a[j];
    		        count++;
    		        
    		    }count++;
    		    //and set j back to i
    		   
    		    
    		    a[j+1] = temp;
    		    
    		    
    		    System.out.println("j + 1 = " + j);
    		    System.out.println("a = " + Arrays.toString(a));
    		}
	}

	
	/**
	* Executes the quick sort algorithm sorting the argument array.
	* There is no return as the parameter is to be mutated.
	* @param a the array of long integers to be sorted
	**/
	public void quickSort(long[] a){
		//insert your code here.
		//you will also need to provide some private methods
		int n = 0;
		if(a == null || a.length == 0) return;
		//using the array number [] to be the clown of array a
		this.numbers = a;
		n = a.length;
		quicksort(0, n - 1);
	}
	
	private void quicksort(int low, int high) {
	    int i = low;
	    int j = high;
	    long pivot = numbers[low + (high - low)/2];
	    
	    while(i <= j) {
	    	//if num[i] is smaller than the pivot, move on to next index
	        while(numbers[i] < pivot) {
	            i++;
	           }
	        while(numbers[j] > pivot) {
	        	//ifnum[j] is larger than the pivot move on to next index
	            j--;
	           }
	        if(i <= j) {
	        	//swap two index
	            long temp = numbers[i];
	            numbers[i] = numbers[j];
	            numbers[j] = temp;
	            i++;
	            j--;
	            count++;
	           }           
	       }//recur the whole process by decreasing one length in the array
	       if(low < j) {
	    	   quicksort(low, j);
	    	   count++;
	       }
	       if(i < high) {
	    	   quicksort(i, high);
	    	   count++;
	       }
	   }
	
	/**
	* Executes the merge sort algorithm sorting the argument array.
	* There is no return as the parameter is to be mutated.
	* @param a the array of long integers to be sorted
	**/
	public void mergeSort(long[] a){
	mergeSort(a, 0, a.length-1);
	}
	
    	/**
	*A private method to merge the elements in the array between p and r.
	*the array a, between the indices p and r, inclusive.
	*Given the array is sorted between p and q and q+1 and r
	*sorts the array between p and r.
	*@param a the array to be sorted, which is mutated by the method
	*@param p the lower index of the range to be partitioned
	*@param q the midpoint of the two sorted sections.
	*@param r the upper index of the range to be paritioned
	*@return the index of the point of partition
	**/
	private void merge(long[] a, int p, int q, int r)
	{
	int n = q-p+1;
	int m = r-q;
	long[] an = new long[n];
	long[] am = new long[m];
	for(int i = 0; i<n; i++) {
	  an[i] = a[p+i];
	  count++;
	}
	for(int i = 0; i<m; i++){
	  am[i] = a[q+i+1];
	  count++;
	}
	int i = 0;
	int j = 0;
	for(int k = p; k<=r; k++){
	  if(i==n) a[k] = am[j++];
	  else if(j==m || an[i]<am[j]) a[k] = an[i++];
	  else a[k] = am[j++];
	  count++;
	 }
	}
	
   /**
   *Overloads the mergeSort method with parameters to set the 
   *range to be sorted.
   **/ 
	private void mergeSort(long[] a, int p, int r)
	{
	  if(p<r){
	    int i = (p+r)/2;
	    mergeSort(a,p,i);
	    mergeSort(a,i+1,r);
	    merge(a, p,i,r);
	  }
	}
  }