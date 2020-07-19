import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int n;
    private int capacity;


    // construct an empty randomized queue
    public RandomizedQueue() {
        capacity = 300;
        a = (Item[]) new Object[capacity];
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (n >= capacity || capacity - n < 50) {
            Item[] b = (Item[]) new Object[2 * capacity];
            for (int i = 0; i < n; i++) {
                b[i] = a[i];
            }
            a = b;
            capacity = 2 * capacity;
        }
        a[n++] = item;

    }

    // remove and return a random item
    public Item dequeue() {
        int k = StdRandom.uniform(n);
        Item ret = a[k];
        if (k != n - 1) {
            a[k] = a[n - 1];
        }
        n--;
        if (n <= capacity / 4) {
            Item[] b = (Item[]) new Object[capacity / 2];
            for (int i = 0; i < n; i++) {
                b[i] = a[i];
            }
            a = b;
            capacity = capacity / 2;
        }
        return ret;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        int k = StdRandom.uniform(n);
        return a[k];
    }


    // return an independent iterator over items in random order

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private int k = n;
        private Item ka[] = a;

        public boolean hasNext() {
            return k != 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (k == 0)
                throw new NoSuchElementException();
            int kk = StdRandom.uniform(k);
            Item ret = ka[kk];
            if (kk != k - 1) {
                ka[kk] = ka[k - 1];
            }
            k--;
            return ret;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        System.out.println(rq.isEmpty());
        rq.enqueue("hi");
        rq.enqueue("hello");
        System.out.println(rq.size());
        System.out.println(rq.dequeue());
        System.out.println(rq.sample());
        Iterator<String> it = rq.iterator();
        while (it.hasNext())
            System.out.println(it.next());

    }

}
