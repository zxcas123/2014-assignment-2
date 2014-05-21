package ps3.graph;


import java.util.*;


public abstract class Graph<N> {
	
	
	
	public abstract void addNode(N nodeName);
	
	public abstract void addEdge(N parent, N child);
	
	public abstract void listNodes();
	
	public abstract void listChildren(N parent);
	
}
