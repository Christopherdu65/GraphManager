import java.util.*;

/**
 * @author Monthe Chris. Raoul
 * Town class which represent attributes of a vertex
 *
 */
public class Town implements Comparable<Town>{

	private String name;
	protected Town backPath; //backpath for shortestpath algo
	protected int weight;
	protected List<Town> adj;

	/**
	 * Parameterized constructor
	 * @param name the name of the town
	 */
	public Town(String name) {
		this.name = name;
		adj = new ArrayList<Town>();
	}
	
	/**
	 * Copy constructor
	 * @param templateTown the town to copy
	 */
	public Town(Town templateTown) {
		this.name = templateTown.name;
		this.adj = templateTown.adj;
	}
	
	/**
	 * getter for name
	 * @return the name of the town
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * getter for weight
	 * @return the weight of a vertex 
	 */
	public int getWt() {
		return weight;
	}
	
	/**
	 * Setter for weight
	 * @param wt the weight of the vertex
	 */
	public void setWt(int wt) {
		this.weight = wt;
	}
	
	@Override
	public int compareTo(Town arg0) {
		
		return this.name.compareTo(arg0.name) ;
	}
	
	/**
	 * getter for backPath
	 * @return backPath for a given vertex(town)
	 */
	public Town getBackPath() {
		return backPath;
	}
	
	/**
	 * setter for backPath
	 * @param backPath
	 */
	public void setBackPath(Town backPath) {
		this.backPath = backPath;
	}

	@Override
	public String toString() {
		return name;
	}
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		Town temp = (Town) obj;
		return this.name.equals(temp.name);
	}
	
	/**
	 * Add adjacent towns to the list of adjacent town of a given town
	 * @param t a town
	 */
	public void AdjacentTown(Town t) {
		if (!adj.contains(t)) {
			adj.add(t);		
			}
	}
}
