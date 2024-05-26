import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.HashMap;

/**
 * The Graph class represents a graph data structure.
 * It stores the adjacency matrix and the number of vertices in the graph.
 */
public class Graph {
  private int[][] adjMatrix;
  private int numVertices;

  
  public Graph(int numVertices){
    adjMatrix = new int[numVertices][numVertices];
    this.numVertices = numVertices;
  }

  public int getNumVertices(){
    return numVertices;
  }

  public void addEdge(int x, int y, int weight){//self loops, 
    if (x >= numVertices || y >= numVertices || x < 0 || y < 0) {
      throw new IllegalArgumentException("Vertex out of bounds");
    }

    if (weight <= 0){
      throw new IllegalArgumentException("weight must be a positive number");
    }

    if (x == y) {
      throw new IllegalArgumentException("Self loops are not allowed");
    }
    adjMatrix[x][y] = weight;
    adjMatrix[y][x] = weight;
  }
 

  public void removeEdge(int x, int y){
    if (x >= numVertices || y >= numVertices || x < 0 || y < 0){
      throw new IllegalArgumentException("Vertex out of bounds");
    }
    adjMatrix[x][y] = 0;
    adjMatrix[y][x] = 0;
  }


  public int[] getNeighbors(int x){
    if (x >= numVertices  || x < 0){
      throw new IllegalArgumentException("Vertex out of bounds");
    }
    
    //collect neighbors in arraylist
    List<Integer> neighbors = new ArrayList<>();
    for (int i = 0;i < numVertices; i++){
      if(adjMatrix[x][i] != 0){
        neighbors.add(i);
      }
    }

    //transfer from arraylist to array
    int [] neighborsArray = new int[neighbors.size()];
    for(int i = 0; i < neighbors.size();i++){
      neighborsArray[i] = neighbors.get(i);
    }

    return neighborsArray;
  }
  

  public void breadthTraversal(int start){
    if(start >= numVertices || start < 0 ){
      throw new IllegalArgumentException("Vertex out of bounds");
    }
    
    HashMap<Integer, Boolean> visited = new HashMap<>();
    Queue<Integer> queue = new LinkedList<Integer> ();

    queue.add(start);
    visited.put(start, true);

    while( !queue.isEmpty()){
      System.out.print(queue.peek() + " "); //print vertice

      for(int i : getNeighbors(queue.poll())){ //add adjacent vertices to queue
        if(!visited.containsKey(i)){
           queue.add(i); //if neighbor hasn't been visited, then add to the queue
           visited.put(i, true);
        }
      }
      
    }

    System.out.println();
  }


  public void depthTraversal(int start){
    if (start >= numVertices || start < 0) {
      throw new IllegalArgumentException("Vertex out of bounds");
    }
    
    HashMap<Integer, Boolean> visited = new HashMap<>();
    deepyTravvy(start, visited);
    System.out.println();
  }

  private void deepyTravvy(int vertex, HashMap<Integer, Boolean> visited){
    visited.put(vertex, true);
    System.out.print(vertex + " ");

    for(int i : getNeighbors(vertex)){
      if(!visited.containsKey(i)){ 
        deepyTravvy(i, visited);
      }
    }
  }

  public void dijkstra(int start, int destination){
    if (start >= numVertices || start < 0 || destination >= numVertices || destination < 0) {
      throw new IllegalArgumentException("Vertex out of bounds");
    }

    if (start == destination){
      System.out.println("Shortest path is 0");
      return;
    }
    
    HashMap<Integer, Boolean> visited = new HashMap<>();           //mark vertices as visited 
    HashMap<Integer, Integer> shortestDistance = new HashMap<>();  //shortest distance for each vertices
    HashMap<Integer, Integer> previous = new HashMap<>();          //previous vertex in the shortest path
    PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(); //queue to store vertices to visit

    for(int i =0; i< numVertices; i++){ //set all shortest paths to infinity
      shortestDistance.put(i, Integer.MAX_VALUE); 
    }

    shortestDistance.put(start, 0);
    minHeap.add(start);

    while(!minHeap.isEmpty()){
      int current = minHeap.poll();
      visited.put(current, true);

      for(int i : getNeighbors(current)){
        if(!visited.containsKey(i)){ 
          int newDistance = shortestDistance.get(current) + adjMatrix[current][i];
          if (newDistance < shortestDistance.get(i)) {
            shortestDistance.put(i, newDistance);
            previous.put(i, current);
          }
          minHeap.add(i);
        }
      }
    }

    int current = destination;
    System.out.print("Shortest Path: ");
    while(current != start){
      System.out.print(current + " ");
      current = previous.get(current);
    }
    System.out.println(start);
    System.out.println("Distance: " + shortestDistance.get(destination));
  }



  

}
