import java.util.*;

public class Graph {
    private Map<Integer, Vertex> vertices;
    private Map<Integer, List<Integer>> adjList;
    private boolean isUndirected;

    public Graph(boolean isUndirected){
        this.vertices = new HashMap<>();
        this.adjList = new HashMap<>();
        this.isUndirected = isUndirected;
    }

    public void addVertex(Vertex v){
        vertices.put(v.getId(), v);
        adjList.putIfAbsent(v.getId(), new LinkedList<>());
    }

    public void addEdge(int from, int to){
        if (!vertices.containsKey(from) || !vertices.containsKey(to)){
            System.out.println("Both vertices must exist to add an edge.");
            return;
        }

        adjList.get(from).add(to);

        if (isUndirected){
            adjList.get(to).add(from);
        }
    }

    public void printGraph(){
        for (int vertexId: adjList.keySet()){
            System.out.println("Vertex " + vertexId + " is connected to: ");
            for (int neighborId : adjList.get(vertexId)){
                System.out.println(neighborId + " ");
            }
            System.out.println();
        }
    }

    public void bfs(int start){
        if (!vertices.containsKey(start)) return;

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue= new LinkedList<>();

        visited.add(start);
        queue.add(start);

        System.out.println("BFS Traversal: ");
        while (!queue.isEmpty()){
            int current = queue.poll();
            System.out.println(current + " ");

            for (int neighbor : adjList.get(current)){
                if (!visited.contains(neighbor)){
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    public void dfs(int start){
        if (!vertices.containsKey(start)) return;

        Set<Integer> visited = new HashSet<>();
        System.out.println("DFS Traversal: ");
        dfsHelper(start, visited);
        System.out.println();
    }

    private void dfsHelper(int current, Set<Integer> visited){
        visited.add(current);
        System.out.println(current + " ");

        for (int neighbor: adjList.get(current)){
            if (!visited.contains(neighbor)){
                dfsHelper(neighbor, visited);
            }
        }
    }
    public int getSize(){
        return vertices.size();
    }
}
