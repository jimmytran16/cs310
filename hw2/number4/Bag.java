import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Bag<Item> implements Iterable<Item> {
    private Node first; // first node in list
    private List<Item> itemList;

    private class Node {
        Item item;
        Node next;
    }

    // constructor
    public Bag(){
        itemList = new LinkedList<Item>(); //init the itemList
    }
    public void add(Item item) { // same as push() in Stack
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}