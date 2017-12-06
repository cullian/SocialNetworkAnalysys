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

}
