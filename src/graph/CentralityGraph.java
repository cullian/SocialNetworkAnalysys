/**
 * @author William Cullian
 * CentralityGraph is a sub class of SocialNetworkGraph designed 
 * to hold centrality metrics and methods
 * 
 */
package graph;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class CentralityGraph extends SocialNetworkGraph {

	private CentralityData data;

	/**
	 * Create a new empty CentralityGraph
	 */
	public CentralityGraph() {
		super();
		data = new CentralityData();
	}

	/**
	 * Create a centrality graph from social network graph
	 * 
	 * @param socialNetworkGraph
	 */
	public CentralityGraph(SocialNetworkGraph socialNetworkGraph) {
		super(socialNetworkGraph);
		data = new CentralityData();

	}

	/**
	 * @param subCommunities
	 */
	public void addSubCommunities(LinkedList<CentralityGraph> subCommunities) {
		data.addSubCommunitys(subCommunities);
	}

	/**
	 * This method uses DFS to separate out disconnected communities
	 * 
	 * @param graph
	 * 
	 * 
	 * @return a list of Graphs of the separate communities
	 */
	LinkedList<CentralityGraph> findIsolatedCommunities() {
		// initialize
		HashSet<Integer> visited = new HashSet<>();
		LinkedList<CentralityGraph> returnList = new LinkedList<>();
		ArrayDeque<Integer> users = new ArrayDeque<>();
		// fill users stack with all the users
		for (Integer user : getPeopleNums()) {
			users.push(user);
		}
		// go thru the stack
		while (!users.isEmpty()) {
			int currUser = users.pop();
			// skip visited
			if (visited.contains(currUser)) {
				continue;
			}
			// add visited
			visited.add(currUser);
			// create sub-graph for the return list
			CentralityGraph subGraph = new CentralityGraph();
			DFSCapture(currUser, visited, subGraph);
			fillInEdges(subGraph);
			// TODO compute betweenness for stats ???
			returnList.add(subGraph);
		}
		return returnList;
	}

	/**
	 * This recursive method performs DFS and makes a sub-graph with all nodes
	 * visited
	 * 
	 * @param userNum
	 *            the current node
	 * @param visited
	 *            the visited list
	 * @param subGraph
	 *            the subgraph being created
	 */
	void DFSCapture(int userNum, HashSet<Integer> visited, SocialNetworkGraph subGraph) {
		// add node to sub-graph
		subGraph.addFriend(userNum);
		Node user = getPerson(userNum);
		// DFS friends
		for (Edge friend : user.getFriends()) {
			int friendNumber = friend.getTarget();
			// check if visited first
			if (!visited.contains(friendNumber)) {
				visited.add(friendNumber);
				DFSCapture(friendNumber, visited, subGraph);
			}
		}

	}

	/**
	 * Given a list of sub communities and maxSize, returns a list
	 * of communities larger than maxSize extracted from list of
	 * sub communities
	 * 
	 * @param subCommunities
	 * @param maxSize
	 * @return
	 */
	ArrayDeque<CentralityGraph> extractLargeCommunities(Queue<CentralityGraph> subCommunities, int maxSize) {
		ArrayDeque<CentralityGraph> returnStack = new ArrayDeque<>();
		int origSize = subCommunities.size();
		for (int i = 0; i < origSize; i++) {
			CentralityGraph subGraph = subCommunities.remove();
			// if it is too big add it to the return stack
			if (subGraph.getNumPeople() > maxSize) {
				returnStack.add(subGraph);
			}
			// else put back in the queue
			else {
				subCommunities.add(subGraph);
			}
		}
		return returnStack;
	}

	/**
	 * Computes betweenness then breaks the strongest link
	 * 
	 * @param currCommunity
	 */
	void breakStrongestLink() {
		computeBetweenness();
		removeLargestFlow();
	}

	/**
	 * Computes centrality of betweenness and flow for graph
	 * 
	 * @param currCommunity
	 */
	void computeBetweenness() {
		// set Centrality of Betweenness to 0 for all nodes
		// set sigma(v,t|v)(shortest paths) to 0 for all nodes
		resetNodesInitial();
		// for all nodes in graph find shortest paths
		// by using BFS starting from each node in graph
		for (int currNode : getPeopleNums()) {
			// reset distance, predecessors and delta for each start
			resetNodes();
			// stack to go back thru the nodes for delta accumulation
			ArrayDeque<Integer> stack = new ArrayDeque<>();
			BFS(currNode, stack);
			// Set all nodes edge betweenness(flow) to 0
			data.initializeEdgeBetweenness(getPeople());
			// go back thru nodes to calculate delta and distribute it to
			// Centrality of betweenness, as well as flow for the edges
			HashMap<Edge, Double> flow = calculateDelta(currNode, stack);
			data.addFlowMatrix(flow);
		}
		findAndSetHighestCentralityAndDegreeNode();
	}

	/**
	 * Resets node before computing betweenness
	 */
	private void resetNodesInitial() {
		for (Node currNode : getPeople()) {
			currNode.setCentralityOfBetweenness(0.0);
			currNode.setShortestPathCount(0);
			currNode.setDistance(Double.POSITIVE_INFINITY);
			currNode.resetPredecessors();
			currNode.setDelta(0.0);
		}
	}

	/**
	 * Resets distance, predecessors and delta only
	 */
	private void resetNodes() {
		for (Node currNode : people.values()) {
			currNode.setDistance(Double.POSITIVE_INFINITY);
			currNode.resetPredecessors();
			currNode.setDelta(0.0);
		}
	}

	/**
	 * Self explanatory
	 */
	private void findAndSetHighestCentralityAndDegreeNode() {
		Collection<Node> nodes = getPeople();
		boolean firstTime = true;
		double highestCentrality = 0;
		int highestCentralityNum = -1;
		int highestDegree = 0;
		int highestDegreeNum = -1;
		for (Node node : nodes) {
			if (firstTime) {
				highestCentrality = node.getCentralityOfBetweenness();
				highestCentralityNum = node.getUserNumber();
				highestDegree = node.getDegree();
				highestDegreeNum = node.getUserNumber();
				firstTime = false;
			}
			double cB = node.getCentralityOfBetweenness();
			int degree = node.getDegree();
			if (cB > highestCentrality) {
				highestCentrality = cB;
				highestCentralityNum = node.getUserNumber();
			}
			if (degree > highestDegree) {
				highestDegree = degree;
				highestDegreeNum = node.getUserNumber();
			}
		}
		data.setHighestCentralityNode(highestCentralityNum);
		data.setHighestDegreeNode(highestDegreeNum);
	}

	/**
	 * Self explanatory
	 */
	private void removeLargestFlow() {
		Edge highestEdge = findEdgeWithLargestFlow();
		removeEdge(highestEdge);

	}

	/**
	 * Self explanatory
	 * 
	 * @return edge with largest flow
	 */
	private Edge findEdgeWithLargestFlow() {
		// get betweenness matrix
		HashMap<Edge, Double> edgeBetweenness = data.getEdgeBetweenness();
		// find edge with largest flow
		double maxFlow = 0.0;
		Edge highestEdge = new Edge(-1, -1);
		boolean firstTime = true;
		for (Edge edge : edgeBetweenness.keySet()) {
			if (firstTime) {
				maxFlow = edgeBetweenness.get(edge);
				highestEdge = edge;
				firstTime = false;
			}
			if (edgeBetweenness.get(edge) > maxFlow) {
				maxFlow = edgeBetweenness.get(edge);
				highestEdge = edge;
			}
		}
		return highestEdge;
	}

	/**
	 * Removes edge and sister edge, reduces edge count
	 * 
	 * @param edgeToRemove
	 */
	private void removeEdge(Edge edgeToRemove) {
		int source = edgeToRemove.getSource();
		int target = edgeToRemove.getTarget();
		Node sourceNode = people.get(source);
		Node targetNode = people.get(target);
		sourceNode.removeEdge(edgeToRemove);
		decreaseEdgeCount();
		Edge sisterEdge = new Edge(target, source);
		targetNode.removeEdge(sisterEdge);
		decreaseEdgeCount();
	}

	/**
	 * Calculates delta for brandes algorithm, also distributes flow
	 * to the edges
	 * 
	 * @param currCommunity
	 * @param stack
	 */
	private HashMap<Edge, Double> calculateDelta(int startNode, ArrayDeque<Integer> stack) {

		HashMap<Edge, Double> flow = new HashMap<>();
		while (!stack.isEmpty()) {
			// pop curr off stack
			int curr = stack.pop();
			// distribute flow
			distributeFlow(curr, startNode, flow);
			// for all curr's predecessors
			HashSet<Integer> preds = getPerson(curr).getPredecessors();
			for (Integer pred : preds) {
				// implement brandes algo
				double deltaV = getPerson(pred).getDelta();
				double deltaW = getPerson(curr).getDelta();
				int sigmaSV = getPerson(pred).getShortestPathCount();
				int sigmaSW = getPerson(curr).getShortestPathCount();
				double magic = ((double) sigmaSV / sigmaSW) * (1 + deltaW);
				// add magic to predecessors delta
				deltaV += magic;
				getPerson(pred).setDelta(deltaV);
				// if its not the start node
				if (curr != startNode) {
					// accumulate delta in Centrality of Betweenness
					double centBetW = getPerson(pred).getCentralityOfBetweenness();
					centBetW += deltaW;
					getPerson(curr).setCentralityOfBetweenness(centBetW);
				}
			}
		}
		return flow;
	}

	/**
	 * Distributes flow to the edges
	 * 
	 * @param curr
	 * @param startNode
	 * @param flow
	 */
	private void distributeFlow(int curr, int startNode, HashMap<Edge, Double> flow) {
		// add the flow from descendants and add 1
		double currDistance = (getPerson(curr)).getDistance();
		double desFlow = 1;
		for (Edge edge : getPerson(curr).getFriends()) {
			int friendNum = edge.getTarget();
			double friendDistance = (getPerson(friendNum)).getDistance();
			if (friendDistance > currDistance) {
				desFlow += flow.get(edge);
			}
		}
		// divide desflow by number of predecessors predecessors to get predflow
		int numShortPaths = 0;
		HashSet<Integer> currPreds = (getPerson(curr)).getPredecessors();
		for (Integer pred : currPreds) {
			numShortPaths += (getPerson(pred)).getPredecessors().size();
		}

		double predFlow = desFlow / numShortPaths;

		// assign flow to edges of predecessors
		for (Integer pred : currPreds) {
			int predsPreds = (getPerson(pred)).getPredecessors().size();
			Edge currEdge = new Edge(pred, curr);
			Edge currEdgeSister = new Edge(curr, pred);
			flow.put(currEdge, predFlow * predsPreds);
			flow.put(currEdgeSister, predFlow * predsPreds);
		}

	}

	/**
	 * Does a BFS from startNode to all available nodes, counting all shortest
	 * paths from every node to find centrality of betweenness for each node as
	 * well as the flow for the edges
	 * 
	 * @param startNode
	 * @param stack
	 */
	private void BFS(Integer startNode, ArrayDeque<Integer> stack) {
		// initialize queue
		LinkedList<Integer> queue = new LinkedList<>();
		queue.add(startNode);
		// set distance of startNode to 0
		getPerson(startNode).setDistance(0.0);
		// while queue is not empty
		while (!queue.isEmpty()) {
			// deque currNode and add it to stack
			int currNodeNum = queue.remove();
			Node currNode = getPerson(currNodeNum);
			stack.push(currNodeNum);
			double sourceDistance = currNode.getDistance();
			// for all currNodes friends
			for (Edge currEdge : currNode.getFriends()) {
				Node currFriend = getPerson(currEdge.getTarget());
				// if currFriend distance is infinity, it hasn't been visited
				// yet, so set distance to source distance + 1 and enque
				// currFriend
				if (currFriend.getDistance() == Double.POSITIVE_INFINITY) {
					// set currFriend distance to source node distance + 1
					currFriend.setDistance(sourceDistance + 1);
					// enque currFriend
					queue.add(currFriend.getUserNumber());
				}
				// if currFriend distance equals sourceDistance + 1
				// it is 1 deeper than source and source is its predecessor
				if (currFriend.getDistance() == sourceDistance + 1) {
					// set shortest path count (start to friend) to
					// SPC(start to friend) + SPC(start to predecessor)
					int SPCFriend = currFriend.getShortestPathCount();
					int SPCPred = currNode.getPredecessors().size();
					currFriend.setShortestPathCount(SPCFriend + SPCPred);
					// add source to predecessors
					currFriend.addPredecessor(currNodeNum);
				}

			}

		}

	}

	/**
	 * Print stats for list of sub communities
	 * 
	 * @param subCommunities
	 */
	void printCommunityStats(Queue<CentralityGraph> subCommunities) {
		System.out.println("\nNumber of sub-communities in this graph: " + subCommunities.size() + "\n");
		int count = 1;
		for (CentralityGraph curr : subCommunities) {
			System.out.println("Community #" + count++);
			System.out.println("Number of people: " + curr.getNumPeople());
			System.out.println("Number of friendships " + curr.getNumFriendships());
			System.out.println("Edge with highest flow " + curr.findEdgeWithLargestFlow());
			System.out.println("Node with highest centrality " + curr.data.getHighestCentralityNode());
			curr.evaluateEgonets(curr.data.getHighestCentralityNode(), 1);
			System.out.println("Node with highest degree " + curr.data.getHighestDegreeNode());
			curr.evaluateEgonets(curr.data.getHighestDegreeNode(), 1);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see graph.SocialNetworkGraph#printStats()
	 */
	@Override
	public void printStats() {
		if (data.getHighestCentralityNode() >= 0) {
			System.out.println("Number of people: " + getNumPeople());
			System.out.println("Number of friendships " + getNumFriendships());
			System.out.println("Edge with highest flow " + findEdgeWithLargestFlow());
			System.out.println("Node with highest centrality" + data.getHighestCentralityNode());
			System.out.println("Node with highest degree" + data.getHighestDegreeNode());
		}

		super.printStats();
	}

}
