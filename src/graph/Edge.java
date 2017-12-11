/**
 * @author William Cullian
 * 
 * A class which represents a graph edge(friendship) connecting
 * two user nodes(people) in the SocialNetworkGraph Object.  
 * These are directional edges, so a friendship will require two
 * edges.  The Class contains the from node's user number 
 * as well as the to node's user number.  Edges are comparable.
 *
 */
package graph;

class Edge implements Comparable<Edge> {
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
	public Edge(int source, int target) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Edge [from=" + source + ", to=" + target + "]";
	}

	/**
	 * compareTo function for comparing edges
	 * 
	 * @return 0 of they have the same to and from, else sort by to, then from
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Edge otherEdge) {
		final int BEFORE = -1;
		final int EQUAL = 0;
		final int AFTER = 1;

		// this optimization is usually worthwhile
		if (this == otherEdge)
			return EQUAL;

		if (this.target == otherEdge.getTarget() && this.source == otherEdge.getSource())
			return EQUAL;
		if (this.target < otherEdge.getTarget())
			return BEFORE;
		if (this.target > otherEdge.getTarget())
			return AFTER;
		if (this.source > otherEdge.getSource())
			return AFTER;
		return BEFORE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + source;
		result = prime * result + target;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (source != other.source)
			return false;
		if (target != other.target)
			return false;
		return true;
	}

}
