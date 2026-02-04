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
        System.out.println(kNearestNeighborsAlgorithm(persons, target));
    }

    private static Recommend kNearestNeighborsAlgorithm(Person[] persons, Person target) {
        if (persons.length == 0) return null;
        Recommend recommend = null;
        Map<Category, Integer> targetRates = target.rates();
        for (Person person : persons) {
            Map<Category, Integer> personRates = person.rates();
            double recommendRate = 0;
            for (Category value : getValues()) {
                recommendRate += Math.pow(Optional.ofNullable(personRates.get(value)).orElse(0) - Optional.ofNullable(targetRates.get(value)).orElse(0), 2);
            }
            if (recommendRate != 0) {
                recommendRate = Math.sqrt(recommendRate);
            }
            if (recommend == null || recommendRate < recommend.rate()) {
                recommend = new Recommend(person, recommendRate);
            }
        }
        return recommend;
    }
}
