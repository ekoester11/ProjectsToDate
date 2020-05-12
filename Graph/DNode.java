/**
* Class that represents one item in a doubly linked list.
* Node data is a generic object.
*
* @author Christine Reilly
*/
public class DNode<E> {
    // The <E> indicates that the data contained in this node is type E
    // where E can be any object type.
    // We use E in the data members and methods of this class to
    // specify the type of the data.

    /** The data contained in this node */
    private E data;

    /** A reference to the next node in the list */
    private DNode<E> next;

    /** (Lab 4) A reference to the previous node in the list */
    private DNode<E> prev;


    /**
    * Lab 4
    * Constructs a node.
    *
    * @param d The data to put into the new node.
    */
    public DNode(E d) {
        setData(d);
        next = null;
        prev = null;
    }

    /**
    * Mutator method for the data held within this node.
    *
    * @param d The data to contain in this node.
    */
    public void setData(E d) {
        data = d;
    }

    /**
    * Mutator method for the next node data member.
    *
    * @param n The node to set as the next node.
    */
    public void setNext(DNode<E> n) {
        next = n;
    }

    /**
    * Lab 4
    * Mutator method for the previous node data member.
    *
    * @param n The node to set as the previous node.
    */
    public void setPrev(DNode<E> n) {
        prev = n;
    }

    /**
    * Returns the data contained in this node.
    *
    * @return The data contained in this node.
    */
    public E getData() {
        return data;
    }

    /**
    * Returns a reference to the next node in the list.
    *
    * @return A reference to the next node in the list.
    */
    public DNode<E> getNext() {
        return next;
    }

    /**
    * Returns a reference to the previous node in the list.
    *
    * @return A reference to the previous node in the list.
    */
    public DNode<E> getPrev() {
        return prev;
    }
}
