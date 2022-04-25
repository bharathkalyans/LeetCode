package leetcode;

// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

import java.util.Iterator;

class PeekingIterator implements Iterator<Integer> {

    private boolean hasPeeked;
    private int peekedValue;
    private final Iterator<Integer> iterator;

    public PeekingIterator(Iterator<Integer> iterator) {
        this.hasPeeked = false;
        this.iterator = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (!hasPeeked) {
            hasPeeked = true;
            peekedValue = iterator.next();
        }
        return peekedValue;
    }


    @Override
    public Integer next() {
        if (hasPeeked) {
            hasPeeked = false;
            Integer value = peekedValue;
            peekedValue = Integer.parseInt(null);
            return value;
        }
        return iterator.next();
    }

    @Override
    public boolean hasNext() {
        return hasPeeked || iterator.hasNext();
    }
}
