import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private final Node frontOfQueue;
    private final Node backOfQueue;
    private int size;

    private class Node {
        Item item;
        Node prev;
        Node next;

        Node(Item item) {
            this.item = item;
        }
    }

    // construct an empty deque
    public Deque() {
        frontOfQueue = new Node(null);
        backOfQueue = new Node(null);
        frontOfQueue.next = backOfQueue;
        backOfQueue.prev = frontOfQueue;
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

        Node newNode = new Node(item);
        newNode.next = frontOfQueue.next;
        newNode.prev = frontOfQueue;
        frontOfQueue.next.prev = newNode;
        frontOfQueue.next = newNode;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node newNode = new Node(item);
        newNode.next = backOfQueue;
        newNode.prev = backOfQueue.prev;
        backOfQueue.prev.next = newNode;
        backOfQueue.prev = newNode;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Node newNode = frontOfQueue.next;
        frontOfQueue.next = newNode.next;
        frontOfQueue.next.prev = frontOfQueue;
        size--;
        return newNode.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Node newNode = backOfQueue.prev;
        backOfQueue.prev = newNode.prev;
        backOfQueue.prev.next = backOfQueue;
        size--;
        return newNode.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new IteratorHelper();
    }

    private class IteratorHelper implements Iterator<Item> {

        private Node currentNode = backOfQueue;

        public boolean hasNext() {
            return currentNode != backOfQueue;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            currentNode = currentNode.next;
            return currentNode.item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Item item : this) {
            result.append(",").append(item);
        }
        if (result.length() > 0) {
            result = new StringBuilder(result.substring(1));
        }
        return "[" + result + "]";
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        while (!StdIn.isEmpty()) {
            String sign = StdIn.readString();
            switch (sign) {
                case "+f":
                    String itemOnFirst = StdIn.readString();
                    deque.addFirst(itemOnFirst);
                    break;
                case "+l":
                    String itemOnLast = StdIn.readString();
                    deque.addLast(itemOnLast);
                    break;
                case "-f":
                    if (!deque.isEmpty())
                        StdOut.print(deque.removeFirst() + " ");
                    break;
                case "-l":
                    if (!deque.isEmpty())
                        StdOut.print(deque.removeLast() + " ");
                    break;
                default:
                    break;
            }
        }
        StdOut.println("(" + deque.size() + " left on deque)");
        for (String str : deque) {
            StdOut.print(str + " ");
        }
    }
}
