/**
* CS206 Lab 5

* Stack class
*
* @author Eli Koester
*/


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Stack<E> {
	
	/** The storage structure is an array of type E */
    private LinkedList<E> theStack;

    /**
    * Constructor initializes the stack data structure.
    */
    public Stack() {
        // Create an array list to hold the data
        theStack = new LinkedList<E>();
    }

    /**
    * Add to the top of the stack.
    *
    * @param thingToAdd The item to add to the stack
    */
    public void push(E thingToAdd) {
        theStack.add(thingToAdd);
    }

    /**
    * Remove from the top of the stack.
    *
    * @return The item removed from the stack.
    */
    public E pop() throws NoSuchElementException {
    	if(theStack.isEmpty() == true) {
    		throw new NoSuchElementException("Empty list");
    	}
        E removed = theStack.remove(theStack.size() - 1);
        return removed;
    }

    /**
    * Returns true if the stack contains no items.
    *
    * @return true if the stack contains no items; false otherwise.
    */
    public boolean isEmpty() {
        if(theStack.isEmpty() == true) {
            return true;
        }
        return false;
    }

    /**
    * Returns the top item from the stack and leaves that item on the stack.
    *
    * @return The top item from the stack.
    */
    public E peek() {
    	if(theStack.isEmpty() == true) {
    		return null;
    	}
        return theStack.get(theStack.size() - 1);
    }
	

}
