package ru.mikhail.chapter_6;

import java.util.Stack;

public record Path<T>(Stack<T> pathToTarget, int totalCost) {

    @Override
    public String toString() {
        StringBuilder path = new StringBuilder();
        if (pathToTarget == null) throw new RuntimeException("Path to target couldn't be null");
        for (int i = pathToTarget.size() - 1; i >= 0; i--) {
            path.append(pathToTarget.pop());
            if (i != 0) {
                path.append(" -> ");
            }
        }
        return "total cost: " + totalCost + "; path: " + path;
    }
}
