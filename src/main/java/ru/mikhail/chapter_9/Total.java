package ru.mikhail.chapter_9;

import java.util.HashSet;
import java.util.Set;

public class Total {

    private Set<Product> products;
    private int cost;
    private int weight;

    public Total(Set<Product> products, int cost, int weight) {
        this.products = products == null ? new HashSet<>() : products;
        this.cost = cost;
        this.weight = weight;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Total{" +
                "products=" + products +
                ", cost=" + cost +
                ", weight=" + weight +
                '}';
    }
}
