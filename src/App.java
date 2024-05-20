public class App {
    public static void main(String[] args) throws Exception {
        // Create a graph with 5 vertices
        Graph graph = new Graph(20);

        // Add edges
        graph.addEdge(0, 1, 1); 
        graph.addEdge(0, 4, 1);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 4, 1);
        graph.addEdge(1, 3, 1);
        graph.addEdge(4, 3, 1);
        graph.addEdge(3, 2, 1);


        System.out.print("Breadth Traversal from 2: "); 
       graph.breadthTraversal(2);
       System.out.print("Depth Traversal from 0: ");
       graph.depthTraversal(0);

       graph.removeEdge(0, 1);
       System.out.print("Depth Traversal from 1: ");
       graph.depthTraversal(1);
    }
    }
