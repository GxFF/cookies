package leet;

import org.junit.Assert;
import org.junit.Test;

public class Code542 {

    /**
     * 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
     *
     * 两个相邻元素间的距离为 1 。
     *
     * 示例 1:
     * 输入:
     *
     * 0 0 0
     * 0 1 0
     * 0 0 0
     * 输出:
     *
     * 0 0 0
     * 0 1 0
     * 0 0 0
     * 示例 2:
     * 输入:
     *
     * 0 0 0
     * 0 1 0
     * 1 1 1
     * 输出:
     *
     * 0 0 0
     * 0 1 0
     * 1 2 1
     * 注意:
     *
     * 给定矩阵的元素个数不超过 10000。
     * 给定矩阵中至少有一个元素是 0。
     * 矩阵中的元素只在四个方向上相邻: 上、下、左、右。
     */
    public int[][] minDistance(int[][] arrays){
        if (arrays == null) {
            return null;
        }
        if (arrays.length == 0 || arrays[0].length == 0) {
            return new int[0][0];
        }
        int[][] result = new int[arrays.length][arrays[0].length];
        for (int i = 0; i < arrays.length; i++) {
            for (int j = 0; j < arrays[i].length; j++) {
                result[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < arrays.length; i++) {
            for (int j = 0; j < arrays[i].length; j++) {
                if (arrays[i][j] == 0) {
                    setValue(result, i, j, 0);
                }
            }
        }
        return result;
    }

    private void setValue(int[][] result, int row, int col, int distance) {
        if (row < 0 || row >= result.length || col < 0 || col >= result[row].length) {
            return;
        }
        if (distance < result[row][col]) {
            result[row][col] = distance;
            setValue(result, row - 1, col, distance + 1);
            setValue(result, row + 1, col, distance + 1);
            setValue(result, row, col - 1, distance + 1);
            setValue(result, row, col + 1, distance + 1);
        }
    }

    public int[][] updateMatrix(int[][] arrays) {
        if (arrays == null) {
            return null;
        }
        if (arrays.length == 0 || arrays[0].length == 0) {
            return new int[0][0];
        }
        int[][] result = new int[arrays.length][arrays[0].length];
        for (int i = 0; i < arrays.length; i++) {
            for (int j = 0; j < arrays[i].length; j++) {
                result[i][j] = 10001;
            }
        }
        for (int i = 0; i < arrays.length; i++) {
            for (int j = 0; j < arrays[i].length; j++) {
                if (arrays[i][j] == 0) {
                    result[i][j] = 0;
                } else {
                    if (i > 0) {
                        result[i][j] = Math.min(result[i][j], result[i - 1][j] + 1);
                    }
                    if (j > 0) {
                        result[i][j] = Math.min(result[i][j], result[i][j - 1] + 1);
                    }
                }
            }
        }
        for (int i = arrays.length-1; i >= 0; i--) {
            for (int j = arrays[i].length - 1; j >= 0; j--) {
                if (arrays[i][j] == 1) {
                    if (i < arrays.length - 1) {
                        result[i][j] = Math.min(result[i][j], result[i + 1][j] + 1);
                    }
                    if (j < arrays[i].length - 1) {
                        result[i][j] = Math.min(result[i][j], result[i][j + 1] + 1);
                    }
                }
            }
        }
        return result;
    }

    @Test
    public void baseTest() {
        int[][] case1 = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        int[][] result1 = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        Assert.assertEquals(result1, updateMatrix(case1));
        int[][] case2 = new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
        int[][] result2 = new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 2, 1}};
        Assert.assertEquals(result2, updateMatrix(case2));
    }
}