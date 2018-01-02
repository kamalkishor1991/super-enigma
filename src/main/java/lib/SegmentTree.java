package lib;

/**
 * Not fully tested
 */
@Deprecated
public class SegmentTree {
  long []tree;
  long []a;
  public SegmentTree(long a[]) {
    tree = new long[(int) (a.length * Math.log(a.length) * 2) + 5];
    this.a = a;
    buildSegTree(0, a.length,1, a);
  }

  private long buildSegTree(int l, int r, int index, long[] a) {
    if (l >= r) return Long.MAX_VALUE;
    if (l + 1 == r ) {
      return tree[index] = a[l];
    } else {
      int r1 = (l + r) / 2;
      int l2 = (r1);
      return tree[index] = Math.min(buildSegTree(l, r1, (index) * 2, a), buildSegTree(l2, r, index * 2 + 1, a));
    }
  }

  public long query(int l, int r) {
    return query(0, a.length, l, r, 1);
  }


  public long query(int l, int r, int ql, int qr, int index) {
    if (l >= r) return Integer.MAX_VALUE;
    if (ql == l && qr == r) {
      return tree[index];
    }

    int r1 = (l + r) / 2;
    int l2 = r1;
    if (ql >= l && qr <= r1) {
      return query(l, r1, ql, qr, index * 2);
    }
    if (ql >= l2 && qr <= r) {
      return query(l2, r, ql, qr, index * 2 + 1);
    }

    return Math.min(query(l, r1, ql, r1, index * 2), query(l2, r, l2, qr, index * 2 + 1));
  }
}