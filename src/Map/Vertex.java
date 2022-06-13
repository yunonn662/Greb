/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Map;


public class Vertex <T extends Comparable<T>, N extends Comparable<N>> {
    T vertexInfo; // Store customers, drivers and also destination.
    public Vertex<T,N> nextVertex;
    Edge<T,N> firstEdge;

    public Vertex() {
        vertexInfo=null;
        nextVertex = null;
        firstEdge = null;
    }
    
    public Vertex(T vInfo, Vertex<T,N> next) {
        vertexInfo = vInfo;
        nextVertex = next;
        firstEdge = null;
   }

   
}//End of Vertex Class
