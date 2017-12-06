/**
 * @author William Cullian
 * Dec 4, 2017
 *
 */

package graph;

/**
 * @author William
 *
 */
public class CentralityEdge extends FriendshipEdge {

	// fields
	private CentralityEdgeData data;

	/**
	 * @param source
	 * @param target
	 */
	public CentralityEdge(int source, int target) {
		super(source, target);
		data = new CentralityEdgeData();
	}

}
