/**
 * @author William Cullian
 * 
 * SocialNetworkGraph is a graph class implementing an adjacency list
 * to store and analyze social network data.  It uses Node and 
 * Edge classes to store the users and their friendship 
 * connections.
 *
 */
package graph;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import util.GraphLoader;

public class SocialNetworkGraph implements Graph {

	// map of user numbers mapped to nodes for respective people.
	protected Map<Integer, Node> people;
	private int numPeople;
	private int numEdges;

	/**
	 * Create a new empty SocialNetworkGraph
	 */
	public SocialNetworkGraph() {
		super();
		this.people = new HashMap<Integer, Node>();
		this.numPeople = 0;
		this.numEdges = 0;
	}

	/**
	 * Create new SocialNetworkGraph from SocialNetworkGraph
	 * 
	 * @param socialNetworkGraph
	 */
	public SocialNetworkGraph(SocialNetworkGraph socialNetworkGraph) {
		super();
		this.people = new HashMap<Integer, Node>(socialNetworkGraph.people);
		this.numPeople = socialNetworkGraph.numPeople;
		this.numEdges = socialNetworkGraph.numEdges;
	}

	/**
	 * Get the number of people in the graph
	 * 
	 * @return The number of people in the graph.
	 */
	public int getNumPeople() {
		return numPeople;
	}

	/**
	 * Return the people numbers, which are the vertices in this graph.
	 * 
	 * @return The vertices in this graph as a set of integers
	 */
	public Set<Integer> getPeopleNums() {
		return people.keySet();
	}

	/**
	 * Return the people, which are the vertices in this graph.
	 * 
	 * @return The vertices in this graph
	 */
	public Collection<Node> getPeople() {
		return people.values();
	}

	/**
	 * Get a single user node
	 * 
	 * @param i
	 */
	public Node getPerson(int userNumber) {
		return people.get(userNumber);

	}

	/**
	 * Get the number of friendships in the graph
	 * 
	 * @return The number of friendships in the graph.
	 */
	public int getNumFriendships() {
		return numEdges / 2;
	}

	/**
	 * 
	 */
	public void decreaseEdgeCount() {
		numEdges--;
	}

	/**
	 * Prints all the vertices and their respective edge lists for debugging
	 * purposes
	 */
	@Override
	public void printGraph() {
		for (Node node : people.values()) {
			System.out.println(node);
		}
	}

	/**
	 * Prints the number of people and the number of friendships, etc.
	 * 
	 */
	public void printStats() {
		System.out.println("Number of people in graph: " + numPeople);
		System.out.println("Number of friendships in graph: " + getNumFriendships());
		System.out.println("Maximum possible number of friendships in graph: " + (numPeople * (numPeople - 1)) / 2);
		System.out.println("Density of graph(num edges/ max num edges): " + getDensity());
		System.out.println("Degree Distribution:");
		TreeMap<Integer, Integer> degrees = getDegreeDistribution();
		int highestValue = 0;
		int d = 0;
		for (Integer degree : degrees.keySet()) {
			if (degrees.get(degree) > highestValue) {
				highestValue = degrees.get(degree);
				d = degree;
			}
			System.out.println("Number of users with degree " + degree + ": " + degrees.get(degree));
		}
		System.out.println("Degree with most users: " + highestValue + " users" + " with degree " + d);
	}

	/**
	 * Add a node corresponding to an person mapped to the person number If the
	 * person is already in the graph, this method does not change the graph.
	 * 
	 * @param num
	 *            The number of the person.
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see graph.Graph#addVertex(int)
	 */
	@Override
	public void addFriend(int num) {
		// if person is not in people list
		if (!people.containsKey(num)) {
			// put it in the list and count it
			people.put(num, new Node(num));
			numPeople++;
		}
	}

