
/**
 * @author Monthe Chris. Raoul
 * Road class which represents attributes of an edge
 *
 */
public class Road implements Comparable<Road>{
	private Town source;
	private Town destination;
	private int weight;
	private String name;

	/**
	 * Parameterized constructor
	 * @param source the beginning town
	 * @param destination the last town 
	 * @param degrees weight of the edges
	 * @param name name of the edge
	 */
	public Road(Town source, Town destination, int degrees,
				String name) {
		this.source = source;
		this.destination = destination;
		this.weight = degrees;
		this.name = name;
		source.AdjacentTown(destination);
		destination.AdjacentTown(source);
	}
	
	/**
	 * Parameterized constructor with constant weight for edge
	 * @param source the beginning town
	 * @param destination the last town
	 * @param name name of the edge
	 */
	public Road(Town source, Town destination, String name) {
		this.source = source;
		this.destination = destination;
		weight = 1;
		this.name = name;
		source.AdjacentTown(destination);
		destination.AdjacentTown(source);
	}
	
	/**
	 * @param town
	 * @return true only if the edge is connected to the given vertex
	 */
	public boolean contains(Town town) {
		return town.getName().equals(source.getName()) ||
				town.getName().equals(destination.getName());
	}
	
	/**
	 * getter for name
	 * @return the name of the edge
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * getter for destination
	 * @return the destination(town)
	 */
	public Town getDestination() {
		return destination;
	}
	
	/**
	 * getter for source
	 * @return the beginning town or source
	 */
	public Town getSource() {
		return source;
		}
	@Override
	public int compareTo(Road o) {
		return this.name.compareTo(o.name);
	}
	
	/**
	 * getter for weight
	 * @return the weight of the edge
	 */
	public int getWeight() {
		return weight;
	}

	@Override
	public boolean equals(Object r) {
		Road temp = (Road) r;
		return (this.source.equals(temp.source) || this.source.equals(temp.destination)
				&& this.destination.equals(temp.source) || this.destination.equals(temp.destination));
	}
		@Override
		public String toString() {
			return name;
		}
	}

