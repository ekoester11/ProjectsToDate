/**
* Represents a linked list that contains generic objects.

*
* @author Christine Reilly
*/

import java.util.*;

public class DLL<E> {

    /** A reference to the first node in the Linked List */
    private DNode<E> head;

    /** A reference to the first node in the Linked List */
    private DNode<E> tail;


    /**
    * Constructs an empty list.
    */
    public DLL() {
        head = null;
        tail = null;
    }

    /**
    * Returns the size of this list.
    *
    * @return The size of this list.
    */
    public int size() {
        int count = 0; // Number of objects in the list

        DNode<E> temp = head; // Reference to the current node

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
        DNode<E> n = new DNode<E>(d);

        if(head == null) {
            // list is empty
            head = n;
            tail = n;
        } else {
            // list is not empty
            // tail refers to the place where we want to add
            DNode<E> temp = tail;

            // Update temp so that the new node is added to the list.
            temp.setNext(n);

            // The new node's previous refers to temp
            n.setPrev(temp);

            // the tail now refers to the new node
            tail = n;
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
        DNode<E> current = head; // current node in the list
        //DNode<E> prev = null; // previous node in the list
        DNode<E> newDNode = new DNode<E>(d); // new node to add to the list

        // Traverse the list to the position where the insert will occur
        while(count < i) {
                // Set the previous node to be the current node
                //prev = current;

                // Update the current node to be the next node
                current = current.getNext();

                // Add 1 to the count of node
                count++;
        }

        // If the position is the end of the list, call the other add method
        if(current == null) {
          add(d);
          return;
        }

        // Insert the node
        // current refers to the node that will come after the new node

        // First, set the previous node's next to reference the new node.
        // If this insert is at the beginning of the list, update head.
        if(current.getPrev() == null) {
            // inserting at the beginning of the list
            head = newDNode;
        } else {
            // The previous node's next references the new node
            current.getPrev().setNext(newDNode);
        }

        // Second, set the new node's next to reference the next node in
        // the list. If this insert is at the end of the list, leave next set
        // to null.

        // The new node's next references the current node
       newDNode.setNext(current);

       // The new node's previous references current's previous
       newDNode.setPrev(current.getPrev());

       // Third, set the current node's previous reference to the current node.
       // Make sure current is not null.
       if(current != null) {
         current.setPrev(newDNode);
       }
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
        DNode<E> temp = head;
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
        DNode<E> temp = head;

        // traverse the list
        while(temp.getNext() != null) {
            if(temp.getData().equals(d)) {
                // found the specified object
                return true;
            }
            temp = temp.getNext();
        }

        // did not find the specified object
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

        // keep a reference to the removed node
        DNode<E> temp = head;

        if(head == tail) {
          // special case, removing the final node from the list
          head = null;
          tail = null;
        } else {
          // Set head to the second node in the list
          head = head.getNext();

          // Set the new head's previous to null
          head.setPrev(null);
        }

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

        // keep a reference to the removed node
        DNode<E> temp = tail;

        if(head == tail) {
          // special case, removing the final node from the list
          head = null;
          tail = null;
        } else {
          // Remove the item by setting the previous item's next reference to null
          temp.getPrev().setNext(null);

          // update tail to the new end of the list
          tail = temp.getPrev();
        }


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

        // Special case: remove the first node
        if(i == 0) {
          return removeFirst();
        }

        // Special case: remove the last node
        if(i == size() - 1) {
          return removeLast();
        }

        DNode<E> temp = head;
        int count = 0;

        // Traverse the list to the desired item
        while(temp.getNext() != null && count < i ) {
            //prev = temp;
            temp = temp.getNext();
            count++;
        }

        // Set the previous node's next reference to refer to the next node in this list.
        temp.getPrev().setNext(temp.getNext());

        // Set the next node's previous reference to refer to the previous node in this list.
        temp.getNext().setPrev(temp.getPrev());

        return temp.getData();
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

    /**
    * Removes all elements from this list. The list will be empty after this
    * method returns.
    */
    public void clear() {
        head = null;
        tail = null;
    }
}
