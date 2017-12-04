/**
 * Egonet is a graph class designed to hold egonets and stats on them
 */
package graph;

/**
 * @author William Cullian
 *
 */
public class Egonet extends SocialNetworkGraph {

	private EgonetData data;

	/**
	 * Create a new empty Egonet
	 */
	public Egonet(int user) {
		super();
		data = new EgonetData(user);
		this.addFriend(user);
	}

	/**
	 * Prints all the vertices and their respective edge lists for debugging
	 * purposes
	 */
	@Override
	public void printGraph() {
		for (UserNode node : people.values()) {
			System.out.println(node);
		}
	}

	/**
	 * Prints the number of people and the number of friendships for debugging
	 * purposes
	 */
	@Override
	public void printStats() {
		System.out.println("Egonet for user: " + data.getCentralEgoUser());
		System.out.println("Egonet depth: " + data.getEgonetDepth());
		System.out.println("Average number of friends per person: " + data.getAveNumOfFriendships());
		super.printStats();
	}

	/**
	 * Get the user for whom this egonet was created
	 * 
	 * @return the centralEgoUser
	 */
	public int getCentralEgoUser() {
		return data.getCentralEgoUser();
	}

	/**
	 * @return the egonetDepth
	 */
	public int getEgonetDepth() {
		return data.getEgonetDepth();
	}

	/**
	 * @param egonetDepth
	 *            the egonetDepth to set
	 */
	public void setEgonetDepth(int egonetDepth) {
		data.setEgonetDepth(egonetDepth);
	}

	/**
	 * Get the connectivity strength of the egonet
	 * 
	 * @return the friendshipConnectivityStrength
	 */
	public double getFriendshipConnectivityAverage() {
		return data.getAveNumOfFriendships();
	}

	/**
	 * Add a node corresponding to an person mapped to the person number If the
	 * person is already in the graph, this method does not change the graph.
	 * 
	 * @param num
	 *            The number of the person.
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see graph.Graph#addVertex(int)
	 */
	@Override
	public void addFriend(int num) {
		super.addFriend(num);
		recalculateFriendAverage();

	}

	/**
	 * Adds a directed edge to the graph from person number 1 to person number
	 * 2. Precondition: Both people have already been added to the graph If the
	 * people numbers do not exist in the graph, it does nothing
	 * 
	 * @param from
	 *            The starting point of the edge
	 * @param to
	 *            The ending point of the edge
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see graph.Graph#addEdge(int, int)
	 */
	@Override
	public void addFriendship(int from, int to) {
		super.addFriendship(from, to);
		recalculateFriendAverage();
	}

	public Graph exportEgonet() {
		// create object to return
		Egonet ret = new Egonet(data.getCentralEgoUser());
		// iterate thru each node
		for (UserNode node : people.values()) {
			// add nodes to return object
			ret.addFriend(node.getUserNumber());
		}
		// go thru again and add edges
		for (UserNode node : people.values()) {
			for (FriendshipEdge friendship : node.getFriends()) {
				ret.addFriendship(friendship.getSource(), friendship.getTarget());
			}
		}
		ret.setEgonetDepth(data.getEgonetDepth());
		return ret;
	}

	private void recalculateFriendAverage() {
		if (super.data.getNumPeople() < 2) {
			data.setAveNumOfFriendships(0);
			return;
		}
		data.setAveNumOfFriendships(((double) super.data.getNumEdges() / 2) / (super.data.getNumPeople()));

	}

}