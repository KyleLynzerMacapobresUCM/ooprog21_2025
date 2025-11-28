package Chapter_7;

import java.util.Arrays;

public class ArraysDemo {

    // Method to display message + array values
    public static void display(String message, int array[]) {
        System.out.print(message + "    ");
        for (int v : array) {
            System.out.print(v + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int[] nums = new int[5]; // default: 0 0 0 0 0

        // 1. Display original array
        display("Original array:", nums);

        // 2. Fill array with 8
        Arrays.fill(nums, 8);
        display("After filling with 8s:", nums);

        // 3. Change two elements (your screenshot values: 8 6 8 6 3)
        nums[0] = 8;
        nums[1] = 6;
        nums[2] = 8;
        nums[3] = 6;
        nums[4] = 3;
        display("After changing two values:", nums);

        // 4. Sort the array
        Arrays.sort(nums);
        display("After sorting:", nums);
    }
}
