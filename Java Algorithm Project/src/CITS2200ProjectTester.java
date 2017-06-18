import java.io.*;
import java.util.*;
import CITS2200.Graph;
public class CITS2200ProjectTester {
	
	public static void loadGraph(CITS2200Project project, File f) {
		// The graph is in the following format:
		// Every pair of consecutive lines represent a directed edge.
		// The edge goes from the URL in the first line to the URL in the second line.
		try {
			BufferedReader reader = new BufferedReader(new FileReader(f));
			while (reader.ready()) {
				String from = reader.readLine();
				String to = reader.readLine();
				System.out.println("Adding edge from " + from + " to " + to);
				project.addEdge(from, to);
			}
		} catch (Exception e) {
			System.out.println("There was a problem:");
			System.out.println(e.toString());
		}
	}

	public static void main(String[] args) {
		// Change this to be the path to the graph file.
		//String pathToGraphFile = "/path/to/the/file";
		File f = new File("C:\\Users\\HP User\\Desktop\\graph.txt");
		// Create an instance of your implementation.
		Project proj = new Project(f);
		// Load the graph into the project.
		loadGraph(proj, f);
		Graph g = Graph.randomWeightedGraph(50, true,0.05, 1);
		Graph g1 = Graph.randomWeightedGraph(100, true,0.05, 1);
		Graph g2 = Graph.randomWeightedGraph(500, true,0.05, 1);
		Graph g3 = Graph.randomWeightedGraph(1000, true,0.05, 1);
		System.out.println(Arrays.toString(proj.getCenters()));
		System.out.println(proj.getShortestPath("/wiki/Flow_network", "/wiki/Dinic%27s_algorithm"));
		//long startTime = System.nanoTime();
		//System.out.println(Arrays.toString(proj.getCenters()));
		//long endTime = System.nanoTime();
		//System.out.println(endTime - startTime);
		/*long startTime1 = System.nanoTime();
		System.out.println(proj.getShortestPath(g, 0, 49));
		long endTime1 = System.nanoTime();
		System.out.println(endTime1 - startTime1);
		long startTime2 = System.nanoTime();
		System.out.println(proj.getShortestPath(g1, 0, 99));
		long endTime2 = System.nanoTime();
		System.out.println(endTime2 - startTime2);
		long startTime3 = System.nanoTime();
		System.out.println(proj.getShortestPath(g2, 0, 499));
		long endTime3 = System.nanoTime();
		System.out.println(endTime3 - startTime3);
		long startTime4 = System.nanoTime();
		System.out.println(proj.getShortestPath(g3, 0, 999));
		long endTime4 = System.nanoTime();
		System.out.println(endTime4 - startTime4);
		*/
		
		/*long startTime1 = System.nanoTime();
		System.out.println(Arrays.toString(proj.getCenters(g)));
		long endTime1 = System.nanoTime();
		System.out.println(endTime1 - startTime1);
		long startTime2 = System.nanoTime();
		System.out.println(Arrays.toString(proj.getCenters(g1)));
		long endTime2 = System.nanoTime();
		System.out.println(endTime2 - startTime2);
		long startTime3 = System.nanoTime();
		System.out.println(Arrays.toString(proj.getCenters(g2)));
		long endTime3 = System.nanoTime();
		System.out.println(endTime3 - startTime3);
		long startTime4 = System.nanoTime();
		System.out.println(Arrays.toString(proj.getCenters(g3)));
		long endTime4 = System.nanoTime();
		System.out.println(endTime4 - startTime4);
		*/
		System.out.println(Arrays.deepToString(proj.getStronglyConnectedComponents()));
		System.out.println(Arrays.toString(proj.getHamiltonianPath()));
		
	}
}