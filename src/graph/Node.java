/**
 * @author William Cullian
 * Dec 9, 2017
 *
 */

package graph;

import java.util.HashSet;

/**
 * @author William
 *
 */
public class Node {

	protected int userNumber;
	protected HashSet<Edge> friendships;
	protected double centralityOfBetweenness;
	protected double distance;
	protected HashSet<Integer> predecessors;
	protected double delta;
	protected Integer shortestPathCount;


	/**
	 * @param userNumber
	 */
	public Node(int userNumber) {
		super();
		this.userNumber = userNumber;
		this.friendships = new HashSet<>();
		this.centralityOfBetweenness = 0.0;
		this.distance = Double.POSITIVE_INFINITY;
		this.predecessors = new HashSet<>();
		this.delta = 0.0;
		this.shortestPathCount = 0;
	}

	/**
	 * @return the shortestPathCount
	 */
	public Integer getShortestPathCount() {
		return shortestPathCount;
	}

	/**
	 * @param shortestPathCount the shortestPathCount to set
	 */
	public void setShortestPathCount(Integer shortestPathCount) {
		this.shortestPathCount = shortestPathCount;
	}

	/**
	 * Add an edge to the edges list if it does not already exist
	 * 
	 * @param add
	 *            newEdge to list
	 * @return true if edge added
	 */
	public boolean addFriend(Edge newEdge) {
		// if friendships contains new edge return false
		for (Edge edge : friendships) {
			if (edge.compareTo(newEdge) == 0) {
				return false;
			}
		}
		// else add edge and return true
		friendships.add(newEdge);
		return true;
	}

	public int getDegree() {
		return friendships.size();
	}

	public HashSet<Edge> getFriends() {
		return new HashSet<Edge>(friendships);
	}

	@Override
	public String toString() {
		return "UserNode [userNumber=" + userNumber + ", friendships=" + friendships + "]";
	}

	/**
	 * @param source
	 * @param target
	 */
	public void removeEdge(int source, int target) {
		Edge edgeToRemove = new Edge(source, target);
		friendships.remove(edgeToRemove);
		
	}

	/**
	 * @param edgeWithHighestFlow
	 */
	public void removeEdge(Edge edgeWithHighestFlow) {
		friendships.remove(edgeWithHighestFlow);
		
	}

	/**
	 * @return the userNumber
	 */
	public int getUserNumber() {
		return userNumber;
	}

	/**
	 * @param userNumber
	 *            the userNumber to set
	 */
	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}

	/**
	 * 
	 */
	public void resetPredecessors() {
		this.predecessors.clear();
	}

	/**
	 * @param currNodeNum
	 */
	public void addPredecessor(int currNodeNum) {
		this.predecessors.add(currNodeNum);
		
	}

	/**
	 * @return
	 */
	public int getNumFriends() {
		return friendships.size();
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
	 * @return the delta
	 */
	public double getDelta() {
		return delta;
	}

	/**
	 * @param delta the delta to set
	 */
	public void setDelta(double delta) {
		this.delta = delta;
	}

}