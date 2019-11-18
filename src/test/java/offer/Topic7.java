package offer;

import common.TNode;
import org.junit.Test;

import java.util.Arrays;

public class Topic7 {

    TNode rebuildTree(int[] preOrder, int[] midOrder) {
        if (null == preOrder || preOrder.length == 0 || null == midOrder || midOrder.length == 0) {
            return null;
        }
        int i = 0;
        for (;i < midOrder.length; i++) {
            if (midOrder[i] == preOrder[0]) {
                break;
            }
        }
        TNode root = new TNode<>(preOrder[0]);
        root.left = rebuildTree(Arrays.copyOfRange(preOrder, 1, 1 + i), Arrays.copyOfRange(midOrder, 0, i));
        root.right = rebuildTree(Arrays.copyOfRange(preOrder, 1 + i, preOrder.length), Arrays.copyOfRange(midOrder, i + 1, midOrder.length));
        return root;
    }

    @Test
    public void main() {
        int[] preOrder = new int[]{1, 2, 4, 7, 3, 5, 6, 8};
        int[] minOrder = new int[]{4, 7, 2, 1, 5, 3, 8, 6};
//        System.out.println(Arrays.toString(Arrays.copyOfRange(preOrder, 1, 1+2)));
        TNode root = rebuildTree(preOrder, minOrder);
        System.out.println(root);
    }
}