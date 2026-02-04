package ru.mikhail.chapter_7;

import java.util.*;

public class DijkstraAlgorithm_6 {
    public static void main(String[] args) {
        List<Vertex<Integer>>[] graph = new List[] {
                List.of(new Vertex<>(1, 5), new Vertex<>(2, 7), new Vertex<>(3,1)),
                List.of(new Vertex<>(4, 6)),
                List.of(new Vertex<>(4, 1), new Vertex<>(5, 4), new Vertex<>(6, 3)),
                List.of(new Vertex<>(6, 12)),
                List.of(new Vertex<>(5, 0)),
                List.of(new Vertex<>(6, 1)),
                List.of()
        };

        System.out.println(
                dijkstraAlgorithmSearch(graph, 0,6)
        );

    }


    private static Path<Integer> dijkstraAlgorithmSearch(List<Vertex<Integer>>[] graph, int head, int target) {
        int graphSize = graph.length;
        boolean[] checked = new boolean[graphSize];
        int[] cost = new int[graphSize];
        int[] parents = new int[graphSize];
        Arrays.fill(cost, Integer.MAX_VALUE - 1);
        Arrays.fill(parents, -1);



        int parent = head;
        cost[head] = 0;
        while (parent != target) {
            int headCost = cost[parent];
            for (Vertex<Integer> child : graph[parent]) {
                int sumCost = child.cost() + headCost;
                if (cost[child.name()] > sumCost) {
                    cost[child.name()] = sumCost;
                    parents[child.name()] = parent;
                }
            }
            int min = Integer.MAX_VALUE - 1;
            for (int i = 0; i < graphSize; i++) {
                if (!checked[i] && cost[i] < min) {
                    parent = i;
                    min = cost[i];
                }
            }
            checked[parent] = true;
        }
        int i = target;
        Stack<Integer> pathToTarget = new Stack<>();
        pathToTarget.add(target);
        while (i != head) {
            i = parents[i];
            pathToTarget.add(i);
        }
        return new Path<>(pathToTarget, cost[target]);
    }
}
