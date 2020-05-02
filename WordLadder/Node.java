/**
* Class that represents one item in a list.
* Node data is a generic object.
*
* @author Christine Reilly
*/
public class Node<E> {
    // The <E> indicates that the data contained in this node is type E
    // where E can be any object type.
    // We use E in the data members and methods of this class to
    // specify the type of the data.

    /** The data contained in this node */
    private E data;

    /** A reference to the next node in the list */
    private Node<E> next;


    /**
    * Constructs a node.
    *
    * @param d The data to put into the new node.
    */
    public Node(E d) {
        setData(d);
        next = null;
    }

    /**
    * Mutator method for the data held within this node.
    *
    * @param d The object to contain in this node.
    */
    public void setData(E d) {
        data = d;
    }

    /**
    * Mutator method for the next node data member.
    *
    * @param n The node to set as the next node.
    */
    public void setNext(Node<E> n) {
        next = n;
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
    public Node<E> getNext() {
        return next;
    }
}
