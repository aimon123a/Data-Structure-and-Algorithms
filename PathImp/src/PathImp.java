import CITS2200.*;

/**
 * Write a description of class PathImp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PathImp<E> implements Path
{
	private PriorityQueueLinked<Integer> p;
	@Override
	public int getMinSpanningTree(Graph g) {
		int [][] graph = g.getEdgeMatrix();
		
		p = new PriorityQueueLinked<Integer>();
		
		int[] parent = new int[graph.length];
		int[] distance = new int[graph.length];
		int[] visited = new int[graph.length];
		
		for(int i = 0; i<distance.length;i++){
			distance[i] = Integer.MAX_VALUE;
			parent[i] = -1;
		}
		
		int v1 = 0;
		int startV = 0;
		distance[startV] = 0;
		p.enqueue(startV, 0);
		while(!p.isEmpty()){
			v1 = p.dequeue();
			visited[v1] = 2;
			for(int j = 0; j<graph.length;j++){
				if(graph[v1][j] != 0 && visited[j] != 2){
					if(distance[j] > graph[v1][j]){
						/*if(visited[j] == 1){
							p.l = p.front;
							while(p.l.priority != distance[j] && p.l.element != j) {
								p.l = p.l.next;
							}
							if(p.l.next == null) {
								p.l = null;
							} else {
								p.l.element = p.l.next.element;
								p.l.next = p.l.next.next;
							} 
							p.enqueue(j, graph[v1][j]);
							distance[j] = graph[v1][j];
						} */
							distance[j] = graph[v1][j];
							
						
						parent[j] = v1;
						p.enqueue(j, graph[v1][j]);
						visited[j] = 1;
					}
				}
			}
		}
		int weight = 0;
		for(int w = 0; w<distance.length; w++)weight = weight + distance[w];
		return weight;
	}


	public int[] getShortestPaths(Graph g, int source) {
		p = new PriorityQueueLinked<Integer>();
		int[][]graph = g.getEdgeMatrix();
		int[] parent = new int [graph.length];
		int [] distance = new int[graph.length];
		int [] visited = new int[graph.length];
		for(int i = 0;i<graph.length; i++){
			distance[i] = Integer.MAX_VALUE;
			parent[i] = -1;
		}
		int startV = source;
		int v1;
		p.enqueue(startV, 0);
		distance[source] = 0;
		while(!p.isEmpty()){
			v1 = p.dequeue();
			visited[v1] = 2;
			for(int j = 0; j<graph.length;j++){
				if(graph[v1][j] != 0 && visited[j] != 2) {
					int newDist = distance[v1] + graph[v1][j];
					/*if(distance[j] == Integer.MAX_VALUE){
						distance[j] = newDist;
						parent[j] = v1;
						p.enqueue(j, distance[j]);
					} else*/ if( distance[j] > newDist ){
						distance[j] = newDist;
						parent[j] = v1;
						p.enqueue(j, distance[j]);
					}
				}
			}
		}
		//int a = source;
		/*int count = 1;
		while(parent[a] != -1){
			count++;
			a = parent[a];
		}*/
		//int []b = new int[graph.length];
		//b[a] = 0;
		//for( int c = 0;c<graph.length;c++){
				//b[c] = distance[c];
			//}
			/*int dist = distance[a] - distance[parent[a]];
			b[c] = dist;
			a = parent[a];
		}*/
		return distance;
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

		
		public E examine() throws Underflow {
			if(!isEmpty()) {
				return (E) front.element;
			}
			else throw new Underflow ("Empty Queue");
		}

		
		public boolean isEmpty() {
			return front == null;
		}
	}
	

	
}
