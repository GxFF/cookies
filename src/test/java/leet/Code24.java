package leet;

import common.ListNode;
import common.ListUtil;
import org.junit.Assert;
import org.junit.Test;

public class Code24 {

    /**
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     *
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     *
     * 示例:
     * 给定 1->2->3->4, 你应该返回 2->1->4->3.
     */
    public ListNode swapPairs(ListNode head){
        if (head == null) {
            return null;
        }
        ListNode root = new ListNode(0);
        ListNode before = root;
        before.next = head;
        ListNode first, second;
        while (true) {
            first = before.next;
            if (first == null) {
                break;
            }
            second = before.next.next;
            if (second == null) {
                break;
            }
            before.next = second;
            first.next = second.next;
            second.next = first;
            before = first;
        }
        return root.next;
    }

    @Test
    public void baseTest() {
        ListUtil.ListHolder source = ListUtil.toLink(1, 2, 3, 4);
        ListUtil.ListHolder target = ListUtil.toLink(2, 1, 4, 3);
        Assert.assertTrue(ListUtil.equals(target.getHead(), swapPairs(source.getHead())));
        ListUtil.ListHolder source1 = ListUtil.toLink(1);
        ListUtil.ListHolder target1 = ListUtil.toLink(1);
        Assert.assertTrue(ListUtil.equals(target1.getHead(), swapPairs(source1.getHead())));
    }
}