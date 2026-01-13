package ru.mikhail.chapter_6;

import java.util.*;

public class DijkstraAlgorithm_6 {

    public static void main(String[] args) {

        Map<String, List<Vertex>> graph = new HashMap<>();
//        Map<String,Vertex> paths = dijkstraAlgorithmSearch(graph, head, target);

//        System.out.println(paths);
    }

    private static Map<String, Vertex> dijkstraAlgorithmSearch(Map<String, List<Vertex>> graph, Vertex head, String target) {
        Map<String, Vertex> paths = new HashMap<>();
        Queue<String> parents = new ArrayDeque<>(List.of(head.name()));
        while (!parents.isEmpty()) {
            String parentName = parents.poll();
            for (Vertex child : graph.get(parentName)) {
                int sum = child.cost() + head.cost();
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
        return paths;
    }

    private record Vertex(String name, int cost) {}
}
