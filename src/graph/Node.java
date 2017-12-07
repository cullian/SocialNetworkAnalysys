/**
 * @author William Cullian
 * Dec 6, 2017
 *
 */

package graph;

/**
 * @author William
 *
 */
public interface Node {
	public boolean addFriend(Edge newEdge);
	public int getUserNumber();
	public int getDegree();
	/**
	 * @param i
	 * @param j
	 */
	public void removeEdge(int i, int j);
}
