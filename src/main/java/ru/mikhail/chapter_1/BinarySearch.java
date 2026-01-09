package ru.mikhail.chapter_1;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i * 321;
        }
        System.out.println(binarySearch(arr,23 * 321));
    }

    private static int binarySearch(int[] arr, int target) {
        int min = 0;
        int max = arr.length - 1;
        int i;
        while (min <= max) {
            i = (max + min) / 2;
            if (arr[i] == target) return i;
            if (arr[i] < target) {
                min = i + 1;
            } else {
                max = i - 1;
            }
        }
        return -1;
    }
}