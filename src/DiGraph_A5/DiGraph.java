package DiGraph_A5;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class DiGraph implements DiGraphInterface {

  // in here go all your data and methods for the graph
	private long nNum;
	private long eNum;
	private HashMap<String, Node> hashNlabels;
	private HashMap<Long, Node> hashNids;
	private HashMap<String, Edge> hashElabels;
	private HashMap<Long, Edge> hashEids;
	private List<Node> nodes;
	private int cnt;

  public DiGraph ( ) { // default constructor
    // explicitly include this
    // we need to have the default constructor
    // if you then write others, this one will still be there
	  nNum = 0;
	  eNum = 0;
	  hashNids = new HashMap<>();
	  hashNlabels = new HashMap<>();
	  hashElabels = new HashMap<>();
	  hashEids = new HashMap<>();
	  nodes = new LinkedList<>();
  }

@Override
public boolean addNode(long idNum, String label) {
	if (idNum<0) {return false;}
	//check id and label are unique
	if (hashNids.containsKey(idNum)) {
		return false;	//idNum is duplicate
	}
	if (hashNlabels.containsKey(label)) {
		return false;	//label is duplicate
	}
	Node n = new Node(idNum, label);
	hashNlabels.put(label, n);
	hashNids.put(idNum, n);
	nodes.add(n);
	nNum++;
	return true;
}

@Override
public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
	if (idNum<0) {return false;};
	if (!hashNlabels.containsKey(sLabel) || !hashNlabels.containsKey(dLabel)) {return false;}
	//check if edge already exists
	if (hashEids.containsKey(idNum)) {
		return false; //idNum is duplicate
	}
	if (hashNlabels.get(sLabel).outEdges.containsKey(dLabel)) {
		return false; //already an edge from s to d
	}
	//make edge
	Edge e = new Edge(idNum, eLabel, weight, hashNlabels.get(sLabel), hashNlabels.get(dLabel));
	hashElabels.put(eLabel, e);
	hashEids.put(idNum, e);
	hashNlabels.get(sLabel).outEdges.put(dLabel, e);
	hashNlabels.get(sLabel).edges.add(e);
	hashNlabels.get(dLabel).inEdges.put(sLabel, e);
	eNum++;
	return true;
}

@Override
public boolean addEdge(long idNum, String sLabel, String dLabel) {
	if (idNum<0) {return false;};
	if (!hashNlabels.containsKey(sLabel) || !hashNlabels.containsKey(dLabel)) {return false;}
	//check if edge already exists
	if (hashEids.containsKey(idNum)) {
		return false; //idNum is duplicate
	}
	if (hashNlabels.get(sLabel).outEdges.containsKey(dLabel)) {
		return false; //already an edge from s to d
	}
	//make edge
	long w = 1;
	String eLabel = null;
	Edge e = new Edge(idNum, eLabel, w, hashNlabels.get(sLabel), hashNlabels.get(dLabel));
	hashElabels.put(eLabel, e);
	hashEids.put(idNum, e);
	hashNlabels.get(sLabel).outEdges.put(dLabel, e);
	hashNlabels.get(sLabel).edges.add(e);
	hashNlabels.get(dLabel).inEdges.put(sLabel, e);
	eNum++;
	return true;
}

@Override
public boolean delNode(String label) {
	if (!hashNlabels.containsKey(label)) {
		return false; //node is not in graph
	}
	//delete edges
	Node dNode = hashNlabels.get(label);
	dNode.inEdges.forEach((k, v)->{
		hashNlabels.get(k).edges.remove(v);
		hashNlabels.get(k).outEdges.remove(v.dest.label);
		hashEids.remove(v.id);
		hashElabels.remove(v.label);
		eNum--;
	});
	dNode.outEdges.forEach((k, v)->{
		hashNlabels.get(k).edges.remove(v);
		hashNlabels.get(k).inEdges.remove(v.source.label);
		hashEids.remove(v.id);
		hashElabels.remove(v.label);
		eNum--;
	});
	//delete Node
	hashNids.remove(dNode.id);
	hashNlabels.remove(dNode.label);
	nodes.remove(dNode);
	nNum--;
	return true;
}

@Override
public boolean delEdge(String sLabel, String dLabel) {
	if (!hashNlabels.containsKey(sLabel) || !hashNlabels.containsKey(dLabel)) {return false;}
	if (!hashNlabels.get(sLabel).outEdges.containsKey(dLabel)) {
		return false; //edge does not exist
	}
	Edge e = hashNlabels.get(sLabel).outEdges.get(dLabel);
	long id = e.id;
	String eLabel = e.label;
	hashNlabels.get(sLabel).edges.remove(e);
	hashNlabels.get(sLabel).outEdges.remove(dLabel);
	hashNlabels.get(dLabel).inEdges.remove(sLabel);
	hashEids.remove(id);
	hashElabels.remove(eLabel);
	eNum--;
	return true;
}

@Override
public long numNodes() {
	return nNum;
}

@Override
public long numEdges() {
	return eNum;
}

@Override
public ShortestPathInfo[] shortestPath(String label) {
	hashNlabels.forEach((k, v)->{
		v.dist = Integer.MAX_VALUE;
		v.known = false;
	});
	ShortestPathInfo[] dijkstra = new ShortestPathInfo[(int)nNum];
	cnt = 0;
	PriorityQueue<Node> pq = new PriorityQueue<>();
	Node s = hashNlabels.get(label);
	s.dist = 0;
	pq.add(s);
	while (!pq.isEmpty()) {
		Node n = pq.element();
		long d = pq.element().dist;
		if (cnt < dijkstra.length) {
			dijkstra[cnt] = new ShortestPathInfo(n.label, n.dist);
			cnt++;
		}
		pq.remove();
		if (n.known) {
			//back to loop
		} else {
			n.known = true;
			n.outEdges.forEach((k, v)->{
				Node a = hashNlabels.get(k);
				if (!a.known) {
					if (a.dist > (d+v.weight)) {
						a.dist = (d+v.weight);
						if (pq.contains(a)) {
							pq.remove(a);
						}
						pq.add(a);
					}
				}
			});
		}
	}
	hashNlabels.forEach((k, v)->{
		if (!v.known) {
			dijkstra[cnt] = new ShortestPathInfo(k, -1);
			cnt++;
		}
	});
	return dijkstra;
}
  
  // rest of your code to implement the various operations
}