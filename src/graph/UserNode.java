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

class UserNode {
	private int userNumber; // number of person
	private HashSet<FriendshipEdge> friendships; // list of friends of this person

	/**
	 * Constructor sets user number with empty edge list
	 * 
	 * @param loc
	 */
	public UserNode(int num) {
		super();
		this.userNumber = num;
		this.friendships = new HashSet<FriendshipEdge>();
	}

	/**
	 * Add an edge to the edges list if it does not already exist
	 * 
	 * @param add
	 *            newEdge to list
	 * @return true if edge added
	 */
	public boolean addFriend(FriendshipEdge newEdge) {
		// if friendships contains new edge return false
		for (FriendshipEdge friendshipEdge : friendships) {
			if (friendshipEdge.compareTo(newEdge) == 0) {
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

	public int getUserNumber() {
		return userNumber;
	}

	public HashSet<FriendshipEdge> getFriends() {
		return new HashSet<FriendshipEdge>(friendships);
	}

	@Override
	public String toString() {
		return "UserNode [userNumber=" + userNumber + ", friendships=" + friendships + "]";
	}

}
