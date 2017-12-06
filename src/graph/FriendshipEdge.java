/**
 * @author William Cullian
 * 
 * A class which represents a graph edge(friendship) connecting
 * two user nodes(people) in the SocialNetworkGraph Object.  
 * These are directional edges, so a friendship will require two
 * edges.  The Class contains the from node's user number 
 * as well as the to node's user number.
 *
 */
package graph;

class FriendshipEdge implements Comparable<FriendshipEdge> {
	private int source;
	private int target;

	/**
	 * Constructor
	 * 
	 * @param source
	 *            - user number of from node
	 * @param target
	 *            - user number of to node
	 */
	public FriendshipEdge(int source, int target) {
		super();
		this.source = source;
		this.target = target;
	}

	/**
	 * Getter for to node location
	 * 
	 * @return the target
	 */
	public int getTarget() {
		return target;
	}

	/**
	 * Getter for from node location
	 * 
	 * @return the source
	 */
	public int getSource() {
		return source;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FriendshipEdge [from=" + source + ", to=" + target + "]";
	}

	/**
	 * compareTo function for comparing edges
	 * 
	 * @return 0 of they have the same to and from, else sort by to, 
	 * 	then from
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(FriendshipEdge otherEdge) {
		final int BEFORE = -1;
		final int EQUAL = 0;
		final int AFTER = 1;

		// this optimization is usually worthwhile
		if (this == otherEdge)
			return EQUAL;

		if (this.target == otherEdge.target && this.source == otherEdge.source)
			return EQUAL;
		if (this.target < otherEdge.target)
			return BEFORE;
		if (this.target > otherEdge.target)
			return AFTER;
		if (this.source > otherEdge.source)
			return AFTER;
		return BEFORE;
	}

}
