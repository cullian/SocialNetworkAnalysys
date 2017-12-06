/**
 * @author William Cullian
 * Dec 4, 2017
 * A sub-class of UserNode used for centrality measurements
 *
 */

package graph;

import java.util.HashSet;

public class CentralityUserNode extends UserNode {

	// fields
	private int userNumber; // number of person
	private HashSet<CentralityEdge> friendships; // list of friends of this person
	private CentralityNodeData data;

	/**
	 * Constructor sets user number with empty edge list
	 * 
	 * @param num
	 */
	public CentralityUserNode(int num) {
		super(num);
		this.userNumber = num;
		this.friendships = new HashSet<CentralityEdge>();
		data = new CentralityNodeData();
	}



}
