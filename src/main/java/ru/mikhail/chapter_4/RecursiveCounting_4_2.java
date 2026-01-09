package ru.mikhail.chapter_4;

public class RecursiveCounting_4_2 {
    public static void main(String[] args) {
        int[] arr = new int[10];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        System.out.println(recCount(arr.length - 1));
    }

    private static int recCount(int i) {
        return i < 0 ? 0 : recCount(--i) + 1;
    }
}
