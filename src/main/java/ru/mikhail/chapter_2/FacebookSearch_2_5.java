package ru.mikhail.chapter_2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

public class FacebookSearch_2_5 {

    private static final LinkedNode[] users = new LinkedNode[26];

    public static void main(String[] args) {
        readFile("/random_names.txt");
        System.out.println(contains("Avalos", "Max"));
    }

    private static void readFile(String fileName) {
        Objects.requireNonNull(FacebookSearch_2_5.class.getResourceAsStream(fileName));
        try (InputStream is = Objects.requireNonNull(FacebookSearch_2_5.class.getResourceAsStream(fileName)); Scanner scanner = new Scanner(is)) {
            while (scanner.hasNextLine()) {
                String[] name = scanner.nextLine().split(" ");
                add(name[1], name[0]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void add(String lastName, String firstName) {
        int i = lastName.toLowerCase().charAt(0) - 'a';
        LinkedNode user = users[i];
        users[i] = new LinkedNode(lastName, firstName, user);
    }

    private static boolean contains(String lastName, String firstName) {
        int i = lastName.toLowerCase().charAt(0) - 'a';
        LinkedNode user = users[i];
        while (user != null) {
            if (user.getLastName().equals(lastName) && user.getFirstName().equals(firstName)) return true;
            user = user.getNext();
        }
        return false;
    }


    private static class LinkedNode {
        private final String lastName;
        private final String firstName;
        private final LinkedNode next;

        private LinkedNode(String lastName, String firstName, LinkedNode next) {
            this.lastName = lastName;
            this.firstName = firstName;
            this.next = next;
        }

        public String getLastName() {
            return lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public LinkedNode getNext() {
            return next;
        }
    }

}