	/**
	 * Adds a directed edge to the graph from person number 1 to person number
	 * 2. Precondition: Both people have already been added to the graph If the
	 * people numbers do not exist in the graph, it does nothing
	 * 
	 * @param from
	 *            The starting point of the edge
	 * @param to
	 *            The ending point of the edge
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see graph.Graph#addEdge(int, int)
	 */
	@Override
	public void addFriendship(int from, int to) {
		// if the people do not exist, return
		if (!people.containsKey(from) || !people.containsKey(to)) {
			return;
		} else {
			// create new edge, add it to edge list and count it
			Edge newEdge = new Edge(from, to);
			if (people.get(from).addFriend(newEdge)) {
				numEdges++;
			}
		}

	}

	/**
	 * @return the graph density
	 */
	public double getDensity() {
		if (numPeople < 2) {
			return 0.0;
		}
		double graphDensity = numEdges / ((double) (numPeople * (numPeople - 1)));
		return graphDensity;
	}

	/**
	 * This method returns a representation of the graph
	 * 
	 * @return Hashmap adjacancy list of the graph
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see graph.Graph#exportGraph()
	 */
	@Override
	public HashMap<Integer, HashSet<Integer>> exportGraph() {
		// create object to return
		HashMap<Integer, HashSet<Integer>> ret = new HashMap<Integer, HashSet<Integer>>();
		// iterate thru each node
		for (Node node : people.values()) {
			// make hashset of nodes neighbors
			HashSet<Integer> neighbors = new HashSet<Integer>();
			for (Edge edge : node.getFriends()) {
				neighbors.add(edge.getTarget());
			}
			// add node and neighbors to return object
			ret.put(node.getUserNumber(), neighbors);
		}
		return ret;
	}

	/**
	 * @return degree distribution
	 */
	public TreeMap<Integer, Integer> getDegreeDistribution() {
		TreeMap<Integer, Integer> dd = new TreeMap<>();
		for (Node user : people.values()) {
			int degree = user.getDegree();
			if (dd.containsKey(degree)) {
				int value = dd.get(degree);
				dd.put(degree, value + 1);
			} else {
				dd.put(degree, 1);
			}
		}
		return dd;
	}

	/**
	 * This method returns a transposed representation of the graph
	 * 
	 * @return Hashmap adjacancy list of the transposed graph
	 */
	public HashMap<Integer, HashSet<Integer>> exportTranspose() {
		// create object to return
		HashMap<Integer, HashSet<Integer>> ret = new HashMap<Integer, HashSet<Integer>>();
		// create new CapGraph with edges reversed
		SocialNetworkGraph transposeGraph = new SocialNetworkGraph();
		// iterate thru each node add all the vertices to transposeGraph
		for (Node node : people.values()) {
			int user = node.getUserNumber();
			transposeGraph.addFriend(user);
		}
		// now go thru again and add the edges transposed
		for (Node node : people.values()) {
			for (Edge edge : node.getFriends()) {
				transposeGraph.addFriendship(edge.getTarget(), edge.getSource());
			}
		}
		ret = transposeGraph.exportGraph();
		return ret;
	}

	/**
	 * This method generates egonets for user and prints stats
	 * 
	 * @return List of egonets
	 */
	public List<Graph> evaluateEgonets(int user, int depth) {
		// generate return list of egonets to depth depth, for user
		List<Graph> egonets = this.getEgonets(user, depth);
		// print stats
		System.out.println("\nEgonets for " + user + " to depth " + depth);
		for (Graph graph : egonets) {
			((EgonetGraph) graph).printStats();
			System.out.println();
		}
		return egonets;
	}

