/**
 * @author William Cullian
 * 
 * A class which represents a graph node(person) in a 
 * SocialNetworkGraph object connected to other nodes 
 * by edges(friendships).
 * The UserNode contains the user number and a
 * list of edges(friendships) that connect it to other 
 * nodes(people).  It is being used to represent a 
 * social network.
 *
 */
package graph;

import java.util.HashSet;

class UserNode implements Node{
	private int userNumber; // number of person
	private HashSet<Edge> friendships; // list of friends of this person

	/**
	 * Constructor sets user number with empty edge list
	 * 
	 * @param loc
	 */
	public UserNode(int num) {
		super();
		this.userNumber = num;
		this.friendships = new HashSet<Edge>();
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

	public int getUserNumber() {
		return userNumber;
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
		FriendshipEdge edgeToRemove = new FriendshipEdge(source, target);
		friendships.remove(edgeToRemove);
		
	}

	/**
	 * @param edgeWithHighestFlow
	 */
	public void removeEdge(CentralityEdge edgeWithHighestFlow) {
		friendships.remove(edgeWithHighestFlow);
		
	}

}
