package offer;

import common.TNode;
import org.junit.Assert;
import org.junit.Test;

public class Topic8 {

    Node findNext(Node root, Node now) {
        if (null == root || null == now) {
            return null;
        }
        if (null == now.right) {
            Node parent = now.parent;
            while (null != parent) {
                if (parent.left == now) {
                    return parent;
                }
                now = parent;
                parent = now.parent;
            }
            return null;
        } else {
            TNode right = now.right;
            while (null != right.left) {
                right = right.left;
            }
            return (Node) right;
        }
    }

    @Test
    public void main() {
        Node h = new Node<>("h");
        Node i = new Node<>("i");
        Node e = new Node<>("e", h, i);
        h.parent = e;
        i.parent = e;
        Node d = new Node<>("d");
        Node b = new Node<>("b", d, e);
        d.parent = b;
        e.parent = b;
        Node f = new Node<>("f");
        Node g = new Node<>("g");
        Node c = new Node<>("c", f, g);
        f.parent = c;
        g.parent = c;
        Node a = new Node<>("a", b, c);
        b.parent = a;
        c.parent = a;
        Node root = a;
        Assert.assertEquals(i,findNext(root, e));
        Assert.assertEquals(a,findNext(root, i));
        Assert.assertEquals(b,findNext(root, d));
        Assert.assertNull(findNext(root, g));
    }
    private class Node<T> extends TNode{
        Node parent;

        public Node() {}

        public Node(T var) {
            super(var);
        }

        public Node(T var, Node parent) {
            super(var);
            this.parent = parent;
        }

        public Node(T var, TNode left, TNode right) {
            super(var, left, right);
        }

        public Node(T var, Node left, Node right, Node parent) {
            super(var, left, right);
            this.parent = parent;
        }
    }
}