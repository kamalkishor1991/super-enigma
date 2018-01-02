package lib.string;

import java.util.BitSet;


public class BloomFilter<E> {
    private BitSet bitSet;
    private int k, m;
    public BloomFilter(int k, int m) {
        bitSet = new BitSet(m);
        this.k = k;
        this.m = m;
    }

    public void add(E e) {
        int h = e.hashCode();
        int a = h & (0xFFFF); // first 16 bit
        int b = (h >>> 16); // last 16 bit.
        for (int i = 0;i < k;i++) {
            int p = (a + b * i) % m;
            bitSet.set(p);
        }
    }

    public boolean test(E e) {
        int h = e.hashCode();
        int a = h & (0xFFFF); // first 16 bit
        int b = (h >>> 16); // last 16 bit.
        boolean r = true;
        for (int i = 0;i < k;i++) {
            int p = (a + b * i) % m;
            if (!bitSet.get(p)) {
                r = false;
            }
        }
        return r;
    }

    public static void main(String args) {
        BloomFilter<String> bloomFilter = new BloomFilter<String>(10, 997 /*prime*/);
        bloomFilter.add("bloom");
        bloomFilter.add("filter");
        bloomFilter.add("test");
        bloomFilter.add("hello");
        System.out.println(bloomFilter.test("hello"));
    }
}


