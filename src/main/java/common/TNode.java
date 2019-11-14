package com.guo.interview.offer.common;

import java.util.Objects;

public class TNode<T> {
    public T var;
    public TNode left;
    public TNode right;

    public TNode() {}

    public TNode(T var) {
        this.var = var;
    }

    public TNode(T var, TNode left, TNode right) {
        this.var = var;
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TNode<?> tNode = (TNode<?>) o;
        return Objects.equals(var, tNode.var);
    }

    @Override
    public int hashCode() {
        return Objects.hash(var);
    }

    @Override
    public String toString() {
        return "TNode{" +
                "var=" + var +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
