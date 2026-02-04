package ru.mikhail.chapter_7;

import java.util.*;

public class DijkstraBFSAlgorithm_6 {

    public static void main(String[] args) {

        Map<String, List<Vertex<String>>> graph = new HashMap<>();
        graph.put("A", List.of(new Vertex<>("C", 6), new Vertex<>("F", 13), new Vertex<>("B", 10), new Vertex<>("E", 18)));
        graph.put("C", List.of(new Vertex<>("F", 1)));
        graph.put("F", List.of(new Vertex<>("B", 1)));
        graph.put("E", List.of(new Vertex<>("D", 12)));
        graph.put("D", List.of(new Vertex<>("C", 8), new Vertex<>("F", -26)));
        graph.put("B", List.of());

        System.out.println(
                dijkstraBFSAlgorithmSearch(graph, "A", "B")
        );
    }

    private static Path<String> dijkstraBFSAlgorithmSearch(Map<String, List<Vertex<String>>> graph, String headName, String targetName) {
        Map<String, Vertex<String>> paths = new HashMap<>();
        Queue<String> parents = new ArrayDeque<>(List.of(headName));
        while (!parents.isEmpty()) {
            String parentName = parents.poll();
            List<Vertex<String>> parent = graph.get(parentName);
            for (Vertex<String> child : parent) {
                int sum = child.cost() + (paths.containsKey(parentName) ? paths.get(parentName).cost() : 0);
                String childName = child.name();
                if (paths.containsKey(childName)) {
                    Vertex<String> path = paths.get(childName);
                    if (path.cost() > sum) {
                        paths.put(childName, new Vertex<>(parentName, sum));
                    }
                } else {
                    paths.put(childName, new Vertex<>(parentName, sum));
                }
                parents.add(childName);
            }
        }

        Vertex<String> vertex = paths.get(targetName);
        if (vertex == null) throw new RuntimeException("Path to target doesn't exist");
        int totalCost = vertex.cost();

        Stack<String> pathToTarget = new Stack<>();
        pathToTarget.add(targetName);
        while (vertex != null && !vertex.name().equals(headName)) {
            pathToTarget.add(vertex.name());
            vertex = paths.get(vertex.name());
        }
        pathToTarget.add(headName);
        return new Path<>(pathToTarget, totalCost);
    }
}
