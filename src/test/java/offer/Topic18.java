package com.guo.interview.offer;

import com.guo.interview.offer.common.Node;
import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

public class Topic18 {

    Node delNode(Node head, Node del) {
        if (Objects.isNull(head)) {
            return null;
        }
        if (Objects.isNull(del)) {
            return head;
        }
        if (Objects.isNull(del.next)) {
            Node prev = null, now = head;
            while (!Objects.isNull(now)) {
                if (del.equals(now)) {
                    if (head.equals(now)) {
                        return null;
                    } else {
                        prev.next = null;
                        return head;
                    }
                }
                prev = now;
                now = now.next;
            }
        } else {
            Node next = del.next;
            del.var = next.var;
            del.next = next.next;
        }
        return head;
    }

    @Test
    public void main() {
        Node n1 = new Node<>(1);
        Node n2 = new Node<>(2, n1);
        Assert.assertEquals(n2, delNode(n2, n1));
        n2.printLink();
        Node n3 = new Node<>(3);
        Assert.assertNull(delNode(n3, n3));
        Node n4 = new Node<>(4);
        Node n5 = new Node<>(5,n4);
        Node n6 = new Node<>(6,n5);
        Node n7 = new Node<>(7,n6);
        Assert.assertEquals(n7, delNode(n7, n5));
        n7.printLink();
    }
}