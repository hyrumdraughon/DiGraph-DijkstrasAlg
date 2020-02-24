package DiGraph_A5;

public class Edge {
	public long id;
	public String label;
	public long weight;
	public Node source;
	public Node dest;
	
	public Edge (long idNum, String label, long w, Node s, Node d) {
		this.id = idNum;
		this.label = label;
		this.weight = w;
		this.source = s;
		this.dest = d;
	}
}
