package ru.mikhail.chapter_4;

public class RecursiveBinarySearch_4_4 {

    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i * 12;
        }
        System.out.println(binarySearch(arr, 0, arr.length - 1, 5 * 12));
    }

    private static int binarySearch(int[] arr, int i, int j, int target) {
        if (i >= j) return -1;
        int curr = (i + j) / 2;
        if (arr[curr] == target) {
            return curr;
        } else if (arr[curr] < target) {
            return binarySearch(arr, curr + 1, j, target);
        } else {
            return binarySearch(arr, i, curr - 1, target);
        }
    }

}
