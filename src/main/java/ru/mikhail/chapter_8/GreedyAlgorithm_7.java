package ru.mikhail.chapter_8;

import java.util.*;

import static ru.mikhail.chapter_8.State.*;

public class GreedyAlgorithm_7 {

    public static void main(String[] args) {
        Set<Station> stations = Set.of(
                new Station("kone", Set.of(ID, NV, UT)),
                new Station("ktwo", Set.of(WA, ID, MT)),
                new Station("kthree", Set.of(OR, NV, CA)),
                new Station("kfour", Set.of(NV, UT)),
                new Station("kfive", Set.of(CA, AZ))
        );

        System.out.println(greedyAlgorithm(stations, Set.of(State.values())));
    }

    private static Set<Station> greedyAlgorithm(Set<Station> inStations, Set<State> states) {
        Set<Station> result = new HashSet<>();
        Set<Station> stations = new HashSet<>(inStations);
        Set<State> coveredStates = new HashSet<>();
        while(!coveredStates.equals(states) && !stations.isEmpty()) {
            Station bestStation = null;
            Set<State> bestNewStates = new HashSet<>();
            int bestNumOfStates = 0;

            for (Station proceedStation : stations) {
                Set<State> newStates = new HashSet<>(proceedStation.states());
                newStates.removeAll(coveredStates);
                int newNumOfStates = newStates.size();
                if (bestNumOfStates < newNumOfStates) {
                    bestStation = proceedStation;
                    bestNewStates = newStates;
                    bestNumOfStates = newNumOfStates;
                }
            }

            if (bestStation == null) {
                break;
            }

            coveredStates.addAll(bestNewStates);
            result.add(bestStation);
            stations.remove(bestStation);
        }

        return result;
    }
}
