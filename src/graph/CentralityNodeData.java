/**
 * @author William Cullian
 * Dec 4, 2017
 *
 */

package graph;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * @author William
 *
 */
public class CentralityNodeData implements Data {
	
	// fields
	private double centralityOfBetweenness;
	private double distance;
	private HashSet<Integer> predecessors;
	private LinkedList<LinkedList<Integer>> paths;

	/**
	 * 
	 */
	public CentralityNodeData() {
		super();
		this.centralityOfBetweenness = 0;
		this.distance = Double.POSITIVE_INFINITY;
		this.predecessors = new HashSet<>();
		this.paths = new LinkedList<>();
	}

	/**
	 * @param centralityOfBetweenness
	 * @param distance
	 * @param predecessors
	 * @param paths
	 */
	public CentralityNodeData(double centralityOfBetweenness, int distance, HashSet<Integer> predecessors,
			LinkedList<LinkedList<Integer>> paths) {
		super();
		this.centralityOfBetweenness = centralityOfBetweenness;
		this.distance = distance;
		this.predecessors = predecessors;
		this.paths = paths;
	}

	/* (non-Javadoc)
	 * @see graph.Data#printData()
	 */
	@Override
	public void printData() {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the centralityOfBetweenness
	 */
	public double getCentralityOfBetweenness() {
		return centralityOfBetweenness;
	}

	/**
	 * @param centralityOfBetweenness the centralityOfBetweenness to set
	 */
	public void setCentralityOfBetweenness(double centralityOfBetweenness) {
		this.centralityOfBetweenness = centralityOfBetweenness;
	}

	/**
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}

	/**
	 * @return the predecessors
	 */
	public HashSet<Integer> getPredecessors() {
		return predecessors;
	}

	/**
	 * @param predecessors the predecessors to set
	 */
	public void setPredecessors(HashSet<Integer> predecessors) {
		this.predecessors = predecessors;
	}

	/**
	 * @return the paths
	 */
	public LinkedList<LinkedList<Integer>> getPaths() {
		return paths;
	}

	/**
	 * @param paths the paths to set
	 */
	public void setPaths(LinkedList<LinkedList<Integer>> paths) {
		this.paths = paths;
	}

}
