package graph;

public class EgonetData implements Data{
	// fields for egonet info
	private int centralEgoUser;
	private int egonetDepth;
	private double aveNumOfFriendships;

	/**
	 * @param centralEgoUser
	 */
	public EgonetData(int centralEgoUser) {
		super();
		this.centralEgoUser = centralEgoUser;
		this.egonetDepth = 0;
		this.aveNumOfFriendships = 0;
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
