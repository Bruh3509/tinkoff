package edu.hw3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BackwardIterator<T> implements Iterator<T> {
    private final ArrayList<T> array;
    private Integer position;

    public BackwardIterator(Collection<T> collection) {
        array = new ArrayList<>(collection.stream().toList());
        position = array.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return position >= 0;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return array.get(position--);
    }
}
