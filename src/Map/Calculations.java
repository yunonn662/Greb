package Map;

//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.PriorityQueue;


public class Calculations {
//    public static void computePaths(Vertex source)
//    {
//        source.minDistance = 0.;
//        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<>();
//      	vertexQueue.add(source);
//
//	while (!vertexQueue.isEmpty()) {
//	    Vertex u = vertexQueue.poll();
//
//            // Visit each edge exiting u
//            for (Edge e : u.adjacencies)
//            {
//                Vertex v = e.target;
//                double weight = e.weight;
//                double distanceThroughU = u.minDistance + weight;
//		if (distanceThroughU < v.minDistance) {
//		    vertexQueue.remove(v);
//		    v.minDistance = distanceThroughU ;
//		    v.previous = u;
//		    vertexQueue.add(v);
//		}
//            }
//        }
//    }

//    public static List<Vertex> getShortestPathTo(Vertex target) 
//    {
//        List<Vertex> path = new ArrayList<>();
//        for (Vertex vertex = target; vertex != null; vertex = vertex.nextVertex)
//            path.add(vertex);
//        Collections.reverse(path);
//        return path;
//    }

    
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) { //Calculate distance between 2 points on a sphere, in this case Earth.
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);

        double earthRadius = 6371.01; // in kilometers
        return earthRadius * Math.acos(Math.sin(lat1)*Math.sin(lat2) + Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon1 - lon2)); //return distance in KM.
    }
    
    public static double calculateDuration(double speed, double distance){ //Calculate duration using distance and speed
        double duration;
        
        duration = distance/speed; //Duration in hours
        duration = duration*60;    //Duration in minutes
        
        
        return duration;
    }
    
}//End of Calculations Class
