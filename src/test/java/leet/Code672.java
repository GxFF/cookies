package leet;

import java.util.HashSet;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;

public class Code672 {

    /**
     * 现有一个房间，墙上挂有 n 只已经打开的灯泡和 4 个按钮。在进行了 m 次未知操作后，你需要返回这 n 只灯泡可能有多少种不同的状态。
     *
     * 假设这 n 只灯泡被编号为 [1, 2, 3 ..., n]，这 4 个按钮的功能如下：
     *
     * 将所有灯泡的状态反转（即开变为关，关变为开）
     * 将编号为偶数的灯泡的状态反转
     * 将编号为奇数的灯泡的状态反转
     * 将编号为 3k+1 的灯泡的状态反转（k = 0, 1, 2, ...)
     *
     * 示例 1:
     * 输入: n = 1, m = 1.
     * 输出: 2
     * 说明: 状态为: [开], [关]
     *
     * 示例 2:
     * 输入: n = 2, m = 1.
     * 输出: 3
     * 说明: 状态为: [开, 关], [关, 开], [关, 关]
     *
     * 示例 3:
     * 输入: n = 3, m = 1.
     * 输出: 4
     * 说明: 状态为: [关, 开, 关], [开, 关, 开], [关, 关, 关], [关, 开, 开].
     *
     * 注意： n 和 m 都属于 [0, 1000].
     */
    public int flipLights(int n, int m) {
        Set<Integer> statusSet = new HashSet<>();
        n = Math.min(n, 6);
        int shift = Math.max(0, 6 - n);
        for (int op = 0; op < 16; op++) {
            int opCount = Integer.bitCount(op);
            if (opCount % 2 == m % 2 && opCount <= m) {
                int status = 0;
                if ((op & 0b0001) > 0) status ^= 0b111111 >> shift;
                if ((op & 0b0010) > 0) status ^= 0b101010 >> shift;
                if ((op & 0b0100) > 0) status ^= 0b010101 >> shift;
                if ((op & 0b1000) > 0) status ^= 0b100100 >> shift;
                statusSet.add(status);
            }
        }
        return statusSet.size();
    }

    @Test
    public void baseTest() {
        Assert.assertEquals(2, flipLights(1, 1));
        Assert.assertEquals(3, flipLights(2, 1));
        Assert.assertEquals(4, flipLights(3, 1));

    }
}