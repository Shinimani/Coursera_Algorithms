import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private class Node<Item> {
        private Item val;
        private Node<Item> parent;
        private Node<Item> child;

        Node(Item item) {
            val = item;
            parent = null;
            child = null;
        }

        public void setVal(Item val) {
            this.val = val;
        }

        public void setChild(Node<Item> child) {
            this.child = child;
        }

        public void setParent(Node<Item> parent) {
            this.parent = parent;
        }

        public Item getVal() {
            return val;
        }

        public Node<Item> getChild() {
            return child;
        }

        public Node<Item> getParent() {
            return parent;
        }
    }

    private Node<Item> front;
    private Node<Item> back;
    private int size;

    // construct an empty deque
    public Deque() {
        front = null;
        back = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node<Item> n = new Node<>(item);
        if (size == 0) {
            front = back = n;
        }
        else if (size == 1) {
            front = n;
            front.setChild(back);
            back.setParent(front);
        }
        else {
            front.setParent(n);
            n.setChild(front);
            front = n;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node<Item> n = new Node<>(item);
        if (size == 0) {
            front = back = n;
        }
        else if (size == 1) {
            back = n;
            front.setChild(back);
            back.setParent(front);
        }
        else {
            back.setChild(n);
            n.setParent(back);
            back = n;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }
        Item ret = front.getVal();
        front = front.getChild();
        size--;
        return ret;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }
        Item ret = back.getVal();
        back = back.getParent();
        size--;
        return ret;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node<Item> current = front;
        private int k = size;

        public boolean hasNext() {
            return k != 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (current == null || k == 0) {
                throw new java.util.NoSuchElementException();
            }
            Item item = current.getVal();
            current = current.getChild();
            k--;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> d = new Deque<String>();
        System.out.println(d.isEmpty());
        d.addFirst("First");
        System.out.println(d.removeFirst());
        d.addLast("Last");
        System.out.println(d.size());
        d.addFirst("Before first");
        d.addLast("after last");
        d.addLast("added to remove last");
        d.addFirst("added to remove first");

        System.out.println(d.removeLast());
        Iterator<String> it = d.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }


    }

}
