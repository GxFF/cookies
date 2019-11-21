package leet;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class Code31 {

    /**
     * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
     * <p>
     * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
     * <p>
     * 必须原地修改，只允许使用额外常数空间。
     * <p>
     * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
     * 1,2,3 → 1,3,2
     * 3,2,1 → 1,2,3
     * 1,1,5 → 1,5,1
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int index = -1;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                index = i - 1;
                break;
            }
        }
        if (index >= 0) {
            for (int i = nums.length - 1; i > index; i--) {
                if (nums[i] > nums[index]) {
                   swap(nums, index, i);
                   break;
                }
            }
        }
        reverse(nums, index + 1, nums.length - 1);
    }

    private void reverse(int[] array, int i, int j) {
        while (i < j) {
            swap(array, i++, j--);
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @Test
    public void baseTest() {
        int[] source = new int[]{1,2,3};
        nextPermutation(source);
        Assert.assertTrue(Arrays.equals(new int[]{1,3,2}, source));
        int[] source2 = new int[]{3,2,1};
        nextPermutation(source2);
        Assert.assertTrue(Arrays.equals(new int[]{1,2,3}, source2));
        int[] source3 = new int[]{1,1,5};
        nextPermutation(source3);
        Assert.assertTrue(Arrays.equals(new int[]{1,5,1}, source3));
        int[] source4 = new int[]{1,2,4,5,3};
        nextPermutation(source4);
        Assert.assertTrue(Arrays.equals(new int[]{1,2,5,3,4}, source4));
    }
}