public class Experiment {

    public void runTraversals(Graph g){
        if (g.getSize() == 0) return;

        int startVertex = 0;

        long startBfs = System.nanoTime();
        g.bfs(startVertex);
        long endBfs = System.nanoTime();

        long startDfs = System.nanoTime();
        g.dfs(startVertex);
        long endDfs = System.nanoTime();

        printResults("BFS", g.getSize(), endBfs - startBfs);
        printResults("DFS", g.getSize(), endDfs - startDfs);
    }

    public void runMultipleTests(){
        int[] sizes = {10, 30, 100};

        for (int size : sizes){
            System.out.println("---Testing Graph with " + size + " Vertices---");
            Graph g = new Graph(true);

            for (int i=0; i<size; i++){
                g.addVertex(new Vertex(i));
            }

            for (int i=0; i<size-1; i++){
                g.addEdge(i, i+1);
            }

            g.addEdge(0, size/2);
            g.addEdge(size/4, size-1);

            if (size==10){
                g.printGraph();
            }

            runTraversals(g);
        }
    }

    public void printResults(String algorithm, int size, long timeNano){
        System.out.println(algorithm + " Execution Time (" + size + " vertices): " + timeNano + " ns");
    }

    public void runBonusTasks() {
        System.out.println("\n========================================");
        System.out.println("          RUNNING BONUS TASKS           ");
        System.out.println("========================================");


        Graph dag = new Graph(false);
        for (int i = 0; i < 6; i++) dag.addVertex(new Vertex(i));
        dag.addEdge(5, 2);
        dag.addEdge(5, 0);
        dag.addEdge(4, 0);
        dag.addEdge(4, 1);
        dag.addEdge(2, 3);
        dag.addEdge(3, 1);

        System.out.println("Bonus Task 1: Topological Sort Result:");
        System.out.println(dag.topologicalSort());


        Graph unweightedGraph = new Graph(true);
        for (int i = 0; i < 6; i++) unweightedGraph.addVertex(new Vertex(i));
        unweightedGraph.addEdge(0, 1);
        unweightedGraph.addEdge(0, 2);
        unweightedGraph.addEdge(1, 3);
        unweightedGraph.addEdge(2, 4);
        unweightedGraph.addEdge(3, 5);
        unweightedGraph.addEdge(4, 5);

        System.out.println("\nBonus Task 2: Shortest Path via BFS (From 0 to 5):");
        System.out.println(unweightedGraph.findShortestPath(0, 5));
    }


}
