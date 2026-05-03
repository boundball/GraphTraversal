
import java.util.*;

public class GraphTraversal {

    private final Map<String, List<String>> adj = new LinkedHashMap<>();

    public void addVertex(String v) {
        adj.putIfAbsent(v, new ArrayList<>());
    }

    public void addEdge(String from, String to) {
        addVertex(from);
        addVertex(to);
        adj.get(from).add(to);
    }

    public List<String> bfs(String start) {
        List<String> order = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        Map<String, List<String>> bfsTree = new LinkedHashMap<>();

        visited.add(start);
        queue.add(start);
        bfsTree.put(start, new ArrayList<>());

        while (!queue.isEmpty()) {
            String current = queue.remove();
            order.add(current);

            for (String neighbor : adj.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);

                    bfsTree.putIfAbsent(current, new ArrayList<>());
                    bfsTree.putIfAbsent(neighbor, new ArrayList<>());
                    bfsTree.get(current).add(neighbor);
                }
            }
        }

        System.out.println("BFS Tree: " + bfsTree);
        return order;
    }

    public List<String> dfs(String start) {
        List<String> order = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();

        Map<String, List<String>> dfsTree = new LinkedHashMap<>();
        Map<String, String> parent = new HashMap<>();

        stack.push(start);
        dfsTree.put(start, new ArrayList<>());

        while (!stack.isEmpty()) {
            String current = stack.pop();

            if (visited.contains(current)) {
                continue;
            }

            visited.add(current);
            order.add(current);

            if (parent.containsKey(current)) {
                String p = parent.get(current);
                dfsTree.putIfAbsent(p, new ArrayList<>());
                dfsTree.putIfAbsent(current, new ArrayList<>());
                dfsTree.get(p).add(current);
            }

            List<String> neighbors = adj.get(current);

            for (int i = neighbors.size() - 1; i >= 0; i--) {
                String neighbor = neighbors.get(i);

                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                    parent.putIfAbsent(neighbor, current);
                }
            }
        }

        System.out.println("DFS Tree: " + dfsTree);
        return order;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        GraphTraversal graph = new GraphTraversal();

        String[] vertices = {"A", "B", "C", "D", "G", "H", "I"};
        for (String v : vertices) {
            graph.addVertex(v);
        }

        graph.addEdge("A", "B");
        graph.addEdge("A", "D");
        graph.addEdge("A", "E");
        graph.addEdge("B", "E");
        graph.addEdge("D", "G");
        graph.addEdge("E", "F");
        graph.addEdge("E", "H");
        graph.addEdge("G", "H");
        graph.addEdge("F", "C");
        graph.addEdge("F", "H");
        graph.addEdge("H", "I");
        graph.addEdge("C", "B");
        graph.addEdge("I", "F");

        System.out.println("BFS Order: " + graph.bfs("A"));
        System.out.println("DFS Order: " + graph.dfs("A"));
    }

}
