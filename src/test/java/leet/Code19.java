package leet;

import common.ListNode;
import common.ListUtil;
import org.junit.Assert;
import org.junit.Test;

public class Code19 {

    /**
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     *
     * 示例：
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     *
     * 说明：
     * 给定的 n 保证是有效的。
     *
     * 进阶：
     * 你能尝试使用一趟扫描实现吗？
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        n += 1;
        ListNode first = head;
        int count = 0;
        while (first != null && count < n) {
            first = first.next;
            count++;
        }
        if (count < n - 1) {
            return head;
        }
        if (count < n) {
            return head.next;
        }
        ListNode last = head;
        while (first != null) {
            first = first.next;
            last = last.next;
        }
        last.next = last.next.next;
        return head;
    }

    @Test
    public void baseTest() {
        ListUtil.ListHolder<Integer> list = ListUtil.toLink(new Integer[]{1,2,3,4,5});
        ListUtil.ListHolder<Integer> aim = ListUtil.toLink(new Integer[]{1,2,3,5});
        Assert.assertTrue(ListUtil.equals(aim.getHead(), removeNthFromEnd(list.getHead(), 2)));
    }
}