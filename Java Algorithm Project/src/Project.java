
import CITS2200.Graph;
import java.io.*;
import java.util.*;

import CITS2200.IllegalValue;
import CITS2200.Underflow;

/**
 * Write a description of class Project here.
 * 
 * @author (your name) 
 * 21960332 Tony Guo
 * 21862296 Hei Wong Aimon
 * @version (a version number or a date)
 */
public class Project implements CITS2200Project
{
	private PriorityQueueLinked<Integer> p;
	private Queue<Integer> q;
	private String[] vertex;
	int count, row;
	private int pathcount = 1;
	private List<Integer>[] adj;
	private List<Integer>[] adjreversed;

	/**
	 * Adds an edge to the Wikipedia page graph. If the pages do not
	 * already exist in the graph, they will be added to the graph.
	 * 
	 * @param urlFrom the URL which has a link to urlTo.
	 * @param urlTo the URL which urlFrom has a link to.
	 */

	public Project(File f){
		Set<String> mySet = new HashSet<String>();
		try{
			BufferedReader reader = new BufferedReader(new FileReader(f));
			while(reader.ready()){
				String line = reader.readLine();
				mySet.add(line);
			}
			vertex = mySet.toArray( new String[mySet.size()]);
			adj = (List<Integer>[]) new List[vertex.length];
			for(int i = 0; i < vertex.length; i++){
				adj[i] = new LinkedList<Integer>();
			}
		}

		catch(Exception e){
			System.out.println("something is wrong");
		}

	}



	public void addEdge(String urlFrom, String urlTo) {
		int f = 0;
		int t = 0;

		while(!vertex[f].equals(urlFrom)){
			f++;
		}

		while(!vertex[t].equals(urlTo)){
			t++;
		}
		adj[f].add(t);

	}

	public int search (String s){
		int i = 0;
		while(!vertex[i].equals(s)){
			i++;
		}
		return i;
	}


	/**
	 * Finds the shorest path in number of links between two pages.
	 * If there is no path, returns -1.
	 * 
	 * @param urlFrom the URL where the path should start.
	 * @param urlTo the URL where the path should end.
	 * @return the legnth of the shorest path in number of links followed.
	 */


	public int getShortestPath( String urlFrom, String urlTo) {

		p = new PriorityQueueLinked<Integer> ();
		int [] parent = new int [vertex.length];
		int []visited = new int[vertex.length];
		int [] distance = new int[vertex.length];
		for(int i = 0; i < vertex.length; i++) {
			parent[i] = -1;
			distance[i] = Integer.MAX_VALUE;
		}
		int s = search(urlFrom);
		p.enqueue(s, 0);
		distance[s] = 0;
		while(!p.isEmpty()){
			int v1 = p.dequeue();
			visited[v1] = 1;
			Iterator itr = adj[v1].iterator();
			while(itr.hasNext()){
				int j = (int)itr.next();
				if(visited[j] == 0){
					int newDist = distance[v1] + 1;
					if(distance[j] > newDist){
						distance[j] = newDist;
						parent[j] = v1;
						p.enqueue(j, distance[j]);
					}
				}
				
			}
		}
		int sp = distance[search(urlTo)];
		if(sp == Integer.MAX_VALUE)return -1;
		return sp;
	}


	/**
	 * Finds all the centers of the page graph. The order of pages
	 * in the output does not matter. Any order is correct as long as
	 * all the centers are in the array, and no pages that aren't centers
	 * are in the array.
	 * 
	 * @return an array containing all the URLs that correspond to pages that are centers.
	 */
	public String[] getCenters() {
		p = new PriorityQueueLinked<Integer>();
		PriorityQueueLinked<Integer> p1 = new PriorityQueueLinked<Integer>();
		int [] parent = new int [vertex.length];
		int [] visited = new int[vertex.length];
		int [] distance = new int[vertex.length];

		for(int i = 0 ; i < vertex.length;i++){
			for(int k = 0; k < vertex.length; k++) {
				distance[k] = Integer.MAX_VALUE;
				visited[k] = 0;
				parent[k] = -1;
			}
			int max = 0;
			p.enqueue(i, 0);
			distance[i] = 0;
			while(!p.isEmpty()){
				int v1 = p.dequeue();
				visited[i] = 1;
				Iterator itr = adj[v1].iterator();			
				while(itr.hasNext()){
					int j = (int)itr.next();
					if(visited[j] == 0){			
						int newDist = distance[v1] + 1;
						if(distance[j] > newDist){
							distance[j] = newDist;
							parent[j] = v1;
							p.enqueue(j, distance[j]);
							if(distance[j] > max) max = distance[j];
						}

					}
				}
			}
			if(max != 0) p1.enqueue(i, max);
		}
		int lowp = p1.examine();
		ArrayList<Integer> obj = new ArrayList<Integer>();
		while(!p1.isEmpty() && p1.examine() == lowp){
			obj.add(p1.dequeue());
		}
		String [] s = new String [obj.size()];
		for(int i = 0; i < obj.size(); i++){
			s[i] = vertex[obj.get(i)];
		}
		return s;
		
	}



