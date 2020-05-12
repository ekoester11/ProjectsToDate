import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Queue1<E> {
	 /** The storage structure is a doubly linked list of Integers */
    private DLL<E> q;

    /**
    * Constructor initializes the queue.
    */
    public Queue1() {
        q = new DLL<E>();
    }

    /**
    * Add to the head of the list.
    *
    * @param thingToAdd The item to add to the stack
    */
    public void enqueue(E thingToAdd) {
        q.add(0, thingToAdd);
    }

    /**
    * Remove from the tail of the list.
    *
    * @return The item removed from the queue.
    */
    public E dequeue() throws NoSuchElementException {
    	if(q.isEmpty() == true) {
    		throw new NoSuchElementException("Empty list");
    	}
        return q.removeLast();
    }

    /**
    * Returns true if the queue contains no items.
    *
    * @return true if the queue contains no items; false otherwise.
    */
    public boolean isEmpty() {
        return (q.isEmpty());
    }

}
