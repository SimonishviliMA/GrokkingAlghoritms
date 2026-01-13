package ru.mikhail.chapter_6;

import java.util.*;

public class DijkstraAlgorithm_6 {

    public static void main(String[] args) {

        Map<String, List<Vertex>> graph = new HashMap<>();
        graph.put("A", List.of(new Vertex("B", 5), new Vertex("C", 10)));
        graph.put("B", List.of(new Vertex("D", 7), new Vertex("E", 1)));
        graph.put("C", List.of(new Vertex("D", 2), new Vertex("E", 8)));
        graph.put("D", List.of(new Vertex("F", 2)));
        graph.put("E", List.of(new Vertex("F", 1)));
        graph.put("F", List.of());

        String target = "F";
        String head = "A";
        System.out.println(
                dijkstraAlgorithmSearch(graph, head, target)
        );
    }

    private static Path dijkstraAlgorithmSearch(Map<String, List<Vertex>> graph, String headName, String targetName) {
        Map<String, Vertex> paths = new HashMap<>();
        Queue<String> parents = new ArrayDeque<>(List.of(headName));
        while (!parents.isEmpty()) {
            String parentName = parents.poll();
            List<Vertex> parent = graph.get(parentName);
            for (Vertex child : parent) {
                int sum = child.cost() + (paths.containsKey(parentName) ? paths.get(parentName).cost() : 0);
                String childName = child.name();
                if (paths.containsKey(childName)) {
                    Vertex path = paths.get(childName);
                    if (path.cost() > sum) {
                        paths.put(childName, new Vertex(parentName, sum));
                    }
                } else {
                    paths.put(childName, new Vertex(parentName, sum));
                }
                parents.add(childName);
            }
        }

        Vertex vertex = paths.get(targetName);
        if (vertex == null) throw new RuntimeException("Path to target doesn't exist");
        int totalCost = vertex.cost();

        Stack<String> pathToTarget = new Stack<>();
        pathToTarget.add(targetName);
        while (vertex != null && !vertex.name().equals(headName)) {
            pathToTarget.add(vertex.name());
            vertex = paths.get(vertex.name());
        }
        pathToTarget.add(headName);
        return new Path(pathToTarget, totalCost);
    }

    private record Vertex(String name, int cost) {}

    private record Path(Stack<String> pathToTarget, int totalCost) {

        @Override
        public String toString() {
            StringBuilder path = new StringBuilder();
            for (int i = pathToTarget.size() - 1; i >= 0; i--) {
                path.append(pathToTarget.pop());
                if (i != 0) {
                    path.append(" -> ");
                }
            }
            return "total cost: " + totalCost + "; path: " + path;
        }
    }
}
