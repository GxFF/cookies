package com.guo.interview.offer;

import com.guo.interview.offer.common.LinkUtil;
import com.guo.interview.offer.common.Node;
import org.junit.Assert;
import org.junit.Test;

public class Topic23 {

    public Node entranceOfCycle(Node head) {
        if (null == head) {
            return null;
        }
        Node prev = head, next = head;
        while (prev.next != null && prev.next.next != null) {
            prev = prev.next.next;
            next = next.next;
            if (prev == next) {
                break;
            }
        }
        if (prev != next) {
            return null;
        }
        prev = head;
        while (prev != next) {
            prev = prev.next;
            next = next.next;
        }
        return prev;
    }

    @Test
    public void main() {
        LinkUtil.LinkHolder holder = LinkUtil.toLink(1, 2, 3, 4, 5, 6, 7);
        holder.get(6).next = holder.get(2);
        Assert.assertEquals(holder.get(2), entranceOfCycle(holder.getHead()));
    }
}