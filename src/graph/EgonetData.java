package graph;

public class EgonetData extends SocNetData{
	// fields for egonet info
	private int centralEgoUser;
	private int egonetDepth;
	private double aveNumOfFriendships;

	/**
	 * @param centralEgoUser
	 */
	public EgonetData(int centralEgoUser) {
		super(0, 0);
		this.centralEgoUser = centralEgoUser;
		this.egonetDepth = 0;
		this.aveNumOfFriendships = 0;
	}

	/**
	 * @param numPeople
	 * @param numEdges
	 * @param centralEgoUser
	 * @param egonetDepth
	 * @param aveNumOfFriendships
	 */
	public EgonetData(int numPeople, int numEdges, int centralEgoUser, int egonetDepth, double aveNumOfFriendships) {
		super(numPeople, numEdges);
		this.centralEgoUser = centralEgoUser;
		this.egonetDepth = egonetDepth;
		this.aveNumOfFriendships = aveNumOfFriendships;
	}

	/**
	 * @return the centralEgoUser
	 */
	public int getCentralEgoUser() {
		return centralEgoUser;
	}

	/**
	 * @param centralEgoUser the centralEgoUser to set
	 */
	public void setCentralEgoUser(int centralEgoUser) {
		this.centralEgoUser = centralEgoUser;
	}

	/**
	 * @return the egonetDepth
	 */
	public int getEgonetDepth() {
		return egonetDepth;
	}

	/**
	 * @param egonetDepth the egonetDepth to set
	 */
	public void setEgonetDepth(int egonetDepth) {
		this.egonetDepth = egonetDepth;
	}

	/**
	 * @return the aveNumOfFriendships
	 */
	public double getAveNumOfFriendships() {
		return aveNumOfFriendships;
	}

	/**
	 * @param aveNumOfFriendships the aveNumOfFriendships to set
	 */
	public void setAveNumOfFriendships(double aveNumOfFriendships) {
		this.aveNumOfFriendships = aveNumOfFriendships;
	}

}
