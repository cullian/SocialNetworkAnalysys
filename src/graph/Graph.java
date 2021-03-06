/**
 * Abstract graph interface provides the basic methods for
 * creating, printing and exporting a graph
 * 
 * @author William Cullian
 *
 */

package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public interface Graph {
	/* Creates a vertex with the given number. */
	public void addFriend(int num);

	/* Creates an edge from the first vertex to the second. */
	public void addFriendship(int from, int to);

	/*
	 * Return the graph's connections in a readable format. The keys in this
	 * HashMap are the vertices in the graph. The values are the nodes that are
	 * reachable via a directed edge from the corresponding key. The returned
	 * representation ignores edge weights and multi-edges.
	 */
	public HashMap<Integer, HashSet<Integer>> exportGraph();

	public void printGraph();
}
