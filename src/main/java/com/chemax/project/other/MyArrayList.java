package machines;

import java.util.*;

public class MyArrayList<E> implements List<E> {

    private int size;
    private int capacity;
    private E[] obj;

    public MyArrayList() {
        size = 0;
        capacity = 10;
        obj = (E[]) new Object[capacity];
    }

    @Override
    public boolean add(E o) {
        if (size == capacity) {
            grow();
        }
        obj[size] = o;
        size++;
        return obj[size-1] != null;
    }

    private void grow() {
        capacity = capacity * 3 / 2;
        E[] raisedObj = (E[]) new Object[capacity];
        for (int i = 0; i < obj.length; i++) {
            raisedObj[i] = obj[i];
        }
        obj = raisedObj;
    }

    @Override
    public void add(int index, E element) {
        if (size == capacity) {
            grow();
        }
        for (int i = size; i > index-1; i--) {
            obj[i+1] = obj[i];
        }
        obj[index] = element;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E get(int index) {
        return obj[index];
    }

    @Override
    public boolean contains(Object o) {
        boolean isIn = false;
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] == null) {
                isIn = false;
            } else {
                isIn = o.equals(obj[i]);

                if (isIn) {
                    break;
                }
            }
        }
        return isIn;
    }

    @Override
    public boolean remove(Object o) {
        boolean isIn = false;
        for (int i = 0; i < obj.length; i++) {
            if (o.equals(obj[i])) {
                obj[i] = null;
                size--;
                isIn = true;
                for (int j = i; j < obj.length-1; j++) {
                    obj[j] = obj[j + 1];
                }
            }
        } return (isIn);
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }



    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i] = null;
                size--;
            }
        }
    }

    @Override
    public Object set(int index, Object element) {
        obj[index] = (E) element;
        return element;
    }

    @Override
    public E remove(int index) {
        E element = obj[index];
        obj[index] = null;
            size--;
            for (int j = index; j < obj.length-1; j++) {
                obj[j] = obj[j + 1];
            } return element;
    }

    @Override
    public int indexOf(Object o) {
        int indexOf = -1;
        for (int i = 0; i < obj.length; i++) {
            if (o.equals(obj[i])) {
                indexOf = i;
            }
        } return indexOf;
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray(Object[] a) {
        throw new UnsupportedOperationException();
    }
}