	/**
	 * Finds all the strongly connected components of the page graph.
	 * Every strongly connected component can be represented as an array 
	 * containing the page URLs in the component. The return value is thus an array
	 * of strongly connected components. The order of elements in these arrays
	 * does not matter. Any output that contains all the strongly connected
	 * components is considered correct.
	 * 
	 * @return an array containing every strongly connected component.
	 */
	public String [][] getStronglyConnectedComponents() {

		int index = 0;
		Stack<Integer> stack = new Stack<>();
		int[] visited = new int [vertex.length];

		for(int i = 0; i < vertex.length; i++){
			if(visited[i] == 1)continue;
			DFS(i, visited, stack);
		}

		adjreversed = (List<Integer>[]) new List[vertex.length];
		for(int i = 0 ; i < vertex.length; i++) adjreversed[i] = new LinkedList<Integer>();
		for(int j = 0; j < vertex.length; j++){
		Iterator itr = adj[j].iterator();
		while (itr.hasNext()){
			int next = (int)itr.next();
			adjreversed[next].add(j);
		}
		}		
		Arrays.fill(visited, 0);
		q = new LinkedList<Integer>();
		while(!stack.isEmpty()){
			int v1 = stack.pop();
			if(visited[v1] == 1)continue;
			DFSR(v1, visited, q);
			q.add(-1);
			row++;
			

		}
		Integer[] s = q.toArray(new Integer[q.size()]);
		
		int columns = 0;
		String [][] ss = new String [row][vertex.length];
		for(int j = 0 ; j < q.size(); j++){
			if(s[j] == -1){
				index++;
				columns = 0;
				continue;
			}
			ss[index][columns] = vertex[s[j]];
			columns++;
		}
		
		return ss;
	}

	public void DFS(int v, int[] visited, Stack<Integer> stack){
		visited[v] = 1;
		Iterator<Integer> itr = adj[v].iterator();
		while(itr.hasNext()){
			int i = itr.next();
			if(visited[i] == 0) DFS(i, visited, stack);
		}
		stack.push(v);
	}

	public void DFSR(int v, int[] visited, Queue<Integer> q){
		visited[v] = 1;
		q.add(v);
		Iterator itr = adjreversed[v].iterator();
		
		while(itr.hasNext()){
			int i = (int)itr.next();
		
		if(visited[i] != 1) DFSR(i, visited, q);
		
		}
		
	}

	




	/**
	 * Finds a Hamiltonian path in the page graph. There may be many
	 * possible Hamiltonian paths. Any of these paths is a correct output.
	 * This method should never be called on a graph with more than 20
	 * vertices. If there is no Hamiltonian path, this method will
	 * return an empty array. The output array should contain the URLs of pages
	 * in a Hamiltonian path. The order matters, as the elements of the
	 * array represent this path in sequence. So the element [0] is the start
	 * of the path, and [1] is the next page, and so on.
	 * 
	 * @return a Hamiltonian path of the page graph.
	 */
	public String[] getHamiltonianPath() {

		int [] path = new int [vertex.length];
		int [] visited = new int [vertex.length];

		String [] s = new String[vertex.length];
		List<ArrayList<String>> l = (List)new ArrayList<String>();
		for(int i = 0; i <vertex.length ; i++){
			//System.out.println("main count = " + count);
			
			pathcount = 0;
			count = 0;
			Arrays.fill(visited, 0);
			Arrays.fill(path, 0);
			pathcount = 1;
			path[0] = i;
			visited[i] = 1;
			DFSH(i, visited, path);
			pathcount = count;	
			if(pathcount == vertex.length){
				ArrayList<String> al = new ArrayList<String>();
				for(int j = 0 ; j < vertex.length;j++){
					al.add(vertex[path[j]]);
				}
				l.add(al);
			}
		}
		if(l.size() == 0)return s;
			
	
		ArrayList<String> alf = new ArrayList<String>();
		alf = l.get(0);
		for(int i = 0 ; i < vertex.length; i++){
			s[i] = alf.get(i);
		}
		return s;

	}

	public void DFSH(int v, int [] visited, int [] path){
		Iterator itr = adj[v].iterator();
		while(itr.hasNext()){
			boolean i = false;
			int a = (int)itr.next();
			if(visited[a] != 1){
				path[pathcount++] = a;
				visited[a] = 1;
				if(pathcount == vertex.length){
					count = pathcount;
					break;
				}
				DFSH(a, visited, path);
				i = true;
			}
			if(pathcount != vertex.length && i == true){
			visited[path[pathcount]] = 0;
			}
		}

		if(pathcount != vertex.length){
		path[pathcount] = 0;
		pathcount--;
		}
	}

	public class PriorityQueueLinked<E> 
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


		public E dequeue() throws Underflow {
			if(!isEmpty()) {
				E temp = (E) front.element;
				front = front.next;
				return temp;
			}
			else throw new Underflow("Empty Queue");
		}


		public void enqueue(E e, int p) throws IllegalValue {
			if(isEmpty() || p < front.priority){
				front = new Link(e, p, front);
				count1++;
			} else {
				Link l = front;
				while(l.next != null && l.next.priority <= p){
					l = l.next;
				}
				l.next = new Link(e, p, l.next);
				count1++;
			}

		}


		public int examine() throws Underflow {
			if(!isEmpty()) {
				return  front.priority;
			}
			else throw new Underflow ("Empty Queue");
		}


		public boolean isEmpty() {
			return front == null;
		}
	}
}
