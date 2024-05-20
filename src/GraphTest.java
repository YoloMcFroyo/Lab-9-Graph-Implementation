import static org.junit.Assert.*;
import org.junit.Test;

public class GraphTest {

  @Test
  public void testGraph() {
    Graph graph = new Graph(20);
    assertEquals(20, graph.getNumVertices());
  }

  @Test
  public void testAddEdge() {
    Graph graph = new Graph(20);
    graph.addEdge(0, 1, 1);
    int[] neighbors = graph.getNeighbors(0);
    assertEquals(1, neighbors.length);
    assertEquals(1, neighbors[0]);
  }

  @Test
  public void testRemoveEdge() {
    Graph graph = new Graph(20);
    graph.addEdge(0, 1, 1);
    graph.removeEdge(0, 1);
    int[] neighbors = graph.getNeighbors(0);
    assertEquals(0, neighbors.length);
  }

  @Test
  public void testGetNeighbors() {
    Graph graph = new Graph(20);
    graph.addEdge(0, 1, 1);
    graph.addEdge(0, 4, 1);
    int[] neighbors = graph.getNeighbors(0);
    assertEquals(2, neighbors.length);
    assertEquals(1, neighbors[0]);
    assertEquals(4, neighbors[1]);
  }


 

}