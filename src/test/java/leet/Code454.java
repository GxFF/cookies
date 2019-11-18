package leet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Code454 {

    /**
     * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
     *
     * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。
     *
     * 例如:
     *
     * 输入:
     * A = [ 1, 2]
     * B = [-2,-1]
     * C = [-1, 2]
     * D = [ 0, 2]
     *
     * 输出:
     * 2
     *
     * 解释:
     * 两个元组如下:
     * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
     * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int result = 0;
        if (A == null || A.length == 0 || B == null || B.length == 0 || C == null || C.length==0 || D == null || D.length==0) {
            return result;
        }
        Map<Integer, Integer> counter = new HashMap<>((int) (A.length * 1.5));
        int tempSum;
        for (int a : A) {
            for (int b : B) {
                tempSum = a + b;
                counter.put(tempSum, counter.getOrDefault(tempSum, 0) + 1);
            }
        }
        for (int c :C) {
            for (int d : D) {
                tempSum = -c - d;
                result += counter.getOrDefault(tempSum, 0);
            }
        }
        return result;
    }

    @Test
    public void baseTest() {
        Assert.assertEquals(2, fourSumCount(new int[]{1, 2}, new int[]{-2, -1}, new int[]{-1, 2}, new int[]{0, 2}));
    }
}