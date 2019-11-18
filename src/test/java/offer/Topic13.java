package offer;

import org.junit.Assert;
import org.junit.Test;

public class Topic13 {

    private int countMove(int threshold, int row, int col) {
        boolean[][] visited = new boolean[row][col];
        int sum =0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                sum += count(threshold, row, col, visited, i, j);
            }
        }
        return sum;
    }

    private int count(int threshold, int row, int col, boolean[][] visited, int rowV, int colV) {
        if (rowV < 0 || rowV >= row || colV < 0 || colV >= col || visited[rowV][colV]) {
            return 0;
        }
        if (!couldMove(threshold, rowV, colV)) {
            return 0;
        }
        visited[rowV][colV]=true;
        int left = count(threshold, row, col, visited, rowV, colV - 1);
        int right = count(threshold, row, col, visited, rowV, colV + 1);
        int top = count(threshold, row, col, visited, rowV, colV - 1);
        int bottom = count(threshold, row, col, visited, rowV, colV + 1);
        return left + right + top + bottom + 1;
    }

    private boolean couldMove(int threshold, int row, int col) {
        return toNumSum(row) + toNumSum(col) < threshold;
    }

    private int toNumSum(int num) {
        int sum=0;
        while (num > 0) {
            sum += num%10;
            num/=10;
        }
        return sum;
    }

    @Test
    public void main() {
        Assert.assertEquals(0, countMove(0, 3, 3));
        Assert.assertEquals(0, countMove(0, 0,0));
        Assert.assertEquals(1, countMove(1, 3,3));
        Assert.assertEquals(1, countMove(1, 1,3));
    }
}