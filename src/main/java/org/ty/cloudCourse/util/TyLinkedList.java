package org.ty.cloudCourse.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author kangtaiyang
 * @date 2018/7/31
 */
public class TyLinkedList implements List {

    private Node first;
    private Node last;
    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
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
        /*
         * 1.指定对象实现结点的添加
         * 2.要对头结点判断是否为空，空的话将制定对象置为头结点
         * 3.不为空，则往last后增加结点（直接）
         * 4.重要：要将last置为结点
         * 5.size++
         */
        Node temp = new Node();
        if (first == null) {
            temp.setObj(o);
            temp.setNext(null);
            temp.setPrevious(null);
            first = temp;
            last = temp;
        } else {
            //直接往last后增加新的节点
            temp.setObj(o);
            temp.setPrevious(last);
            temp.setNext(null);
            last.setNext(temp);
            last = temp;  //再把temp结点置为尾结点
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
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
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public Object get(int index) {
        Node node = nodeCheck(index);
        return node.getObj();
    }

    @Override
    public Object set(int index, Object element) {
        Node node = nodeCheck(index);
        node.setObj(element);
        return true;
    }

    @Override
    public void add(int index, Object element) {
        if (index+1>size){
            throw new ArrayIndexOutOfBoundsException();
        }
        //把本来位置上的向后挤一位
        //新结点的前驱指向结点的前驱
        //结点的前驱的后继指向新结点
        //新结点的后继指向结点temp
        //结点的前驱指向新结点
        //插在结点temp前面
        Node node = nodeCheck(index);
        Node previousNode = node.getPrevious();
        Node newNode = new Node();
        newNode.setPrevious(previousNode);
        newNode.setNext(node);
        previousNode.setNext(newNode);
        node.setPrevious(newNode);
        size++;
    }

    @Override
    public Object remove(int index) {
        Node node = nodeCheck(index);
        return true;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
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

    private Node nodeCheck(int index) {
        Node temp = null;
        if (first != null) {
            temp = first;
        }
        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }
        return temp;
    }

    class Node {
        private Node previous;
        private Node next;
        private Object obj;

        public Node() {
        }

        public Node(Node previous, Node next, Object obj) {
            this.previous = previous;
            this.next = next;
            this.obj = obj;
        }

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Object getObj() {
            return obj;
        }

        public void setObj(Object obj) {
            this.obj = obj;
        }
    }
}
