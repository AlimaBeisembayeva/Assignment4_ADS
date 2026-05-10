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
}
