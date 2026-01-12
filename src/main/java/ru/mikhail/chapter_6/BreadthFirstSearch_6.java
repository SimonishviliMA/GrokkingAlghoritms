package ru.mikhail.chapter_6;

import java.util.*;

public class BreadthFirstSearch_6 {

    public static void main(String[] args) {
        Map<String, String[]> graph = new HashMap<>();
        graph.put("A", new String[]{"B","C","D"});
        graph.put("B", new String[]{"E"});
        graph.put("C", new String[]{"F"});
        graph.put("D", new String[]{"G"});
        graph.put("E", new String[]{"K", "L"});
        graph.put("F", new String[]{});
        graph.put("G", new String[]{"A"});
        graph.put("K", new String[]{"M"});
        graph.put("L", new String[]{});
        graph.put("M", new String[]{});

        System.out.println(bfs(graph, "A", "B"));
    }

    private static boolean bfs(Map<String, String[]> graph, String head, String target) {
        Set<String> checked = new HashSet<>();
        Queue<String> vertexQueue = new LinkedList<>();
        vertexQueue.add(head);
        checked.add(head);
        while (!vertexQueue.isEmpty()) {
            String vertex = vertexQueue.poll();
            if (vertex.equals(target)) return true;
            for (String e : graph.get(vertex)) {
                if (!checked.contains(e)) {
                    vertexQueue.add(e);
                    checked.add(e);
                }
            }
        }
        return false;
    }


}
