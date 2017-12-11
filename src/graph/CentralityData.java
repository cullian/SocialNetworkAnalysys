/**
 * 
 * A data class to hold data for CentralityGraph class
 * 
 * @author William Cullian
 *
 */
package graph;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

public class CentralityData implements Data {

	private LinkedList<CentralityGraph> subCommunities;
	private int highestDegreeNode;
	private int highestCentralityNode;
	private HashMap<Edge, Double> edgeBetweenness;

	/**
	 * @param subCommunities
	 * @param highestDegreeNode
	 * @param highestCentralityNode
	 * @param edgeBetweenness
	 */
	public CentralityData() {
		super();
		this.subCommunities = new LinkedList<>();
		this.highestDegreeNode = -1;
		this.highestCentralityNode = -1;
		this.edgeBetweenness = new HashMap<>();
	}

	/**
	 * @return the edgeBetweenness
	 */
	public HashMap<Edge, Double> getEdgeBetweenness() {
		return edgeBetweenness;
	}

	/**
	 * @param edgeBetweenness
	 *            the edgeBetweenness to set
	 */
	public void setEdgeBetweenness(HashMap<Edge, Double> edgeBetweenness) {
		this.edgeBetweenness = edgeBetweenness;
	}

	/**
	 * Set all nodes edge betweenness(flow) to 0
	 * 
	 * @param edgeBetweenness
	 *            the edgeBetweenness to set
	 */
	public void initializeEdgeBetweenness(Collection<Node> nodes) {
		for (Node node : nodes) {
			for (Edge edge : node.getFriends()) {
				this.edgeBetweenness.put(edge, 0.0);
			}
		}
	}

	/**
	 * @param flow
	 */
	public void addFlowMatrix(HashMap<Edge, Double> flow) {
		// for every edge add flows value to edgeBetweenness value
		for (Edge edge : flow.keySet()) {
			double edgeVal = edgeBetweenness.get(edge);
			edgeVal += flow.get(edge);
			edgeBetweenness.put(edge, edgeVal);
		}

	}

	/**
	 * @return the subCommunities
	 */
	public LinkedList<CentralityGraph> getSubCommunities() {
		return subCommunities;
	}

	/**
	 * @param subCommunities
	 *            the subCommunities to set
	 */
	public void addSubCommunity(CentralityGraph subCommunity) {
		this.subCommunities.add(subCommunity);
	}

	/**
	 * @param subCommunities2
	 */
	public void addSubCommunitys(LinkedList<CentralityGraph> scs) {
		this.subCommunities.addAll(scs);
	}

	/**
	 * @param subCommunities
	 *            the subCommunities to set
	 */
	public void removeSubCommunity(SocialNetworkGraph subCommunity) {
		this.subCommunities.remove(subCommunity);
	}

	/**
	 * @return the highestDegreeNode
	 */
	public int getHighestDegreeNode() {
		return highestDegreeNode;
	}

	/**
	 * @param highestDegreeNode
	 *            the highestDegreeNode to set
	 */
	public void setHighestDegreeNode(int highestDegreeNode) {
		this.highestDegreeNode = highestDegreeNode;
	}

	/**
	 * @return the highestCentralityNode
	 */
	public int getHighestCentralityNode() {
		return highestCentralityNode;
	}

	/**
	 * @param highestCentralityNode
	 *            the highestCentralityNode to set
	 */
	public void setHighestCentralityNode(int highestCentralityNode) {
		this.highestCentralityNode = highestCentralityNode;
	}

}
