package org.ty.cloudCourse.util;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author kangtaiyang
 * @date 2018/7/31
 */
public class TyArrayList implements List {
    private Object[] elementData;
    private int size;

    public TyArrayList(int initCapacity) {
        elementData = new Object[initCapacity];
        size = 0;
    }

    public TyArrayList() {
        this(10);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

    @Override
    public boolean contains(Object o) {
        for (Object obj :
                elementData) {
            if (obj.equals(o) || obj == o) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {
        ensureCapacity();
        elementData[size] = o;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            Object obj = elementData[i];
            if (obj == o || obj.equals(o)) {
                elementData[i] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public void clear() {
        elementData = new Object[10];
    }

    @Override
    public Object get(int index) {
        if (index >= size) {
            throw  new ArrayIndexOutOfBoundsException();
        }
        return elementData[index];
    }

    @Override
    public Object set(int index, Object element) {
        if (size < index) {
            throw new ArrayIndexOutOfBoundsException();
        }
        elementData[index] = element;
        return true;
    }

    @Override
    public void add(int index, Object element) {

    }

    @Override
    public Object remove(int index) {
        if (size < index) {
            throw new ArrayIndexOutOfBoundsException();
        }
        elementData[index] = null;
        return true;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            Object obj = elementData[i];
            if (obj == o || obj.equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size; i > 0; i++) {
            Object obj = elementData[i];
            if (obj == o || obj.equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    public void ensureCapacity() {
        if (elementData.length == size) {
            Object[] newElementData = new Object[size * 2 + 1];
            System.arraycopy(elementData, 0, newElementData, 0, size);
            elementData = newElementData;
        }
    }

}
