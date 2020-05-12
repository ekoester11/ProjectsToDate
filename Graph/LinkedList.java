/**
* Represents a linked list that contains generic objects.

*
* @author Christine Reilly
*/

import java.util.*;

import examples.Node;

public class LinkedList<E> {

    /** A reference to the first node in the Linked List */
    private Node<E> head;

    /**
    * Constructs an empty list.
    */
    public LinkedList() {
        head = null;
    }

    /**
    * Returns the size of this list.
    *
    * @return The size of this list.
    */
    public int size() {
        int count = 0; // Number of objects in the list

        Node<E> temp = head; // Reference to the current node

        while(temp != null) {
            // Increment the count of objects in the list
            count++;

            // Set temp to the next node in the list
            temp = temp.getNext();
        }
        return count;
    }

    /**
    * Appends the specified object to the end of this list.
    *
    * @d The object data to place at the end of this list.
    */
    public boolean add(E d) {
        // create a new node
        Node<E> n = new Node<E>(d);

        if(head == null) {
            // list is empty
            head = n;
        } else {
            // list is not empty
            // Traverse the list until the last node is found
            Node<E> temp = head;

            while(temp.getNext() != null) {
                // There is another node after the current one.
                // Set temp to the next node in the list.
                temp = temp.getNext();
            }
            // Now temp is the last node in the list. Update temp so that
            // the new node is added to the list.
            temp.setNext(n);
        }

        return true;
    }

    /**
    * Inserts the object at the given index. Shifts the object currently at that
    * position, and any subsequent objects, to the right.
    *
    * @param i The index where the object should be inserted.
    * @param d The object data to insert into this list.
    * @throws IndexOutOfBoundsException if index is less than zero or greater than list size
    */
    public void add(int i, E d) throws IndexOutOfBoundsException {
        // Check that i is greater than 0 and less than the current number of
        // elements in this list.
        if(i < 0 || i > this.size()) {
            throw new IndexOutOfBoundsException("index is not valid");
        }

        int count = 0; // current position in the list
        Node<E> current = head; // current node in the list
        Node<E> prev = null; // previous node in the list
        Node<E> newNode = new Node<E>(d); // new node to add to the list

        // Traverse the list to the position where the insert will occur
        while(count < i) {
                // Set the previous node to be the current node
                prev = current;

                // Update the current node to be the next node
                current = current.getNext();

                // Add 1 to the count of node
                count++;
        }

        // Insert the node

        // First, set the previous node to reference the new node.
        // If this insert is at the beginning of the list, update head.
        if(prev == null) {
            // inserting at the beginning of the list
            head = newNode;
        } else {
            // The previous node's next references the new node
            prev.setNext(newNode);
        }

        // Second, set the new node's next to reference the next node in
        // the list. If this insert is at the end of the list, leave next set
        // to null.

        // The new node's next references the current node
       newNode.setNext(current);
    }

    /**
    * Returns the data found at index i. Does not alter the list.
    *
    * @param i The index of the desired element.
    * @return The element found at index i
    * @throws IndexOutOfBoundsException if i is less than zero or greater than (list size - 1)
    */
    public E get(int i) throws IndexOutOfBoundsException {
        // Check that i is greater than 0 and less than the current number of
        // elements in this list - 1.
        if(i < 0 || i > this.size() - 1) {
            throw new IndexOutOfBoundsException("index is not valid");
        }
        Node<E> temp = head;
        int count = 0;

        // Traverse the list to the desired item
        while(temp.getNext() != null && count < i ) {
            temp = temp.getNext();
            count++;
        }
        return temp.getData();
    }

    /**
    * Returns true if the list contains the specified object.
    *
    * @return true if the list contains the specified object.
    */
    public boolean contains(E d) {
        Node<E> temp = head;

        while(temp.getNext() != null) {
            if(temp.getData().equals(d)) {
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

    /**
    * Removes and returns the first object from this list.
    *
    * @return The object removed from this list.
    * @throws NoSuchElementException if the list is empty
    */
    public E removeFirst() throws NoSuchElementException {
        if(head == null) {
            throw new NoSuchElementException("Method cannot be called on an empty list");
        }
        Node<E> temp = head;
        head = head.getNext();

        return temp.getData();
    }

    /**
    * Removes and returns the last object from this list.
    *
    * @return The object removed from this list.
    * @throws NoSuchElementException if the list is empty
    */
    public E removeLast() throws NoSuchElementException {
        if(head == null) {
            throw new NoSuchElementException("Method cannot be called on an empty list");
        }
        Node<E> temp = head;
        Node<E> prev = null;

        // Traverse the list until the last item
        while(temp.getNext() != null) {
            prev = temp;
            temp = temp.getNext();
        }

        // Remove the item by setting the previous item's next reference to null
        prev.setNext(null);

        // Return the item that was removed from the list
        return temp.getData();

    }

    /**
    * Removes and returns the object from this list at the specified index.
    *
    * @return The object that was removed from this list.
    * @throws IndexOutOfBoundsException if i is less than zero or greater than (list size - 1)
    */
    public E remove(int i) {
        // Check that i is greater than 0 and less than the current number of
        // elements in this list - 1.
        if(i < 0 || i >= this.size()) {
            throw new IndexOutOfBoundsException("index is not valid");
        }

        Node<E> temp = head;
        Node<E> prev = null; // The previous node in the list
        int count = 0;

        // Traverse the list to the desired item
        while(temp.getNext() != null && count < i ) {
            prev = temp;
            temp = temp.getNext();
            count++;
        }

        // Remove the set the previous node's next reference to refer to the
        // next node in this list.
        // Special case: removed the first node
        if(prev == null) {
            head = temp.getNext();
        } else {
            prev.setNext(temp.getNext());
        }

        return temp.getData();
    }

    /**
    * Removes all elements from this list. The list will be empty after this
    * method returns.
    */
    public void clear() {
        head = null;
    }

    /**
    * Returns true if this list contains no elements.
    *
    * @return true if this list contains no elements
    */
    public boolean isEmpty() {
      if(head == null) {
        return true;
      }
      return false;
    }
}
