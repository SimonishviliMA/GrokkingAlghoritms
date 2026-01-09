package ru.mikhail.chapter_4;

public class RecursiveTheBiggestInt_4_3 {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,19,8,9,10};
        System.out.println(theBiggest(arr, arr.length - 1));
    }

    private static int theBiggest(int[] arr, int i) {
        return i <= 0 ? arr[0] : Math.max(arr[i], theBiggest(arr, --i));
    }
}
