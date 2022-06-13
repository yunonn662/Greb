/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Map;


public class Edge <T extends Comparable<T>, N extends Comparable <N>> {
    Vertex<T,N> toVertex;
    N weight; //weight here will be filled with duration needed.
    Edge<T,N> nextEdge;
	
    public Edge()	{
	toVertex = null;
	weight = null;
	nextEdge = null;
    }
	
    public Edge(Vertex<T,N> destination, N w, Edge<T,N> a)	{
	toVertex = destination;
	weight = w;
	nextEdge = a;
    }
    
    
}//End of Edge Class
