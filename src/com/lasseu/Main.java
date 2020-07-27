package com.lasseu;

import java.io.*;
import java.util.*;


public class Main {


    public static void main(String[] args) throws IOException {

        boolean repeat = true;
        long time;
        Random ran = new Random();
        int input[];

        while(repeat) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("\nType wich sorting algorithm you want to test (1-4): ");
            System.out.println("1. Insertion sort ");
            System.out.println("2. Quick sort");
            System.out.println("3. Merge sort");
            System.out.println("4. Radix Sort");
            int sortMethod = Integer.parseInt(br.readLine());
            System.out.println("Type number of values to be sorted: ");
            int n = Integer.parseInt(br.readLine());

            input = new int[n];

            if (sortMethod == 1) {
                for (int i = 0; i < n; i++) {
                    input[i] = ran.nextInt(n);
                }
                time = System.currentTimeMillis();

                insertionSort(input);

                time = System.currentTimeMillis() - time;
                System.out.println("Insertion Sort: " + time / 1000.0 + " seconds " + "\\ Constant: " + (float)time/((float)n*n));
            }

            else if (sortMethod == 3) {
                for (int i = 0; i < n; i++) {
                    input[i] = ran.nextInt(n);
                }
                time = System.currentTimeMillis();

                mergeSort(input, 0, n - 1);

                time = System.currentTimeMillis() - time;
                System.out.println("Merge Sort: " + time / 1000.0 + " seconds " + "\\ Constant: " + (float)time/(float)n*Math.log(n));
            }

            else if (sortMethod == 2) {
                for (int i = 0; i < n; i++) {
                    input[i] = ran.nextInt(n);
                }
                time = System.currentTimeMillis();

                quicksort(input, 0, n - 1);

                time = System.currentTimeMillis() - time;
                System.out.println("Quicksort: " + time / 1000.0 + " seconds " + "\\ Constant: " + (float)time/(float)n*Math.log(n));
            }

            else if (sortMethod == 4){
                for (int i = 0; i < n; i++) {
                    input[i] = ran.nextInt(n);
                }
                time = System.currentTimeMillis();

                radixsort(input);

                time = System.currentTimeMillis() - time;
                System.out.println("Radix Sort: " + time / 1000.0 + " seconds " + "\\ Constant: " + (float)time/(float)n*String.valueOf(n).length());
            }
        }

    }
    public static void insertionSort(int[] input){
        int temp;
        for (int i = 1; i < input.length; i++) {
            for(int j = i; j > 0; j--) {
                if(input[j] < input[j-1]) {
                    temp = input[j];
                    input[j] = input[j-1];
                    input[j-1] = temp;
                }
            }
        }
    }

    public static void mergeSort (int[] input, int min, int max) {

        if (min==max)
            return;

        int[] temp;
        int index1, left, right;
        int size = max - min + 1;
        int mid = (min + max) / 2;

        temp = new int[size];

        mergeSort(input, min, mid);
        mergeSort(input, mid + 1,max);

        for (index1 = 0; index1 < size; index1++)
            temp[index1] = input[min + index1];

        left = 0;
        right = mid - min + 1;
        for (index1 = 0; index1 < size; index1++)
        {
            if (right <= max - min)
                if (left <= mid - min)
                    if (temp[left] > temp[right])
                        input[index1 + min] = temp[right++];
                    else
                        input[index1 + min] = temp[left++];
                else
                    input[index1 + min] = temp[right++];
            else
                input[index1 + min] = temp[left++];
        }
    }

    static int partition(int input[], int low, int high)
    {
        int pivot = input[high];
        int i = (low-1);
        for (int j=low; j<high; j++)
        {
            if (input[j] <= pivot)
            {
                i++;
                int temp = input[i];
                input[i] = input[j];
                input[j] = temp;
            }
        }
        int temp = input[i+1];
        input[i+1] = input[high];
        input[high] = temp;

        return i+1;
    }

    public static void quicksort(int input[], int low, int high)
    {
        if (low < high)
        {
            int pi = partition(input, low, high);

            quicksort(input, low, pi-1);
            quicksort(input, pi+1, high);
        }
    }

    public static void radixsort(int[] input) {

        List<Integer>[] buckets = new ArrayList[10];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<Integer>();
        }

        boolean flag = false;
        int tmp = -1, divisor = 1;
        while (!flag) {
            flag = true;
            for (Integer i : input) {
                tmp = i / divisor;
                buckets[tmp % 10].add(i);
                if (flag && tmp > 0) {
                    flag = false;
                }
            }
            int a = 0;
            for (int b = 0; b < 10; b++) {
                for (Integer i : buckets[b]) {
                    input[a++] = i;
                }
                buckets[b].clear();
            }
            divisor *= 10;
        }
    }
}
