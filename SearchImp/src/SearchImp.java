import CITS2200.*;
import java.util.Queue;
import java.util.LinkedList;

public class SearchImp implements Search {
	
	public int[] getConnectedTree(Graph g, int startVertex) {
		Queue<Integer> q = new LinkedList<Integer>();
		
		int [] con = new int [g.getNumberOfVertices()];
		for(int i = 0; i <g.getNumberOfVertices();i++) con[i] = -1;
			int v1;
			int[][]graph = g.getEdgeMatrix();
			q.add(startVertex);
			con[startVertex] = startVertex;
			while(!q.isEmpty()){
				v1 = q.poll();
				for(int j = 0; j<g.getNumberOfVertices();j++) {
					if(graph[v1][j] !=0 && con[j] == -1) {
						q.add(j);
						con[j] = v1;
					}
				}
				
			}
			con[startVertex] = -1;
			return con;
		}
	



	public int[] getDistances(Graph g, int startVertex) {
		
		Queue<Integer> q = new LinkedList<Integer>();
		int [] dist = new int[g.getNumberOfVertices()];
		int [] colour = new int[g.getNumberOfVertices()];
		int [][]graph = g.getEdgeMatrix();
		for(int i = 0; i <g.getNumberOfVertices();i++) dist[i] = -1;
		
			int v1;
			colour[startVertex] = 1;
			q.add(startVertex);
			dist[startVertex] = 0;
			while(!q.isEmpty()){
				v1 = q.poll();
				for(int j = 0; j < g.getNumberOfVertices();j++){
					if(graph[v1][j] != 0 && colour[j] == 0 ){
						dist[j] = dist[v1] + 1;
						colour[j] = 1;
						q.add(j);
						
					}
				}
				
			}
			
			return dist;
		
	}




	public int[][] getTimes(Graph g, int startVertex) {
		int c [][] = new int[g.getNumberOfVertices()][g.getNumberOfVertices()];
		return c;
	}

}
