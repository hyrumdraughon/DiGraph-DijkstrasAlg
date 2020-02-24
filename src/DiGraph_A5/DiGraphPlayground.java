package DiGraph_A5;

public class DiGraphPlayground {

  public static void main (String[] args) {
  
      // thorough testing is your responsibility
      //
      // you may wish to create methods like 
      //    -- print
      //    -- sort
      //    -- random fill
      //    -- etc.
      // in order to convince yourself your code is producing
      // the correct behavior
      //exTest();
      dTestReflexive();
    }
  
    public static void dTestReflexive() {
    	DiGraph g = new DiGraph();
    	g.addNode(0, "a");
    	g.addNode(1, "b");
    	g.addNode(2, "c");
    	g.addNode(3, "d");
    	g.addNode(4, "e");
    	//
    	g.addEdge(20, "a", "a", 1, null);
    	g.addEdge(5, "a", "b", 1, null);
    	g.addEdge(7, "a", "c", 3, null);
    	g.addEdge(8, "a", "d", 5, null);
    	g.addEdge(10, "a", "e", 9, null);
    	g.addEdge(11, "b", "c", 1, null);
    	g.addEdge(12, "b", "d", 2, null);
    	g.addEdge(13, "b", "e", 7, null);
    	g.addEdge(14, "c", "d", 2, null);
    	g.addEdge(15, "c", "e", 5, null);
    	g.addEdge(16, "d", "e", 3, null);
    	ShortestPathInfo[] a = g.shortestPath("a");
    	//System.out.println(a[0]);
    	for (int i=0; i<a.length; i++) {
    		System.out.println(a[i].toString());
    	}
    	ShortestPathInfo[] b = g.shortestPath("b");
    	for (int i=0; i<b.length; i++) {
    		System.out.println(b[i].toString());
    	}
    }
  
    public static void dTest5() {
    	DiGraph g = new DiGraph();
    	g.addNode(0, "a");
    	g.addNode(1, "b");
    	g.addNode(2, "c");
    	g.addNode(3, "d");
    	g.addNode(4, "e");
    	//
    	g.addEdge(5, "a", "b", 1, null);
    	g.addEdge(7, "a", "c", 3, null);
    	g.addEdge(8, "a", "d", 5, null);
    	g.addEdge(10, "a", "e", 9, null);
    	g.addEdge(11, "b", "c", 1, null);
    	g.addEdge(12, "b", "d", 2, null);
    	g.addEdge(13, "b", "e", 7, null);
    	g.addEdge(14, "c", "d", 2, null);
    	g.addEdge(15, "c", "e", 5, null);
    	g.addEdge(16, "d", "e", 3, null);
    	ShortestPathInfo[] a = g.shortestPath("a");
    	//System.out.println(a[0]);
    	for (int i=0; i<a.length; i++) {
    		System.out.println(a[i].toString());
    	}
    	//System.out.println(a[5].toString());
    }
  
    public static void dTest4() {
    	DiGraph g = new DiGraph();
    	g.addNode(0, "0");
    	g.addNode(1, "1");
    	g.addNode(2, "2");
    	g.addNode(3, "3");
    	g.addNode(5, "4");
    	g.addNode(6, "5");
    	g.addNode(9, "6");
    	g.addEdge(4, "0", "5", 3, null);
    	g.addEdge(7, "3", "2", 6, null);
    	g.addEdge(8, "4", "0", 1, null);
    	g.addEdge(10, "4", "5", 2, null);
    	g.addEdge(11, "6", "4", 4, null);
    	ShortestPathInfo[] a = g.shortestPath("0");
    	//System.out.println(a[0]);
    	for (int i=0; i<a.length; i++) {
    		System.out.println(a[i].toString());
    	}
    }
  
    public static void dTest0() {
    	DiGraph g = new DiGraph();
    	g.addNode(0, "a");
    	g.addNode(1, "b");
    	g.addNode(2, "c");
    	g.addEdge(4, "a", "b", 3, null);
    	g.addEdge(5, "a", "c", 5, null);
    	g.addEdge(6, "c", "b", 4, null);
    	ShortestPathInfo[] a = g.shortestPath("a");
    	//System.out.println(a[0]);
    	for (int i=0; i<a.length; i++) {
    		System.out.println(a[i].toString());
    	}
    }
  
    public static void exTest(){
      DiGraph d = new DiGraph();
      d.addNode(1, "f");
      d.addNode(3, "s");
      d.addNode(7, "t");
      d.addNode(0, "fo");
      d.addNode(4, "fi");
      d.addNode(6, "si");
      d.addEdge(0, "f", "s", 0, null);
      d.addEdge(1, "f", "si", 0, null);
      d.addEdge(2, "s", "t", 0, null);
      d.addEdge(3, "fo", "fi", 0, null);
      d.addEdge(4, "fi", "si", 0, null);
      System.out.println("numEdges: "+d.numEdges());
      System.out.println("numNodes: "+d.numNodes());
    }
    
    public static void test() {
    	DiGraph g = new DiGraph();
    	String s = "a";
    	String prev;
    	g.addNode(0, s);
    	prev = s;
    	s = randString(10);
    	for (int i = 1; i<1000000; i++) {
    		g.addNode(i, s);
    		g.addEdge(i, prev, s, 1, null);
    		prev = s;
    		s = randString(10);
    	}
    	System.out.println("numEdges: "+g.numEdges());
        System.out.println("numNodes: "+g.numNodes());
        g.delNode(prev);
        System.out.println("numEdges: "+g.numEdges());
        System.out.println("numNodes: "+g.numNodes());
        ShortestPathInfo[] a = g.shortestPath("a");
    	//System.out.println(a[0]);
    	for (int i=0; i<a.length; i++) {
    		System.out.println(a[i].toString());
    	}
        
    }
    
    public static String randString(int n) {
    	String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    	String s = "";
    	for (int i = 0; i<n; i++) {
    		int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
    		s = s + (ALPHA_NUMERIC_STRING.charAt(character));
    	}
    	return s.toString();
    }
}