	/**
	 * This method takes an int which is the node/user at the center of the
	 * desired egonet, and returns a linked list of depth Egonet graphs for the
	 * user. If there are no graphs of that depth, it will go as deep as it can
	 * and return maximum possible depth. The returned graph should not share
	 * any objects with the original graph. E.g. if your vertices or edges are
	 * represented using objects, the returned graph should contain copies of
	 * all of the vertex and edge objects. If the vertex center is not present
	 * in the original graph, this method should return an empty Graph.
	 * 
	 * @param centerUser
	 *            The starting point of the egonet
	 * @param depth
	 *            The depth of the egonet
	 * @return a subgraph of the egonet for center
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see graph.Graph#getEgonet(int)
	 */
	public List<Graph> getEgonets(int centerUser, int depth) {
		// check if centerUser exists
		if (!people.containsKey(centerUser)) {
			return new LinkedList<Graph>();
		}
		// initialize
		LinkedList<LinkedList<Integer>> qOfQueues = new LinkedList<>();
		LinkedList<Integer> queue = new LinkedList<>();
		HashSet<Integer> visited = new HashSet<>();
		List<Graph> returnList = new LinkedList<>();
		int depthCounter = 0;
		queue.addLast(centerUser);
		qOfQueues.addLast(queue);
		visited.add(centerUser);

		// create new graph for centerUser to return
		EgonetGraph egonet = new EgonetGraph(centerUser);

		// while qOfQueues is not empty and depth not too deep
		while (!qOfQueues.isEmpty() && depthCounter < depth) {
			depthCounter++;
			// deque current queue
			LinkedList<Integer> currQueue = qOfQueues.removeFirst();
			// create nextQueue to hold next hop out nodes
			LinkedList<Integer> nextQueue = new LinkedList<>();
			// while queue is not empty go thru queue and build egonet
			// one hop out at a time
			while (!currQueue.isEmpty()) {
				// init next hop out queue
				// nextQueue = new LinkedList<>();
				// get current node(user)
				Node curr = people.get(currQueue.removeFirst());
				// loop thru edges(friends)
				for (Edge friend : curr.getFriends()) {
					// get friends number
					int friendNumber = friend.getTarget();
					// if already visited, continue
					if (visited.contains(friendNumber)) {
						continue;
					}
					// add to visited
					visited.add(friendNumber);
					// enque user to nextQueue
					nextQueue.addLast(friendNumber);
					// add friend to egonet
					egonet.addFriend(friendNumber);
				}
			}
			// add nextQueue to qOfQueues
			if (!nextQueue.isEmpty()) {
				qOfQueues.addLast(nextQueue);
			}
			// add edges to egonet
			fillInEdges(egonet);
			// set egonet depth
			egonet.setEgonetDepth(depthCounter);
			// add copy of egonet to list of egonets
			returnList.add(egonet.exportEgonet());

		}
		return returnList;
	}

	/**
	 * This method returns all of the strongly connected components in the Graph
	 * as a list of subgraphs. As with getEgonet, the returned graphs should not
	 * share any objects with the original graph.
	 * 
	 * @return a list of subgraphs of SCC's
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see graph.Graph#getSCCs()
	 */
	public List<Graph> getSCCs() {
		// create list of scc graph objects to return
		List<Graph> sccGraphs = new LinkedList<Graph>();
		// create stack of user numbers(integers) to visit
		ArrayDeque<Integer> vertices = new ArrayDeque<Integer>();
		for (Integer userNumber : people.keySet()) {
			vertices.push(userNumber);
		}
		// call DFS on stack
		ArrayDeque<Integer> finished = DFS(this.exportGraph(), vertices, sccGraphs);
		// compute transpose of G
		HashMap<Integer, HashSet<Integer>> Gt = this.exportTranspose();
		// throw out previous scc's
		sccGraphs = new LinkedList<Graph>();
		// call dfs on transpose with finished stack
		DFS(Gt, finished, sccGraphs);

		return sccGraphs;
	}

