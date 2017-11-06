package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {

    Entry<String> root = new Entry<String>("0");

//    public CustomTree(Entry<String> root) {
//        this.root = root;
//    }

    public static void main(String[] args) {

        CustomTree list = new CustomTree();

        for (int i = 1; i < 26; i++) {
            list.add(String.valueOf(i));
        }

//        System.out.println(list.size());
//        System.out.println(list.getParent("16"));

        //System.out.println("Expected 3, actual is " + ((CustomTree) list).getParent("8"));
        list.remove("2");
        System.out.println(list.size());
        //System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("11"));
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    public String getParent(String s) {

        ConcurrentLinkedQueue<Entry<String>> queue = new ConcurrentLinkedQueue<>();

        if (root == null) return null;

        queue.add(root);

        while(true) {

            Entry<String> customElement = queue.poll();

            if (customElement == null) {
                break;
            }

            if (customElement.elementName.equals(s)) {

                Entry<String> el = customElement.parent;

                if (el == null) {
                    return null;
                }
                else {
                    return el.elementName;
                }
            }

            if (customElement.leftChild != null) {
                queue.add(customElement.leftChild);
            }

            if (customElement.rightChild != null) {
                queue.add(customElement.rightChild);
            }
        }

        return null;
    }

    @Override
    public boolean add(String s) {

        Entry<String> el = new Entry<String>(s);

        ConcurrentLinkedQueue<Entry<String>> queue = new ConcurrentLinkedQueue<>();
        queue.add(root);

        while(true) {

            Entry<String> customElement = queue.poll();

            if (customElement.leftChild != null) {
                queue.add(customElement.leftChild);
            }

            if (customElement.rightChild != null) {
                queue.add(customElement.rightChild);
            }

            if (customElement.leftChild == null) {
                addLeftChild(customElement, s);
                break;
            } else if (customElement.rightChild == null) {
                addRightChild(customElement, s);
                break;
            }
        }

        return true;
    }

    private void addRightChild(Entry<String> parent, String s) {
        Entry<String> el = addElement(parent, s);
        parent.rightChild = el;
    }

    private void addLeftChild(Entry<String> parent, String s) {
        Entry<String> el = addElement(parent, s);
        parent.leftChild = el;
    }

    private Entry<String> addElement(Entry<String> parent, String s) {
        Entry<String> newElement = new Entry<String>(s);
        newElement.parent = parent;
        newElement.lineNumber = parent.lineNumber - 1;
        newElement.elementName = s;

        return newElement;
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    public boolean remove(Object o) {

        String search = o.toString();

        ConcurrentLinkedQueue<Entry<String>> queueSearch = new ConcurrentLinkedQueue<>();
        queueSearch.add(root);

        boolean found = false;

        while(!queueSearch.isEmpty()) {

            Entry<String> customElement = queueSearch.poll();

            if (customElement.leftChild != null && customElement.leftChild.elementName.equals(search)) {
                queueSearch.clear();
                queueSearch.add(customElement.leftChild);
                customElement.leftChild = null;
                found = true;
                continue;
            } else if (customElement.rightChild != null && customElement.rightChild.elementName.equals(search)) {
                queueSearch.clear();
                queueSearch.add(customElement.rightChild);
                customElement.rightChild = null;
                found = true;
                continue;
            }

            if (customElement.leftChild != null) {
                queueSearch.add(customElement.leftChild);

                if (found) {
                    customElement.leftChild = null;
                }
            }

            if (customElement.rightChild != null) {
                queueSearch.add(customElement.rightChild);

                if (found) {
                    customElement.rightChild = null;
                }
            }
        }

        return true;
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        ConcurrentLinkedQueue<Entry<String>> queue = new ConcurrentLinkedQueue<>();

        queue.add(root);

        int total = 0;

        while(true) {

            Entry<String> customElement = queue.poll();

            if (customElement == null) {
                break;
            }

            if (customElement != root) {
                total++;
            }

            if (customElement.leftChild != null) {
                queue.add(customElement.leftChild);
            }

            if (customElement.rightChild != null) {
                queue.add(customElement.rightChild);
            }
        }

        return total;
    }

    static class Entry<T> implements Serializable {
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;
        }

        public void checkChildren() {
            if (leftChild != null) {
                availableToAddLeftChildren = false;
            }
            if (rightChild != null) {
                availableToAddRightChildren = false;
            }
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren | availableToAddRightChildren;
        }
    }
}
