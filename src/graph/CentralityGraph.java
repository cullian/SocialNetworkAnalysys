/**
 * @author William Cullian
 * CentralityGraph is a sub class of SocialNetworkGraph designed 
 * to hold centrality metrics and methods
 * 
 */
package graph;

import java.util.HashMap;
import java.util.Map;

public class CentralityGraph extends SocialNetworkGraph {

	private Map<Integer, CentralityUserNode> people;
	private CentralityData data;

	/**
	 * Create a new empty CentralityGraph
	 */
	public CentralityGraph() {
		this.people = new HashMap<Integer, CentralityUserNode>();
		data = new CentralityData();
	}


	public void analyzeCommunities(int maxSize) {
		printStats();
		
	}


	/* (non-Javadoc)
	 * @see graph.SocialNetworkGraph#printStats()
	 */
	@Override
	public void printStats() {
		// TODO Auto-generated method stub
		super.printStats();
	}


}
