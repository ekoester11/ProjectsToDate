/**
 * Represents a graph data structure using an adjacency matrix.
 *
 * @author Eli and Pranav
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class GraphMatrix {
	// DO NOT ADD DATA MEMBERS

	/** The Vertexes in this graph */
	private Vertex[] v;

	/** The edge adjacency matrix */
	private int[][] edge;

	/**
	 * Constructor creates a graph with room for the given number of vertices.
	 *
	 * @param numV The number of vertices in this graph.
	 */
	public GraphMatrix(int numV) {
		v = new Vertex[numV];
		// populate vertexes with empty names
		for(int i = 0; i < numV; i++) {
			v[i] = new Vertex("empty");
		}

		edge = new int[numV][numV];
		// populate edges equal to 0
		for(int j = 0; j < edge.length; j++) {
			for(int k = 0; k < edge.length; k++) {
				edge[j][k] = 0;
			}
		}

	} // end of constructor

	/**
	 * Sets every vertex in the graph to the unvisited state.
	 * DO NOT CHANGE THIS METHOD
	 */
	private void makeAllUnvisited() {
		for (int i=0; i<v.length; i++)
		{
			v[i].setUnvisited();
		}
	}

	/**
	 * Adds a vertex to the graph.
	 *
	 * @param vNum The number to associate with this vertex in the graph.
	 * @param vName The name to associate with this vertex.
	 * @exception IllegalArgumentException if vNum is not a valid vertex number.
	 */
	public void addVertex(int vNum, String vName) throws IllegalArgumentException {
		if(vNum > v.length-1 || vNum<0) {
			throw new IllegalArgumentException("Invalid index");
		}
		v[vNum].setName(vName);

	} // end of addVertex method

	/**
	 * Adds an undirected, unweighted edge between the two vertices.
	 *
	 * @param v1 Vertex number of one endpoints of the edge.
	 * @param v2 Vertex number of the other endpoint of the edge.
	 * @exception IllegalArgumentException if either parameter is not a valid vertex number.
	 */
	public void addEdge(int v1, int v2) throws IllegalArgumentException {
		if(v1 > v.length-1 || v1<0) {
			throw new IllegalArgumentException("v1 is not valid");
		}
		if(v2 > v.length-1 || v2<0) {
			throw new IllegalArgumentException("v2 is not valid");
		}
		edge[v1][v2] = 1;
		edge[v2][v1] = 1;

	} // end of addEdge method

	/**
	 * Adds a directed, unweighted edge from vertex v1 to vertex v2.
	 *
	 * @param v1 Vertex number of the starting vertex for the edge.
	 * @param v2 Vertex number of the destination vertex for the edge.
	 * @exception IllegalArgumentException if either parameter is not a valid vertex number.
	 */
	public void addDirectedEdge(int v1, int v2) throws IllegalArgumentException {
		if(v1 > v.length-1 || v1<0) {
			throw new IllegalArgumentException("v1 is not valid");
		}
		if(v2 > v.length-1 || v2<0) {
			throw new IllegalArgumentException("v2 is not valid");
		}

		edge[v1][v2] = 1;

	} // end of addDirectedEdge method

	/**
	 * Adds an undirected, weighted edge between the two vertices.
	 *
	 * @param v1 Vertex number of one endpoints of the edge.
	 * @param v2 Vertex number of the other endpoint of the edge.
	 * @param w Weight of the edge. Must be greater than zero.
	 * @exception IllegalArgumentException if either vertex is not a valid vertex number,
	 * or if the weight is not greater than zero.
	 */
	public void addEdgeWithWeight(int v1, int v2, int w) throws IllegalArgumentException {
		if(v1 > v.length-1 || v1<0) {
			throw new IllegalArgumentException("v1 is not valid");
		}
		if(v2 > v.length-1 || v2<0) {
			throw new IllegalArgumentException("v2 is not valid");
		}
		if(w <= 0) {
			throw new IllegalArgumentException("weight has to be greater than 0");
		}

		edge[v1][v2] = w;
		edge[v2][v1] = w;

		// Program 5: complete this method

	} // end of addEdgeWithWeight method

	/**
	 * Adds a directed, weighted edge from vertex v1 to vertex v2.
	 *
	 * @param v1 Vertex number of the starting vertex for the edge.
	 * @param v2 Vertex number of the destination vertex for the edge.
	 * @param w Weight of the edge. Must be greater than zero.
	 * @exception IllegalArgumentException if either vertex is not a valid vertex number,
	 * or if the weight is not greater than zero.
	 */
	public void addDirectedEdgeWithWeight(int v1, int v2, int w) throws IllegalArgumentException {
		if(v1 > v.length-1 || v1<0) {
			throw new IllegalArgumentException("v1 is not valid");
		}
		if(v2 > v.length-1 || v2<0) {
			throw new IllegalArgumentException("v2 is not valid");
		}
		if(w <= 0) {
			throw new IllegalArgumentException("weight has to be greater than 0");
		}

		edge[v1][v2] = w;

	} // end of addDirectedEdgeWithWeight method

	/**
	 * Returns a string representation of the graph vertices traversed in
	 * bredth first order from the specified starting vertex.
	 *
	 * @param start The starting vertex
	 * @return A string representation of the graph vertices traversed in bredth
	 * first order; For each node includes node name and vertex number.
	 * @exception IllegalArgumentException if start is not a valid vertex number.
	 */
	public String bfs(int start) throws IllegalArgumentException{
		if(start > v.length || start < 0) {
			throw new IllegalArgumentException("Invalid starting index");
		}

		String vertices = "";
		Queue1<Integer> waiting = new Queue1<Integer>();
		waiting.enqueue(start);
		makeAllUnvisited();
		// while the waiting list isn't empty
		while(!waiting.isEmpty()) {
			// dequeue
			start = waiting.dequeue();
			// set it to visited
			v[start].setVisited();
			// add it to string
			vertices += v[start].getName()+ ", " + start + ";";
			// go through all the edges leaving that vertex and enqueue those
			for (int i = 0; i<edge.length;i++) {
				if (edge[start][i]!=0) {
					if(v[i].isUnvisited()) {
						waiting.enqueue(i);
						v[i].setWaiting();
					}
				}
			}

		}


		return vertices;

	} // end of bfs method

	/**
	 * 
	 * @param start: starting vertex
	 * @param waiting: arrayList of already sorted vertexes
	 * @param toBeSortedAL: arrayList of 
	 * @return
	 */
	private void addToWaiting(int start, ArrayList<Integer> waiting) {

		ArrayList<Integer> toBeSortedAL = new ArrayList<Integer>();
		// add all the indexes of outgoing edges from given vertex to arrayList to be sorted
		for (int i = 0; i<edge.length;i++) {

			if (edge[start][i]!=0) {
				if(v[i].isUnvisited()) {
					toBeSortedAL.add(i);
					v[i].setWaiting();
				}
			}
		} 
		int[] toBeSortedA = new int[toBeSortedAL.size()];
		// copy into array so that bubble sort can be used
		for (int i = 0; i<toBeSortedA.length; i++) {
			toBeSortedA[i] = toBeSortedAL.get(i);
		}
		//bubble sort
		int temp = 0;
		// go through toBeSorted array
		for (int i = 0; i<toBeSortedA.length;i++) {
			for (int j = 1; j<toBeSortedA.length-i; j++) {
				// if the edge is greater than the 
				if (edge[start][toBeSortedA[j-1]]>edge[start][toBeSortedA[j]]) {
					temp = toBeSortedA[j];
					toBeSortedA[j] = toBeSortedA[j-1];
					toBeSortedA[j-1] = temp;
				}
			}
		}
		for (int i=0; i<toBeSortedA.length;i++) {
			waiting.add(toBeSortedA[i]);
		}
		v[start].setVisited();

	}

	/**
	 * Returns a string representation of the graph vertices traversed in
	 * depth first order from the specified starting vertex.
	 *
	 * @param start The starting vertex
	 * @return A string representation of the graph vertices traversed in depth
	 * first order; For each node includes node name and vertex number.
	 * @exception IllegalArgumentException if start is not a valid vertex number.
	 */
	public String dfs(int start) throws IllegalArgumentException{
		if(start > v.length || start < 0) {
			throw new IllegalArgumentException("Invalid starting index");
		}
		makeAllUnvisited();
		Stack<Integer> stack = new Stack<Integer>();
		String vertices = "";
		v[start].setVisited();
		stack.push(start);
		// while the stack isn't empty
		while(!stack.isEmpty()) {
			// pop the top off
			int visited = stack.pop();
			// set it to visited
			v[visited].setVisited();
			start = visited;
			vertices += v[visited].getName() + ", " + visited + "; ";
			for(int i = 0; i < edge.length; i++) {
				if(edge[start][i] != 0) {
					if(v[i].isUnvisited()) {
						stack.push(i);

					}
				}
			}

		}



		return vertices;

	} // end of dfs method

	/**
	 * Returns a string that contains information about the shortest path from
	 * the given node to every node in the graph, including the path from the given
	 * node to itself. This method considers the shortest path assuming the
	 * edges are unweighted. The string should have an end of line after each
	 * path from the given node to another node.
	 *
	 * @param start The starting vertex
	 * @return A string containing information about the shortest path from the
	 * given node to every node in the graph.
	 * @exception IllegalArgumentException if start is not a valid vertex number.
	 */

	public String shortestPath(int start) {
		if(start > v.length || start < 0) {

			throw new IllegalArgumentException("Invalid starting index");
		}
		
		String output = "";
		String startName = v[start].getName();
		makeAllUnvisited();
		PathInfo pi = new PathInfo(startName, 0, startName, start);
		Queue1<PathInfo> visit = new Queue1<PathInfo>();
		visit.enqueue(pi);
		PathInfo save;
		PathInfo temp;
		// while visit queue isn't empty
		while(!visit.isEmpty()) {
			// dequeue the first item
			save = visit.dequeue();
			// set it to visited
			v[save.getIndex()].setVisited();
			// add the info to output string
			output += startName + save.toString() + "\n";
			// loop through all nodes adjacent to it
			for(int i = 0; i < edge.length; i++) {
				if(v[i].isUnvisited()) {
					if(edge[save.getIndex()][i] != 0) {
						// get that node's name, the length of the last node plus 1, the pathString of the last node
						// plus this one, and the node's index and assign that to a PathInfo object
						temp = new PathInfo(v[i].getName(), 
								save.getLength() + 1, 
								save.getPathString() + " -- " + v[i].getName(), i);
						// enqueue that object
						visit.enqueue(temp);
						v[i].setWaiting();
					}
				}
			}
			
		}


		return output;

	} // end of shortestPath method


	/**
	 * Returns a string that contains information about the shortest path from
	 * the given node to every node in the graph, including the path from the given
	 * node to itself. This method uses Dijkstra's algorithm for finding the
	 * shortest path assuming the edges are weighted. The string should have an
	 * end of line after each path from the given node to another node.
	 *
	 * Note: in order to use java.util.PriorityQueue you will need to create a
	 * class that contains the information needed for the items that are added
	 * to the priority queue.
	 *
	 * @param start The starting vertex
	 * @return A string containing information about the shortest path from the
	 * given node to every node in the graph.
	 * @exception IllegalArgumentException if start is not a valid vertex number.
	 */
	public String dijkstras(int start) {
		PathInfo temp;
		// priority queue to hold lengths of nodes to start sorted in order from shortest to long
		PriorityQueue<PathInfo> pq = new PriorityQueue<PathInfo>();
		// pathList to hold final paths
		PathInfo[] pathList = new PathInfo[v.length];
		// loop through vertexes and set max length to infinity, add them to pathList and set to unvisited
		for (int i=0; i<v.length; i++)
		{
			v[i].setUnvisited();
			temp = new PathInfo(v[i].getName(), Integer.MAX_VALUE, "", i);
			pathList[i] = temp;
		}
		String output = ""; // final output string
		// put starting node on pq
		PathInfo startV = new PathInfo(v[start].getName(), 0, v[start].getName(), start);
		pq.add(startV);
		pathList[start].setLength(0);
		PathInfo save;
		int saveIndex;
		
		// while pq isn't empty
		while(!pq.isEmpty()) {
			// dequeue the first item and set to visited
			save = pq.poll();
			saveIndex = save.getIndex();
			v[saveIndex].setVisited();
			// loop through the edges leaving it that are unvisited
			for(int i = 0; i < edge.length; i++) {
				if(edge[saveIndex][i] != 0) {
					if(v[i].isUnvisited()) {
					// if the length on pathlist is bigger than length of to previous node plus edge 
					//	connecting these 2 nodes 
						if(pathList[i].getLength() > save.getLength() + edge[saveIndex][i]) { 
							
							// increase length by edge amount and add the edge to the string
							pathList[i].setLength(save.getLength() + edge[saveIndex][i]);
							pathList[i].setPathString(save.getPathString() + " " + v[i].getName());
							PathInfo temp1 = new PathInfo(pathList[i].getName(), 
									pathList[i].getLength(), pathList[i].getPathString(), i);
							pq.add(temp1);
						}
					}
				}
			}
		}
		
		// add to output
		for(int i = 0; i < pathList.length; i++) {
			output+= v[start].getName() + pathList[i].toString() + "\n";
		}
		
		
		
		
		
		return output;

	} // end of dijkstras method

}
