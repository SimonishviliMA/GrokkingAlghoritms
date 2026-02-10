package ru.mikhail.chapter_10;

import java.util.Map;
import java.util.Optional;

import static ru.mikhail.chapter_10.Category.*;

public class KNearestNeighborsAlgorithm_10 {

    /**
     * Misha = sqrt(pow(2 - 1, 2) + pow(4 - 4, 2) + pow(5 - 0, 2) + pow(0 - 4, 2)) = sqrt(1 + 0 + 25 + 16) = sqrt(42)
     * Toma = sqrt(pow(3 - 1, 2) + pow(2 - 4, 2) + pow(4 - 0, 2) + pow(2 - 4, 2)) = sqrt(4 + 4 + 16 + 4) = sqrt(28)
     * Iliya = sqrt(pow(5 - 1, 2) + pow(0 - 4, 2) + pow(1 - 0, 2) + pow(5 - 4, 2)) = sqrt(16 + 16 + 1 + 1) = sqrt(34)
     */
    public static void main(String[] args) {
        Person[] persons = new Person[] {
                new Person("Misha", Map.of(
                        COMEDY, 2,
                        ACTION, 4,
                        DRAMA,5
                )), new Person("Toma", Map.of(
                        COMEDY, 3,
                        ACTION, 2,
                        DRAMA, 4,
                        HORROR, 2
                )), new Person("Iliya", Map.of(
                        COMEDY, 5,
                        DRAMA, 1,
                        HORROR, 5
                ))
        };
        Person target = new Person("Leha", Map.of(
                COMEDY, 1,
                ACTION, 4,
                DRAMA, 0,
                HORROR, 4
        ));
        System.out.println(kNearestNeighborsAlgorithmPythagoreanTheorem(persons, target));
        System.out.println(kNearestNeighborsAlgorithmCosineSimilarity(persons, target));
    }

    private static Recommend kNearestNeighborsAlgorithmPythagoreanTheorem(Person[] persons, Person target) {
        if (persons.length == 0) return null;
        Recommend recommend = null;
        Map<Category, Integer> targetRates = target.rates();
        for (Person person : persons) {
            Map<Category, Integer> personRates = person.rates();
            double recommendRate = calculateRatePythagoreanTheorem(personRates, targetRates);

            if (recommend == null || recommendRate < recommend.rate()) {
                recommend = new Recommend(person, recommendRate);
            }
        }
        return recommend;
    }

    private static double calculateRatePythagoreanTheorem(Map<Category, Integer> personRates, Map<Category, Integer> targetRates) {
        double recommendRate = 0;
        for (Category value : Category.getValues()) {
            recommendRate += Math.pow(Optional.ofNullable(personRates.get(value)).orElse(0) - Optional.ofNullable(targetRates.get(value)).orElse(0), 2);
        }
        return Math.sqrt(recommendRate);
    }

    private static Recommend kNearestNeighborsAlgorithmCosineSimilarity(Person[] persons, Person target) {
        if (persons.length == 0) return null;
        Recommend recommend = null;
        Map<Category, Integer> targetRates = target.rates();
        for (Person person : persons) {
            Map<Category, Integer> personRates = person.rates();
            double recommendRate = calculateRateCosineSimilarity(personRates, targetRates);

            if (recommend == null || recommendRate > recommend.rate()) {
                recommend = new Recommend(person, recommendRate);
            }
        }
        return recommend;
    }

    private static double calculateRateCosineSimilarity(Map<Category, Integer> personRates, Map<Category, Integer> targetRates) {
        double dotProduct = 0;
        double personEuclideanNorm = 0;
        double targetEuclideanNorm = 0;
        for (Category value : getValues()) {
            int personRate = Optional.ofNullable(personRates.get(value)).orElse(0);
            int targetRate = Optional.ofNullable(targetRates.get(value)).orElse(0);
            dotProduct += personRate * targetRate;
            personEuclideanNorm += Math.pow(personRate, 2);
            targetEuclideanNorm += Math.pow(targetRate, 2);
        }
        return 1 - dotProduct / (Math.sqrt(personEuclideanNorm) * Math.sqrt(targetEuclideanNorm));
    }
}
