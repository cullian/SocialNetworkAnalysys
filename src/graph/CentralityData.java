/**
 * @author William Cullian
 * Dec 4, 2017
 *
 */

package graph;

import java.util.HashSet;
import java.util.LinkedList;

public class CentralityData extends SocNetData {

	// fields
	private CentralityEdge edgeWithHighestFlow;
	private int nodeWithHighestCentrality;
	private LinkedList<EgonetGraph> highestDegreeNodes;
	private LinkedList<EgonetGraph> highestCentralityNodes;

	/**
	 * @param numPeople
	 * @param numEdges
	 * @param edgeWithHighestFlow
	 * @param nodeWithHighestCentrality
	 * @param highestDegreeNodes
	 */
	public CentralityData(int numPeople, int numEdges, CentralityEdge edgeWithHighestFlow, int nodeWithHighestCentrality,
			LinkedList<EgonetGraph> highestDegreeNodes) {
		super(numPeople, numEdges);
		this.edgeWithHighestFlow = edgeWithHighestFlow;
		this.nodeWithHighestCentrality = nodeWithHighestCentrality;
		this.highestDegreeNodes = highestDegreeNodes;
	}

	public CentralityData() {
		super(0, 0);
		this.edgeWithHighestFlow = null;
		this.nodeWithHighestCentrality = -1;
		this.highestDegreeNodes = null;
	}

	/**
	 * @return the edgeWithHighestFlow
	 */
	public CentralityEdge getEdgeWithHighestFlow() {
		return edgeWithHighestFlow;
	}

	/**
	 * @param edgeWithHighestFlow the edgeWithHighestFlow to set
	 */
	public void setEdgeWithHighestFlow(CentralityEdge edgeWithHighestFlow) {
		this.edgeWithHighestFlow = edgeWithHighestFlow;
	}

	/**
	 * @return the nodeWithHighestCentrality
	 */
	public int getNodeWithHighestCentrality() {
		return nodeWithHighestCentrality;
	}

	/**
	 * @param nodeWithHighestCentrality the nodeWithHighestCentrality to set
	 */
	public void setNodeWithHighestCentrality(int nodeWithHighestCentrality) {
		this.nodeWithHighestCentrality = nodeWithHighestCentrality;
	}

	/**
	 * @return the highestDegreeNodes
	 */
	public LinkedList<EgonetGraph> getHighestDegreeNodes() {
		return highestDegreeNodes;
	}

	/**
	 * @param highestDegreeNodes the highestDegreeNodes to set
	 */
	public void setHighestDegreeNodes(LinkedList<EgonetGraph> highestDegreeNodes) {
		this.highestDegreeNodes = highestDegreeNodes;
	}

	/**
	 * @return the highestCentralityNodes
	 */
	public LinkedList<EgonetGraph> getHighestCentralityNodes() {
		return highestCentralityNodes;
	}

	/**
	 * @param highestCentralityNodes the highestCentralityNodes to set
	 */
	public void setHighestCentralityNodes(LinkedList<EgonetGraph> highestCentralityNodes) {
		this.highestCentralityNodes = highestCentralityNodes;
	}

}
