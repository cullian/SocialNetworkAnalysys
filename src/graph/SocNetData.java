/**
 * 
 */
package graph;

/**
 * @author William
 *
 */
public class SocNetData implements Data{
	private int numPeople;
	private int numEdges;


	/**
	 * @param numPeople
	 * @param numEdges
	 */
	public SocNetData(int numPeople, int numEdges) {
		super();
		this.numPeople = numPeople;
		this.numEdges = numEdges;
	}


	/**
	 * @return the numPeople
	 */
	public int getNumPeople() {
		return this.numPeople;
	}


	/**
	 * @param numPeople the numPeople to set
	 */
	public void setNumPeople(int numPeople) {
		this.numPeople = numPeople;
	}


	/**
	 * @return the numEdges
	 */
	public int getNumEdges() {
		return this.numEdges;
	}


	/**
	 * @param numEdges the numEdges to set
	 */
	public void setNumEdges(int numEdges) {
		this.numEdges = numEdges;
	}


	@Override
	public void printData() {
		System.out.println("People: " + numPeople + " Edges: " + numEdges);		
	}


	public void incrementNumPeople() {
		this.numPeople++;
		
	}


	public void incrementNumEdges() {
		this.numEdges++;
		
	}

}
