# Assignment 4:  Graph Traversal and Representation System
**Student:** Alima Beisembayeva
**Group:** IT-2504
## A. Project Overview
This project implements a graph representation system using an adjacency list and explores two fundamental graph traversal algorithms: Breadth-First Search (BFS) and Depth-First Search (DFS).

* **Graph Structure:** A graph is a mathematical structure defined as an ordered pair (V, E), where V represents the set of vertices (nodes) and E represents the set of edges.
* **Vertices and Edges:** Vertices are the individual entities or nodes in the graph. Edges form the connections between these vertices. In this project, the graph can handle undirected edges, meaning a connection from vertex A to B also implies a connection from B to A.
* **BFS and DFS:** BFS explores the graph level-by-level, visiting all immediate neighbors of a vertex before moving deeper. DFS, on the other hand, explores as far down a single path as possible before backtracking.

## B. Class Descriptions
The system is built using an object-oriented approach with the following core classes:

* **`Vertex`:** Represents a single node in the graph. It contains a unique integer `id` to identify it.
* **`Edge`:** Represents a connection between two vertices, containing references to the `source` vertex and the `destination` vertex.
* **`Graph`:** The main data structure managing the vertices and edges. It utilizes an **Adjacency List** representation. Instead of an NxN matrix, it uses a `HashMap` mapping each `Vertex` ID to a `LinkedList` of adjacent neighbor IDs. This is highly efficient for sparse graphs.
* **`Experiment` & `Main`:** These classes handle the creation of different sized graphs (10, 30, and 100 vertices), execute the traversals, and measure the processing time using `System.nanoTime()`.

## C. Algorithm Descriptions

### 1. Breadth-First Search (BFS)
* **Step-by-step explanation:** BFS starts at a chosen root node. It uses a `Queue` (FIFO) to keep track of nodes to visit. It marks the start node as visited, enqueues it, and then enters a loop. In each iteration, it dequeues a node, prints it, and enqueues all of its unvisited neighbors.
* **Use cases:** Finding the shortest path in unweighted graphs (like finding the fewest number of connections between two users in a social network), web crawling, and peer-to-peer network routing.
* **Time complexity:** O(V + E), where V is the number of vertices and E is the number of edges, because every vertex and every edge is explored exactly once in the worst case.

### 2. Depth-First Search (DFS)
* **Step-by-step explanation:** DFS starts at the root and explores as far along each branch as possible before backtracking. In this implementation, it uses recursion (which inherently utilizes the system Call Stack). It marks the current node as visited, prints it, and then recursively calls the DFS helper function on each unvisited neighbor.
* **Use cases:** Topological sorting (scheduling jobs), cycle detection in graphs, and solving puzzles like mazes or Sudoku where a complete path needs to be explored.
* **Time complexity:** O(V + E), similar to BFS, as it also visits every node and edge once.

## D. Experimental Results & Analysis

### Execution Time Comparison Table
*(Measured using `System.nanoTime()`)*

| Graph Size | BFS Execution Time (ns) | DFS Execution Time (ns) |
| :--- | :--- | :--- |
| **10 vertices** | 277,200 | 61,200 |
| **30 vertices** | 176,100 | 87,800 |
| **100 vertices** | 301,300 | 205,600 |

### Analysis Questions
* **How does graph size affect BFS and DFS performance?** Overall, as the number of vertices increases from 30 to 100, the execution time for both algorithms increases, matching the expected behavior. Interestingly, the 10-vertex BFS test took longer than the 30-vertex test. This is a classic example of "JVM Warmup" in Java—the very first time a queue and collections are instantiated, the Java Virtual Machine takes longer to load the classes and allocate memory. Once warmed up, the times scale linearly.
* **Which traversal is faster in your experiments?** In every single test case, DFS was significantly faster than BFS. For example, at 100 vertices, DFS took 205,600 ns compared to BFS's 301,300 ns.
* **Do results match the expected complexity O(V + E)?** Yes. Excluding the initial JVM warmup anomaly for the first 10-vertex run, the times increase roughly proportionally as the graph grows to 30 and then 100 vertices, demonstrating the linear O(V + E) time complexity. DFS's speed advantage over BFS comes down to the constant factors hidden by Big-O notation: DFS uses highly optimized hardware-level call stack memory (recursion), while BFS requires the overhead of creating and updating a `Queue` object in heap memory.
* **How does graph structure affect traversal order?** A dense graph with many interconnections causes BFS to queue many neighbors immediately, resulting in a wide search tree. A sparse, chain-like graph causes DFS to dive extremely deep very quickly without branching.
* **When is BFS preferred over DFS?** BFS is preferred when you need to find the shortest path between two nodes in an unweighted graph, or when the target node is known to be relatively close to the starting root.
* **What are the limitations of DFS?** DFS is not guaranteed to find the shortest path. Additionally, on massive or infinite graphs, DFS can get trapped venturing down an infinitely long path and never return, leading to `StackOverflow` errors due to deep recursion limits.

## E. Screenshots

1. **Graph structure output (10 Vertices):** ![Снимок экрана (201).png](screens/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%20%28201%29.png)

2. **BFS traversal output:** ![Снимок экрана (202).png](screens/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%20%28202%29.png)![BFS Output](./docs/screenshots/Снимок_экрана_(202).png)

3. **DFS traversal output:** ![Снимок экрана (203).png](screens/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%20%28203%29.png)![DFS Output](./docs/screenshots/Снимок_экрана_(203).png)

4. **Performance results (100 Vertices):** ![Снимок экрана (208).png](screens/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%20%28208%29.png)![Performance Results](./docs/screenshots/Снимок_экрана_(208).png)

*(Note: Ensure your screenshot files are named correctly and placed in the `docs/screenshots/` folder so these links render properly on GitHub).*

## F. Reflection Section
Implementing this graph traversal system deepened my understanding of how interconnected data is represented in computer science. Moving from the theoretical concepts of vertices and edges to writing a functional Adjacency List using HashMaps and Linked Lists highlighted the importance of choosing the right data structure for efficiency.

The primary difference I observed between BFS and DFS is structural and performance-based: BFS requires maintaining an explicit Queue, while DFS elegantly utilizes Java's call stack via recursion. As my experimental results showed, DFS was consistently faster due to avoiding the overhead of Queue object management. The main challenge faced during implementation was ensuring that the algorithms accurately kept track of `visited` nodes. Without the `HashSet` to strictly monitor visited states, cyclic graphs would cause both traversals to fall into infinite loops. Overall, this assignment solidified the relationship between theoretical Big-O complexity and actual runtime behavior.