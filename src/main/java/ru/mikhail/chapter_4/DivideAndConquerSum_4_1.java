package ru.mikhail.chapter_4;

public class DivideAndConquerSum_4_1 {

    public static void main(String[] args) {
        int[] arr = new int[] {1,1,2,3,4,5,6,7,8,9,10};
        System.out.println(sum(arr, arr.length - 1));
    }

    private static int sum(int[] arr, int i) {
        return i < 0 ? 0 : arr[i] + sum(arr, --i);
    }
}
