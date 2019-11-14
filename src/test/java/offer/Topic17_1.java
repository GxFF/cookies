package com.guo.interview.offer;

import org.junit.Test;

public class Topic17_1 {

    void printToMaxOfNDigits(int n) {
        if (n <= 0) {
            return;
        }
        int[] num = new int[n];
        for (int i = 0; i < 10; i++) {
            num[0] = i;
            printRecursively(num, 1);
        }
    }

    void printRecursively(int[] num, int index) {
        if (index >= num.length) {
            print(num);
            return;
        }
        for (int i = 0; i < 10; i++) {
            num[index] = i;
            printRecursively(num, index + 1);
        }
    }
    private void print(int[] nums) {
        boolean begin=false;
        for (int i = 0; i < nums.length; i++) {
            if (!begin && nums[i] > 0) {
                begin=true;
            }
            if (begin) {
                System.out.print(nums[i]);
            }
        }
        System.out.println();
    }

    @Test
    public void main() {
        printToMaxOfNDigits(3);
    }
}