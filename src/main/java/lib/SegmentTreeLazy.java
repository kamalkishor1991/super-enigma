package lib;

import java.util.Objects;
import java.util.function.BiFunction;

public class SegmentTreeLazy<T> {
    private T[] a;
    private T[] tree;
    private T[] lazy;
    private BiFunction<T, T, T> function;
    public SegmentTreeLazy(T a[], BiFunction<T, T, T> function) {
        Objects.requireNonNull(function);
        this.a = a;
        int size = (1 << (int) Math.ceil((Math.log(a.length) / Math.log(2)) + 1));
        tree =  (T[])new Object[size];
        lazy =  (T[])new Object[size];
        this.function = (x1, x2) -> {
            if (x1 == null) return x2;
            if (x2 == null) return x1;
            return function.apply(x1, x2);
        };
        build(1, 0, a.length - 1);

    }
    public T[] getData() {
        return a;
    }
    private T build(int root, int l, int r) {
        if (l == r) {
            return tree[root] = a[l];
        } else {
            int mid = (l + r) / 2;
            T left = build(root * 2, l, mid);
            T right = build(root * 2 + 1, mid + 1, r);
            return tree[root] = function.apply(left, right);
        }
    }
    public void update(int pos, T x) {
        a[pos] = x;
        update(pos, 1, 0, a.length - 1);
    }

    public void update(int l, int r, T x) {
        updateLazy(1, 0, a.length - 1,l, r, x);
    }

    private T updateLazy(int root, int l, int r, int ql, int qr, T x) {
        if (l > qr || r < ql) return null;
        if (lazy[root] != null) {
            tree[root] = function.apply(lazy[root], tree[root]);
            if (l != r) {

                lazy[root * 2] = function.apply(lazy[root], lazy[root * 2]);
                lazy[root * 2 + 1] = function.apply(lazy[root], lazy[root * 2 + 1]);
            }
            lazy[root] = null;
        }
        if (ql <= l && qr >= r ) {
            if (l != r) {
                lazy[root * 2] = function.apply(lazy[root * 2], x);
                lazy[root * 2 + 1] = function.apply(lazy[root * 2 + 1], x);
            }
            return tree[root] = function.apply(tree[root], x);
        } else {
            int mid = (l + r) / 2;
            T left = updateLazy(root * 2, l, mid, ql, qr, x);
            T right = updateLazy(root * 2 + 1, mid + 1, r, ql, qr, x);
            return tree[root] = function.apply(function.apply(left, right), tree[root]);
        }
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
        if (lazy[root] != null) {
            tree[root] = function.apply(lazy[root], tree[root]);
            if (l != r) {
                lazy[root * 2] = function.apply(lazy[root], lazy[root * 2]);
                lazy[root * 2 + 1] = function.apply(lazy[root], lazy[root * 2 + 1]);
            }
            lazy[root] = null;
        }
        if (lq <= l && rq >= r  )  {
            return tree[root];
        }
        int mid = (l + r) / 2;
        T left = query(root * 2, l, mid, lq, rq);
        T right = query(root * 2 + 1, mid + 1, r, lq, rq);
        T result = function.apply(left, right);
        return result;

    }
}

