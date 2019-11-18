package offer;

import org.junit.Test;

public class Topic17 {

    public void printNumTo(int n) {
        if (n < 1) {
            return;
        }
        int[] nums = new int[n];
        while (true) {
            int pos = n-1;
            while (pos >= 0 && add(nums, pos)) {
                pos--;
            }
            if (pos < 0) {
                break;
            }
            print(nums);
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

    private boolean add(int[] nums, int pos) {
        nums[pos]++;
        if (nums[pos] == 10) {
            nums[pos] = 0;
            return true;
        }
        return false;
    }

    @Test
    public void main() {
        printNumTo(0);
    }
}