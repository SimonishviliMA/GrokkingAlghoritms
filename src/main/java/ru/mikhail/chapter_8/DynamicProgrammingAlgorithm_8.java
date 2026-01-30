package ru.mikhail.chapter_8;

import java.util.HashSet;
import java.util.Set;

public class DynamicProgrammingAlgorithm_8 {

    public static void main(String[] args) {
        Product[] products = new Product[]{
                new Product("Tape recorder", 3000, 4),
                new Product("Laptop", 2000, 3),
                new Product("Guitar", 1500, 1),
                new Product("Iphone", 2000, 1)
        };
        System.out.println(dynamicProgrammingAlgorithm(4, products));
    }

    private static Total dynamicProgrammingAlgorithm(int backpackMaxWeight, Product[] products) {

        Total[][] totals = new Total[products.length][backpackMaxWeight];

        Product firstProduct = products[0];
        for (int j = 0; j < backpackMaxWeight; j++) {
            totals[0][j] = firstProduct.weight() - 1 <= j ? new Total(Set.of(firstProduct), firstProduct.cost(), firstProduct.weight()) : null;
        }

        for (int i = 1; i < totals.length; i++) {

            Product product = products[i];
            for (int j = 0; j < backpackMaxWeight; j++) {
                if (product.weight() - 1 <= j) {
                    Set<Product> productSet = new HashSet<>();
                    productSet.add(product);
                    int totalCost = product.cost();
                    int totalWeight = product.weight();

                    if (j - product.weight() >= 0) {
                        Total fitTotal = totals[i - 1][j - product.weight()];
                        if (fitTotal != null) {
                            productSet.addAll(fitTotal.products());
                            totalCost += fitTotal.cost();
                            totalWeight += fitTotal.weight();
                        }
                    }

                    if (totals[i - 1][j] != null && totals[i - 1][j].cost() > totalCost) {
                        totals[i][j] = totals[i - 1][j];
                    } else {
                        totals[i][j] = new Total(productSet, totalCost, totalWeight);
                    }
                } else {
                    totals[i][j] = totals[i - 1][j];
                }
            }
        }
        return totals[products.length - 1][backpackMaxWeight - 1];
    }
}
