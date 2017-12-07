/**
 * @author William Cullian
 * CentralityGraph is a sub class of SocialNetworkGraph designed 
 * to hold centrality metrics and methods
 * 
 */
package graph;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see graph.SocialNetworkGraph#addFriend(int)
	 */
	@Override
	public void addFriend(int num) {
//		// if person is not in people list
//		if (!people.containsKey(num)) {
//			// put it in the list and count it
//			people.put(num, new CentralityUserNode(num));
//			data.incrementNumPeople();
//		}
		super.addFriend(num);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see graph.SocialNetworkGraph#addFriendship(int, int)
	 */
	@Override
	public void addFriendship(int from, int to) {
//		// if the people do not exist, return
//		if (!people.containsKey(from) || !people.containsKey(to)) {
//			return;
//		} else {
//			// create new edge, add it to edge list and count it
//			CentralityEdge newEdge = new CentralityEdge(from, to);
//			if (people.get(from).addFriend(newEdge)) {
//				data.incrementNumEdges();
//			}
//		}
		super.addFriendship(from, to);
	}

	public void analyzeCommunities(int maxSize) {
		printStats();
		LinkedList<CentralityGraph> subCommunities = findIsolatedCommunities();
		printCommunityStats();
		ArrayDeque<CentralityGraph> largeCommunities = extractLargeCommunities(subCommunities, maxSize);
		while (!largeCommunities.isEmpty()) {
			CentralityGraph currCommunity = largeCommunities.pop();
			while (currCommunity.data.getNumPeople() > maxSize) {
				breakStrongestLink(currCommunity);
				// check if it split into two communities
				LinkedList<CentralityGraph> sc = currCommunity.findIsolatedCommunities();
				// remove any larger than maxSize
				ArrayDeque<CentralityGraph> lc = currCommunity.extractLargeCommunities(sc, maxSize);
				// add them to stack
				largeCommunities.addAll(lc);
				// put small ones in list
				subCommunities.addAll(sc);
			}
			printCommunityStats();
		}

	}

	/**
	 * @param currCommunity
	 */
	private void breakStrongestLink(CentralityGraph currCommunity) {
		computeBetweenness(currCommunity);
		removeLargestFlow(currCommunity);
		// TODO Auto-generated method stub
	}

	/**
	 * @param currCommunity
	 */
	private void removeLargestFlow(CentralityGraph currCommunity) {
		currCommunity.removeEdge(currCommunity.data.getEdgeWithHighestFlow());

	}

	/**
	 * @param edgeWithHighestFlow
	 */
	private void removeEdge(CentralityEdge edgeWithHighestFlow) {
		int source = edgeWithHighestFlow.getSource();
		int target = edgeWithHighestFlow.getTarget();
		UserNode sourceNode = people.get(source);
		UserNode targetNode = people.get(target);
		sourceNode.removeEdge(edgeWithHighestFlow);
		targetNode.removeEdge(edgeWithHighestFlow);
		data.decreaseEdgeCount();

	}

	/**
	 * @param currCommunity
	 */
	private void computeBetweenness(CentralityGraph currCommunity) {
		for (Integer currNode : currCommunity.getPeople()) {

		}

	}

	/**
	 * @param subCommunities
	 * @param maxSize
	 * @return
	 */
	private ArrayDeque<CentralityGraph> extractLargeCommunities(LinkedList<CentralityGraph> subCommunities,
			int maxSize) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	private void printCommunityStats() {
		// TODO Auto-generated method stub

	}

	/**
	 * @return
	 */
	private LinkedList<CentralityGraph> findIsolatedCommunities() {

		HashSet<Integer> visited = new HashSet<>();
		LinkedList<CentralityGraph> returnList = new LinkedList<>();
		ArrayDeque<Integer> users = new ArrayDeque<>();
		for (Integer user : people.keySet()) {
			users.push(user);
		}

		while (!users.isEmpty()) {
			int currUser = users.pop();
			if (visited.contains(currUser)) {
				continue;
			}
			visited.add(currUser);
			CentralityGraph subGraph = new CentralityGraph();
			DFSCapture(currUser, visited, subGraph);
			fillInEdges(subGraph);
			returnList.add(subGraph);
		}
		return returnList;
	}

	/**
	 * @param user
	 * @param visited
	 * @param subGraph
	 */
	private void DFSCapture(int user, HashSet<Integer> visited, CentralityGraph subGraph) {
		subGraph.addFriend(user);
		for (Edge friend : people.get(user).getFriends()) {
			int friendNumber = friend.getTarget();
			visited.add(friendNumber);
			DFSCapture(friendNumber, visited, subGraph);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see graph.SocialNetworkGraph#printStats()
	 */
	@Override
	public void printStats() {
		if (data.getNodeWithHighestCentrality() >= 0) {
			System.out.println("Edge with highest flow: " + data.getEdgeWithHighestFlow());
			System.out.println("Node with highest centrality: " + data.getNodeWithHighestCentrality());
			System.out.println("Three highest degree nodes and their egonets: " + data.getHighestDegreeNodes());
			System.out.println("Three highest centrality nodes and their egonets: " + data.getHighestCentralityNodes());
		}

		super.printStats();
	}

}
