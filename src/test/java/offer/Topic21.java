package com.guo.interview.offer;

import com.google.common.collect.Collections2;
import org.junit.Test;

import java.util.Arrays;


public class Topic21 {

    public int[] reorderOddEven(int[] nums) {
        if (null == nums || nums.length == 0) {
            return nums;
        }
        return reorder(nums, x -> (x & 1) == 1);
    }
    private int[] reorder(int[] nums, Partition partition) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            while (i < j && partition.isInLeft(nums[i])) {
                i++;
            }
            while (i < j && !partition.isInLeft(nums[j])) {
                j--;
            }
            if (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        return nums;
    }
    interface Partition{
        boolean isInLeft(int num);
    }
    @Test
    public void main() {
        System.out.println(Arrays.toString(reorderOddEven(new int[]{1,2,3,4,5,6,7})));
        System.out.println(Arrays.toString(reorderOddEven(new int[]{1})));
        System.out.println(Arrays.toString(reorderOddEven(new int[]{2})));
        System.out.println(Arrays.toString(reorderOddEven(new int[]{2,4})));
        System.out.println(Arrays.toString(reorderOddEven(new int[]{})));
    }
}