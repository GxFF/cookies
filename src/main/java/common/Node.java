package common;

import java.util.Objects;

public class Node<T> {
    public T var;
    public Node<T> next;

    public Node() {}

    public Node(T var) {
        this.var = var;
    }

    public Node(T var, Node next) {
        this.var = var;
        this.next = next;
    }

    public void printLink() {
        Node now = this;
        while (!Objects.isNull(now.next)) {
            now.print();
            System.out.print("-");
            now = now.next;
        }
        now.print();
        System.out.println();
    }

    public void print(){
        System.out.print("[" + var.toString() + "]");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?> node = (Node<?>) o;
        return Objects.equals(var, node.var) ;
    }

    @Override
    public int hashCode() {

        return Objects.hash(var, next);
    }
}
