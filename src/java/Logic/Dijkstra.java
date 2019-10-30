/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

/**
 *
 * @author sumit
 */
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class Vertex implements Comparable<Vertex>
{
    public final String name;
    public Edge[] adjacencies;
    public double minDistance = Double.POSITIVE_INFINITY;
    public Vertex previous;
    public Vertex(String argName) { name = argName; }
    public String toString() { return name; }
    public int compareTo(Vertex other)
    {
        return Double.compare(minDistance, other.minDistance);
    }

}


class Edge
{
    public final Vertex target;
    public final double weight;
    public Edge(Vertex argTarget, double argWeight)
    { target = argTarget; weight = argWeight; }
}

public class Dijkstra
{
    public static void computePaths(Vertex source)
    {
        source.minDistance = 0.;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
    vertexQueue.add(source);

    while (!vertexQueue.isEmpty()) {
        Vertex u = vertexQueue.poll();

            // Visit each edge exiting u
            for (Edge e : u.adjacencies)
            {
                Vertex v = e.target;
                double weight = e.weight;
                double distanceThroughU = u.minDistance + weight;
        if (distanceThroughU < v.minDistance) {
            vertexQueue.remove(v);

            v.minDistance = distanceThroughU ;
            v.previous = u;
            vertexQueue.add(v);
        }
            }
        }
    }

    public static List<Vertex> getShortestPathTo(Vertex target)
    {
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
            path.add(vertex);

        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args)
    {
        // mark all the vertices 
        Vertex B1S1 = new Vertex("B1S1");
        Vertex B1S2 = new Vertex("B1S2");
        Vertex B1S3 = new Vertex("B1S3");
        Vertex B1S4 = new Vertex("B1S4");
        Vertex B1S5 = new Vertex("B1S5");
        Vertex B2S1 = new Vertex("B2S1");
        Vertex B2S2 = new Vertex("B2S2");
        Vertex B2S3 = new Vertex("B2S3");
        Vertex B2S4 = new Vertex("B2S4");
        Vertex B2S5 = new Vertex("B2S5");
        Vertex B3S1 = new Vertex("B3S1");
        Vertex B3S2 = new Vertex("B3S2");
        Vertex B3S3 = new Vertex("B3S3");
        Vertex B3S4 = new Vertex("B3S4");
        Vertex B3S5 = new Vertex("B3S5");
        Vertex B4S1 = new Vertex("B4S1");
        Vertex B4S2 = new Vertex("B4S2");
        Vertex B4S3 = new Vertex("B4S3");
        Vertex B4S4 = new Vertex("B4S4");
        Vertex B4S5 = new Vertex("B4S5");

        // set the edges and weight
        B1S1.adjacencies = new Edge[]{ new Edge(B1S2, 1) };
        B1S2.adjacencies = new Edge[]{ new Edge(B1S3, 1) };
        B1S3.adjacencies = new Edge[]{ new Edge(B1S4, 1) };
        B1S4.adjacencies = new Edge[]{ new Edge(B1S5, 1) };
        B1S5.adjacencies = new Edge[]{ new Edge(B4S1, 4) };
        //B1S1.adjacencies = new Edge[]{ new Edge(B2S2, 4) };
        B2S1.adjacencies = new Edge[]{ new Edge(B2S2, 1) };
        B2S2.adjacencies = new Edge[]{ new Edge(B2S3, 1) };
        B2S3.adjacencies = new Edge[]{ new Edge(B2S4, 1) };
        B2S4.adjacencies = new Edge[]{ new Edge(B2S5, 1) };
        B2S5.adjacencies = new Edge[]{ new Edge(B3S1, 4) };
        
        B3S1.adjacencies = new Edge[]{ new Edge(B3S2, 1) };
        B3S2.adjacencies = new Edge[]{ new Edge(B3S3, 1) };
        B3S3.adjacencies = new Edge[]{ new Edge(B3S4, 1) };
        B3S4.adjacencies = new Edge[]{ new Edge(B3S5, 1) };
       
        B4S1.adjacencies = new Edge[]{ new Edge(B4S2, 1) };
        B4S2.adjacencies = new Edge[]{ new Edge(B4S3, 1) };
        B4S3.adjacencies = new Edge[]{ new Edge(B4S4, 1) };
        B4S4.adjacencies = new Edge[]{ new Edge(B4S5, 1) };
       
        B1S1.adjacencies = new Edge[]{ new Edge(B2S1, 4) };
        B1S5.adjacencies = new Edge[]{ new Edge(B2S5, 4) };
        B4S1.adjacencies = new Edge[]{ new Edge(B3S1, 4) };
        B4S5.adjacencies = new Edge[]{ new Edge(B3S5, 4) };

        computePaths(B1S3); // run Dijkstra
        System.out.println("Distance to " + B3S4 + ": " + B3S4.minDistance);
        List<Vertex> path = getShortestPathTo(B3S4);
        System.out.println("Path: " + path);
    }
}