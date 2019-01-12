package lib.collections;

import java.util.HashMap;

public class Counter<E> extends HashMap<E, Long> {
    public Counter() {
        super();
    }

    public Counter(int capacity) {
        super(capacity);
    }


    public void countUp(E e) {
        super.put(e, super.getOrDefault(e, 0L) + 1);
    }

    public long getCount(E e) {
        return super.getOrDefault(e, 0L);
    }

    public void countDown(E e) {
        long count = getCount(e);
        if (count == 1) {
            super.remove(e);
        } else {
            super.put(e, count - 1);
        }
    }
}