	/**
	 * This method performs a depth first search of all the vertices in vertices
	 * and returns a stack in the order in which the vertices finish
	 * 
	 * @param G:
	 *            the graph we are working with
	 * @param vertices:
	 *            a stack of all the vertices in G
	 * @return a list of subgraphs of SCC's
	 */
	private ArrayDeque<Integer> DFS(HashMap<Integer, HashSet<Integer>> G, ArrayDeque<Integer> vertices,
			List<Graph> sccGraphs) {
		// create visited hashset
		HashSet<Integer> visited = new HashSet<Integer>();
		// create stack of user numbers(integers) to visit
		ArrayDeque<Integer> finished = new ArrayDeque<Integer>();

		SocialNetworkGraph scc = new SocialNetworkGraph();
		// while there are still users to visit
		while (!vertices.isEmpty()) {
			int v = vertices.pop();
			if (!visited.contains(v)) {
				DFSVisit(G, v, visited, finished, scc);
				// every time it gets back here, its an scc
				// the modified scc, only has vertices
				// the edges must be filled in
				fillInEdges(scc);
				// add to sccGraphs
				sccGraphs.add(scc);
				// create new graph for next scc
				scc = new SocialNetworkGraph();
			}

		}
		return finished;
	}

	/**
	 * This method visits each vertex in a SCC, then pushes each vertex back
	 * onto the finished stack, deepest first
	 * 
	 * @param G:
	 *            the graph we are working with
	 * @param v:
	 *            the vertex being visited
	 * @param visited:
	 *            a list of all the vertices visited so far
	 * @param finished:
	 *            a stack of all the vertices in in finished order
	 */
	private void DFSVisit(HashMap<Integer, HashSet<Integer>> G, int v, HashSet<Integer> visited,
			ArrayDeque<Integer> finished, Graph scc) {
		// add v to visited
		visited.add(v);
		// add them to graph of scc
		scc.addFriend(v);
		// for all v's friends
		for (Integer friend : G.get(v)) {
			// if friend has not been visited
			if (!visited.contains(friend)) {
				// visit them
				DFSVisit(G, friend, visited, finished, scc);
			}
		}
		finished.push(v);
	}

	/**
	 * This method fills in the edges in a graph that only contains the vertexes
	 * of the scc. This is necessary because addEdge will not add an edge to
	 * vertices that are not in the graph, so I cannot add them as the graph is
	 * being built. This also allows me to add all the edges from the graph to
	 * the scc graph and any edges to nodes not in the scc graph will not be
	 * created.
	 * 
	 * @param graph:
	 *            a graph with nodes only
	 */
	protected void fillInEdges(SocialNetworkGraph graph) {
		// for each node in egonet
		for (Integer node : graph.getPeopleNums()) {
			// for each edge in each node in parent graph
			for (Edge edge : people.get(node).getFriends()) {
				// add the edge to egonet graph
				graph.addFriendship(edge.getSource(), edge.getTarget());
			}
		}

	}

	/**
	 * This method will analyze the graph for sub communities
	 * 
	 * @param maxSize
	 *            the maximum size for the sub communities
	 */
	public void analyzeCommunities(int maxSize) {
		// create a centrality graph for analysis
		CentralityGraph centralityGraph = new CentralityGraph(this);
		// print stats for graph
		System.out.println("\nInitial Graph:");
		centralityGraph.printStats();
		// print list of initial sub communities in graph
		LinkedList<CentralityGraph> subCommunities = centralityGraph.findIsolatedCommunities();
		// compute betweenness to get stats for all communities
		for (CentralityGraph sc : subCommunities) {
			sc.computeBetweenness();
		}
		System.out.println("\nInitial communities:");
		centralityGraph.printCommunityStats(subCommunities);
		// remove communities larger than maxSize from subCommunities
		// and put in stack
		ArrayDeque<CentralityGraph> largeCommunities = centralityGraph.extractLargeCommunities(subCommunities, maxSize);
		// break down large communities
		while (!largeCommunities.isEmpty()) {
			// pop one
			CentralityGraph currCommunity = largeCommunities.pop();
			// break link
			currCommunity.breakStrongestLink();
			// check if it split into two communities
			LinkedList<CentralityGraph> subComm = currCommunity.findIsolatedCommunities();
			// compute betweenness to get stats for all communities
			for (CentralityGraph subGraph : subComm) {
				subGraph.computeBetweenness();
			}
			// remove any larger than maxSize
			ArrayDeque<CentralityGraph> lc = currCommunity.extractLargeCommunities(subComm, maxSize);
			// add them to stack
			largeCommunities.addAll(lc);
			// put small ones in list
			subCommunities.addAll(subComm);
		}
		// TODO put subcommunities in data
		centralityGraph.addSubCommunities(subCommunities);
		System.out.println("\nFinal breakdown of communities smaller than " + maxSize + ":");
		centralityGraph.printCommunityStats(subCommunities);

	}

