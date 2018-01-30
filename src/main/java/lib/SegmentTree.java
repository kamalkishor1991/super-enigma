package lib;

import java.util.Objects;
import java.util.function.BiFunction;

public class SegmentTree<T> {
    private T[] a;
    private T[] tree;
    private BiFunction<T, T, T> function;
    public SegmentTree(T a[], BiFunction<T, T, T> function) {
        Objects.requireNonNull(function);
        this.a = a;
        tree =  (T[])new Object[3 * a.length + 10];
        this.function = function;
        build(1, 0, a.length - 1);

    }

    private T build(int root, int l, int r) {
        if (l == r) {
            return tree[root] = a[l];
        } else {
            T left = build(root * 2, l, (l + r) / 2);
            T right = build(root * 2 + 1, ((l + r) / 2) + 1, r);
            return tree[root] = function.apply(left, right);
        }
    }
    public void update(int pos, T x) {
        a[pos] = x;
        update(pos, 1, 0, a.length - 1);
    }

    private T update(int pos, int root, int left, int right) {
        if (pos < left || pos > right) {
            return tree[root];
        } else if (left == right) {
            return tree[root] = a[pos];
        } else {
            int mid = (left + right) / 2;
            T leftResult = update(pos, 2 * root, left, mid);
            T rightResult = update(pos, 2 * root + 1, mid + 1, right);
            tree[root] = function.apply(leftResult, rightResult);
            return tree[root];
        }

    }

    public T query(int l, int r) {
        return query(1, 0, a.length - 1, l, r);
    }

    private T query(int root, int l, int r, int lq, int rq) {
        if (rq < l || lq > r) return null;
        if (l == r) return tree[root];
        if (lq <= l && rq >= r  ) return tree[root];
        int mid = (l + r) / 2;
        T leftResult = query(root * 2, l, mid, lq, rq);
        T rightResult = query(root * 2 + 1, mid + 1, r, lq, rq);
        if (leftResult == null) return rightResult;
        if (rightResult == null) return leftResult;
        return function.apply(leftResult, rightResult);

    }
}