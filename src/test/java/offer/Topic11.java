package offer;

import org.junit.Assert;
import org.junit.Test;

public class Topic11 {

    private int getMin(int[] nums) {
        if (null == nums || 0 == nums.length) {
            throw new IllegalArgumentException();
        }
        int start = 0, end = nums.length - 1, center=start;
        while (nums[start] >= nums[end]) {
            if (end - start == 1) {
                center = end;
                break;
            }
            center = (start +end)/2;
            if (nums[center] > nums[start]||(nums[center] == nums[start] && nums[start] > nums[end])) {
                start = center;
            } else if (nums[end] < nums[center]||(nums[center] == nums[end] && nums[start] > nums[end])) {
                end = center;
            }else {
                int min = Integer.MAX_VALUE;
                for (int i = start; i <= end; i++) {
                    min = Math.min(min, nums[i]);
                }
                return min;
            }
        }
        return nums[center];
    }

    @Test
    public void main() {
        Assert.assertEquals(1, getMin(new int[]{3,4,5,1,2,}));
        Assert.assertEquals(1, getMin(new int[]{1}));
        Assert.assertEquals(1, getMin(new int[]{1,1}));
        Assert.assertEquals(0, getMin(new int[]{0,1}));
        Assert.assertEquals(0, getMin(new int[]{1,0}));
        Assert.assertEquals(0, getMin(new int[]{1,0,1}));
        Assert.assertEquals(0, getMin(new int[]{1,1,1,0,1,}));
        Assert.assertEquals(0, getMin(new int[]{1,0,1,1,1,}));
        try {
            getMin(new int[]{});
            getMin(null);
        } catch (Throwable e) {
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
    }
}