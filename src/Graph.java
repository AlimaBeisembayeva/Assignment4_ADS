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

    public List<Integer> topologicalSort(){
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();

        for (int vertexId : vertices.keySet()){
            if (!visited.contains(vertexId)){
                topologicalSortHelper(vertexId, visited, stack);
            }
        }
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    private void topologicalSortHelper(int current, Set<Integer> visited, Stack<Integer> stack) {
        visited.add(current);
        for (int neighbor : adjList.get(current)) {
            if (!visited.contains(neighbor)) {
                topologicalSortHelper(neighbor, visited, stack);
            }
        }
        stack.push(current);
    }


    public List<Integer> findShortestPath(int start, int end) {
        if (!vertices.containsKey(start) || !vertices.containsKey(end)) {
            return Collections.emptyList();
        }

        Map<Integer, Integer> parentMap = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);
        parentMap.put(start, null);

        boolean pathFound = false;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (current == end) {
                pathFound = true;
                break;
            }
            for (int neighbor : adjList.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        if (!pathFound) return Collections.emptyList();

        List<Integer> path = new LinkedList<>();
        Integer current = end;
        while (current != null) {
            path.add(0, current);
            current = parentMap.get(current);
        }
        return path;
    }

}
