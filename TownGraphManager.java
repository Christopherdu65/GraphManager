import java.util.ArrayList;
import java.util.Collections;


/**
 * @author Monthe Chris. Raoul
 *Manager class for the graph class
 */
public class TownGraphManager implements TownGraphManagerInterface {
	Graph m;
	
	public TownGraphManager() {
		m = new Graph();
	}
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		Town v1 = getTown(town1);
		Town v2 = getTown(town2);
		Road r = m.addEdge(v1,v2, weight, roadName);
		return r != null;
	}

	@Override
	public String getRoad(String town1, String town2) {
		Town v1 = getTown(town1);
		Town v2 = getTown(town2);
		Road r = m.getEdge(v1, v2);
		return r.getName();
	}

	@Override
	public boolean addTown(String v) {
		Town v1 = new Town(v);
		return m.addVertex(v1);
	}

	@Override
	public Town getTown(String name) {
		//Town v = new Town(name);
		for(Town v: m.vertexSet()) {
			if(v.getName().equals(name)) {
				return v;
			}
		}
		return null;
	}

	@Override
	public boolean containsTown(String v) {
		Town v1 = new Town(v);
		return m.containsVertex(v1);
	}

	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		Town v1 = getTown(town1);
		Town v2 = getTown(town2);
		return m.containsEdge(v1, v2);
	}

	@Override
	public ArrayList<String> allRoads() {
		ArrayList<String> result = new ArrayList<>();
		for(Road edge : m.edgeSet()) {
			result.add(edge.getName());
		}
		Collections.sort(result);
		return result;
	}

	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		Town v1 = getTown(town1);
		Town v2 = getTown(town2);
		Road edge = new Road(v1, v2, road);
		m.removeEdge(v1, v2, edge.getWeight(), road);
		return !m.containsEdge(v1, v2);
	}

	@Override
	public boolean deleteTown(String v) {
		Town v1 = getTown(v);
		if(!m.containsVertex(v1)) return false;
		else {
			m.removeVertex(v1);
			return true;
		}
	}

	@Override
	public ArrayList<String> allTowns() {
		ArrayList<String> result = new ArrayList<>();
		for(Town v: m.vertexSet()) {
			result.add(v.getName());
		}
		Collections.sort(result);
		return result;
	}

	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		ArrayList<String> result = new ArrayList<>();
		Town v1 = getTown(town1);
		Town v2 =  getTown(town2);
		if(v1 != null && v2 != null){
			result =  m.shortestPath(v1, v2);
//			Collections.sort(result);
			return result;
	}
		return null;
//		if(v1 != null && v2 != null) {
//			m.dijkstraShortestPath(v1);
//			Town dest = v2;
//			Town prev = dest.getBackPath();
//			while(prev != null) {
//				Road r = m.getEdge(prev, dest);
//				result.add(prev.getName() + " via " + r.getName() + " to " + dest.getName() + " " + r.getWeight() +  " mi");
//				dest = prev;
//				prev = prev.backPath;
//	}
//		}
//		return result;
	}
}
