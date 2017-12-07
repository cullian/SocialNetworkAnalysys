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
public interface Edge {
	public int getTarget();
	public int getSource();
	public int compareTo(Edge newEdge);
}
