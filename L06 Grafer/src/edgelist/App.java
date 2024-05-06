package edgelist;

import java.util.List;

public class App {
    public static void main(String[] args) {
        AdjacencyEdgeListGraph<Integer> elg = new AdjacencyEdgeListGraph<>();

        elg.addVertex(6);
        elg.addVertex(15);
        elg.addVertex(38);
        elg.addVertex(66);
        elg.addVertex(123);

        elg.addEdge(0,1,23);
        elg.addEdge(0,3,8);
        elg.addEdge(0,4,7);

        elg.addEdge(1,2,10);
        elg.addEdge(1,3,90);

        elg.addEdge(2,3,2);
        elg.addEdge(2,4,55);

        elg.addEdge(3,4,76);

        System.out.println(elg.getEdges().toString());

        System.out.println(biggest(elg));

        System.out.println(elg.remove(0,1));

        System.out.println(elg.getEdges().toString());

        System.out.println(elg.remove(6));
        System.out.println(elg.getEdges().toString());
    }

    public static int biggest(AdjacencyEdgeListGraph<Integer> adjacencyEdgeListGraph){
        List<Integer> knuder = adjacencyEdgeListGraph.getVertices();
        int max = knuder.get(0);

        for (int i = 1; i < knuder.size(); i++){
            int current = knuder.get(i);
            if (current > max){
                max = current;
            }
        }

        return max;
    }
}
