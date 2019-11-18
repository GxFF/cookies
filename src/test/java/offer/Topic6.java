package offer;

import common.Node;
import org.junit.Test;


public class Topic6 {

    public void printReversed(Node node) {
        if (null == node) {
            return;
        }
        if (null != node.next) {
            printReversed(node.next);
        }
        System.out.print(node.var);
    }

    @Test
    public void main() {
        Node n5 = new Node<>(5, null);
        Node n4 = new Node<>(4, n5);
        Node n3 = new Node<>(3, n4);
        Node n2 = new Node<>(2, n3);
        Node n1 = new Node<>(1, n2);
        printReversed(n1);
    }
}