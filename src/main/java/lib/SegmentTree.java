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
        int size = (1 << (int) Math.ceil((Math.log(a.length) / Math.log(2)) + 1));
        tree =  (T[])new Object[size];
        this.function =  (x1, x2) -> {
          if (x1 == null) return x2;
          if (x2 == null) return x1;
          return function.apply(x1, x2);
        };
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
        return function.apply(leftResult, rightResult);

    }
}