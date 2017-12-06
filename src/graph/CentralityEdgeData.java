/**
 * @author William Cullian
 * Dec 4, 2017
 *
 */

package graph;

/**
 * @author William
 *
 */
public class CentralityEdgeData implements Data {
	
	//fields
	private double flow;
	private int sisterEdge;

	/**
	 * 
	 */
	public CentralityEdgeData() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see graph.Data#printData()
	 */
	@Override
	public void printData() {
		// TODO Auto-generated method stub
	
	}

	/**
	 * @return the flow
	 */
	public double getFlow() {
		return flow;
	}

	/**
	 * @param flow the flow to set
	 */
	public void setFlow(double flow) {
		this.flow = flow;
	}

	/**
	 * @return the sisterEdge
	 */
	public int getSisterEdge() {
		return sisterEdge;
	}

	/**
	 * @param sisterEdge the sisterEdge to set
	 */
	public void setSisterEdge(int sisterEdge) {
		this.sisterEdge = sisterEdge;
	}

}