	public static void testing() {
		SocialNetworkGraph test = new SocialNetworkGraph();
		// SocialNetworkGraph test2 = new SocialNetworkGraph();
		// SocialNetworkGraph test3 = new SocialNetworkGraph();
		GraphLoader.loadGraph(test, "data/facebook_ucsd.txt");
		// GraphLoader.loadGraph(test2, "data/test5.txt");
		// GraphLoader.loadGraph(test3, "data/test6.txt");
		// test2.addFriend(0);
		// test2.addFriend(25);
		// test.printStats();
		// test.printGraph();
		// System.out.println("\nRemove edge from 3 to 4-------------");
		// test.removeEdge(3, 4);
		// test.printStats();
		// test.printGraph();
		test.analyzeCommunities(5000);

	}

	public static void main(String[] args) {
		testing();

		// System.out.print("Making new maps...");
		// SocialNetworkGraph soloEgo = new SocialNetworkGraph();
		// SocialNetworkGraph sparse = new SocialNetworkGraph();
		// SocialNetworkGraph medium = new SocialNetworkGraph();
		// SocialNetworkGraph full = new SocialNetworkGraph();
		// SocialNetworkGraph facebook = new SocialNetworkGraph();
		// System.out.print("DONE. \nLoading the maps...");
		// GraphLoader.loadGraph(sparse, "data/small_test_graph.txt");
		// GraphLoader.loadGraph(medium, "data/test2.txt");
		// GraphLoader.loadGraph(full, "data/test3.txt");
		// GraphLoader.loadGraph(facebook, "data/facebook_ucsd.txt");
		// soloEgo.addFriend(1);
		// System.out.println("DONE.");
		// System.out.println("PRINT GRAPH SOLO---------------------");
		// soloEgo.printGraph();
		// System.out.println("PRINT GRAPH SPARSE---------------------");
		// sparse.printGraph();
		// System.out.println("PRINT GRAPH MEDIUM---------------------");
		// medium.printGraph();
		// System.out.println("PRINT GRAPH FULL---------------------");
		// full.printGraph();
		// System.out.println("PRINT GRAPH FACEBOOK DATA---------------------");
		// facebook.printGraph();
		// System.out.println("TEST MAP 1 SOLO ---------------------");
		// soloEgo.evaluateEgonets(1, 3);
		// soloEgo.evaluateEgonets(10, 3);
		// System.out.println("TEST MAP 2 SPARSE ---------------------");
		// sparse.evaluateEgonets(1, 3);
		// sparse.evaluateEgonets(7, 3);
		// System.out.println("TEST MAP 3 MEDIUM ---------------------");
		// medium.evaluateEgonets(1, 3);
		// medium.evaluateEgonets(7, 3);
		// System.out.println("TEST MAP 4 FULL ---------------------");
		// full.evaluateEgonets(1, 3);
		// full.evaluateEgonets(4, 3);
		// System.out.println("FACEBOOK UCSD DATA---------------------");
		// facebook.evaluateEgonets(0, 3);
		// facebook.evaluateEgonets(1, 3);
		// facebook.evaluateEgonets(7, 3);
		// facebook.evaluateEgonets(94, 3);
		// facebook.evaluateEgonets(14945, 3);

	}

}
