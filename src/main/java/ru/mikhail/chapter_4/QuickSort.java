package ru.mikhail.chapter_4;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = new int[]{2,5,8,7,4,10,1};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private static void quickSort(int[] arr, int begin, int end) {
        if (begin < end) {
            int pi = partition(arr, begin, end);

            quickSort(arr, begin, pi - 1);
            quickSort(arr, pi + 1, end);
        }
    }

    private static int partition(int[] arr, int begin, int end) {
        int pivot = arr[end];
        int pi = begin - 1;
        for (int i = begin; i < end; i++) {
            if (arr[i] <= pivot) {
                swap(arr, i, ++pi);
            }
        }
        swap(arr, ++pi, end);
        return pi;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
