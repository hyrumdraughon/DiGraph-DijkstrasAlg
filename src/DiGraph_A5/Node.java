package DiGraph_A5;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Node implements Comparable<Node> {
	public long id;
	public String label;
	public List<Edge> edges;
	public HashMap<String, Edge> inEdges; //key= source node
	public HashMap<String, Edge> outEdges; //key= destination node
	//for dijkstra's
	public long dist;
	public boolean known;

	public Node (long idNum, String label) {
		this.id = idNum;
		this.label = label;
		inEdges = new HashMap<>();
		outEdges = new HashMap<>();
		edges = new LinkedList<>();
	}

	@Override
	public int compareTo(Node o) {
		if (this.dist == o.dist) {
			return 0;
		} else if (this.dist < o.dist) {
			return -1;
		} else if (this.dist > o.dist) {
			return 1;
		}
		return 0;
	}
}
