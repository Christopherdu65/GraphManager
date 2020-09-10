import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Christopher R.
 *A graph implementation
 */
public class Graph implements GraphInterface<Town, Road>{
		private Set<Town> vertices;
		private Set<Road> edges;
		private Set<Town> open;
		private Set<Town> closed;
		
		/**
		 * Default constructor, will instantiate each sets
		 */
		public Graph() {
			vertices = new HashSet<Town>();
			edges = new HashSet<Road>();
			open = new HashSet<Town>();
			closed = new HashSet<Town>();
		}
		
		@Override
		public Road getEdge(Town sourceVertex, Town destinationVertex) {
			for(Road r: edges)
				if(r.contains(sourceVertex) && r.contains(destinationVertex)) {
					return r;
				}
			return null;
		}

		@Override
		public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
			Road r = new Road(sourceVertex, destinationVertex, weight, description);
			if(sourceVertex == null || destinationVertex == null) {
				throw new NullPointerException("Vertex is null");
			}
			else if(!vertices.contains(sourceVertex) || !vertices.contains(destinationVertex)) {
				throw new IllegalArgumentException("Vertex not found");
			}
			else
				edges.add(r);
				return r;
		}

		@Override
		public boolean addVertex(Town v) {
			if(v == null) throw new NullPointerException("Vertex is null");
			if(vertices.contains(v))return false;
			else {
				vertices.add(v);
				return true;
			}
		}

		@Override
		public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
			for(Road r: edges) {
				if(r.contains(sourceVertex) && r.contains(destinationVertex)) {return true;}
			}
			return false;
		}

		@Override
		public boolean containsVertex(Town v) {
			if(vertices.contains(v))
				return true;
			return false;
		}

		@Override
		public Set<Road> edgeSet() {
			return edges;
		}

		@Override
		public Set<Road> edgesOf(Town vertex) {
			if(vertex == null) {throw new NullPointerException("Vertex is null");}
			if(!vertices.contains(vertex)) {throw new IllegalArgumentException("Vertex not found");}
			Set<Road> edgOf = new HashSet<>();
			for(Town v: vertices ) {
				if(containsEdge(vertex, v)) {
					edgOf.add(getEdge(vertex, v));
				}
			}

			return edgOf;
		}

		@Override
		public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
			
			Set<Road> toRemove = new HashSet<>();
			if(description == null || weight == -1) {
				return null;
			}
			else {
				Road edge = new Road(sourceVertex, destinationVertex, weight, description);
				for(Road r : edges) {
				if(r.contains(sourceVertex) && r.contains(destinationVertex)) {
					toRemove.add(r);
				}
			}
			edges.removeAll(toRemove);
				//edges.remove(edge);
			return edge;}
		}

		@Override
		public boolean removeVertex(Town v) {
			Set<Road> toRemove = new HashSet<>();
			if(v == null || !vertices.contains(v))return false;
			else {
				for(Town vertex: vertices) {
					if(containsEdge(vertex, v)) {
						toRemove.add(getEdge(vertex, v));
					}
				}
				edges.removeAll(toRemove);
				vertices.remove(v);
				return true;
			}

		}

		@Override
		public Set<Town> vertexSet() {
			return vertices;
		}


		@Override
		public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
			ArrayList<String> res = new ArrayList<String>();
			//boolean isAnEdge = false;
			if(edgesOf(sourceVertex).size() == 0 || edgesOf(destinationVertex).size() == 0) {
				return res;
			}
			dijkstraShortestPath(sourceVertex);
			Town dest = destinationVertex;
			Town prev = dest.getBackPath();
			while(prev != null) {
				Road r = getEdge(prev, dest);
				res.add(prev.getName() + " via " + r.getName() + " to " + dest.getName() + " " + r.getWeight() +  " mi");
				dest = prev;
				prev = prev.backPath;
		}
			Collections.reverse(res);
			return res;
		}
		

		@Override
		public void dijkstraShortestPath(Town sourceVertex) {
			open.addAll(vertices);
			closed.add(sourceVertex);
			open.remove(sourceVertex);
			//closed.add(sourceVertex);
			while(!open.isEmpty()) {
				int minWt = Integer.MAX_VALUE;
				Town minAdjVertex = null;
				for (Town vertex : closed) {
					for (Town adjVertex : getAdjVerticesInSet(vertex, open)) {
						int wt = getWtToSource(adjVertex, vertex, sourceVertex);
						if (wt < minWt) {
							minWt = wt;
							minAdjVertex = adjVertex;
							adjVertex.setBackPath(vertex);
						}
					}
				}
				if (minAdjVertex != null) {
					minAdjVertex.setWt(minWt);
					open.remove(minAdjVertex);
					closed.add(minAdjVertex);
				}
			}
			
		}

		/**
		 * This method will evaluate the weight from a vertex to the source vertex
		 * @param adjVertex vertex that is adjacent to "vertex"
		 * @param vertex 
		 * @param sourceVertex the beginning vertex
		 * @return the weight of the path
		 */
		public int getWtToSource(Town adjVertex, Town vertex, Town sourceVertex) {
			int res =0;
			if(containsEdge(adjVertex, vertex)) {
				Road r = getEdge(adjVertex, vertex);
				res+= r.getWeight();}
			Town prev = vertex.backPath;
			while(prev!= null) {
				if(containsEdge(vertex, prev)) {
					Road g = getEdge(vertex, prev);
					res += g.getWeight();
					}
				vertex = prev;
				prev = prev.backPath;
			}
			return res;
		}
		

		/**
		 * This  method will return all the adj vertices to a vertex that are in the set
		 * of vertices
		 * @param vertex the vertex to get the adj vertices from
		 * @param open2 the set containing all the vertices
		 * @return
		 */
		public Set<Town> getAdjVerticesInSet(Town vertex, Set<Town> open2) {
			List<Town> adjL = vertex.adj;
			Set<Town> adjS = new HashSet<Town>();
			for(Town d: adjL) {
				if(open2.contains(d)) {
					adjS.add(d);
				}
			}
			return adjS;
		}
}
