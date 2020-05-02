/**
* Represents one  Binary Tree node that contains generic data
*
* @author Christine Reilly
*/
public class BTNode<E> {

    /** The data in this node */
    private E key;

    /** Reference to the left child node */
    private BTNode<E> left;

    /** Reference to the right child node */
    private BTNode<E> right;

    /** Reference to the parent of this node */
    private BTNode<E> parent;

    /**
    * Initialize the data and set the children nodes to null.
    *
    * @param d The data to put into this node
    */
    public BTNode(E d) {
        setKey(d);
        left = null;
        right = null;
        parent = null;
    }

    /**
    * Accessor method for the data.
    *
    * @return The data contained by this node
    */
    public E getKey() {
        return key;
        
        
    }

    /**
    * Accessor method for the left child node.
    *
    * @return A reference to the left child node
    */
    public BTNode<E> getLeft() {
        return left;
    }

    /**
    * Accessor method for the right child node.
    *
    * @return A reference to the right child node
    */
    public BTNode<E> getRight() {
        return right;
    }

    /**
    * Accessor method for the parent node.
    *
    * @return A reference to the parent node
    */
    public BTNode<E> getParent() {
        return parent;
    }

    /**
    * Mutator method for the data.
    *
    * @param d The data to put into this node
    */
    public void setKey(E d) {
        key = d;
    }

    /**
    * Mutator method for the left child node.
    *
    * @param l A reference to the node to set as the left child
    */
    public void setLeft(BTNode<E> l) {
        left = l;
    }

    /**
    * Mutator method for the right child node.
    *
    * @param r A reference to the node to set as the right child
    */
    public void setRight(BTNode<E> r) {
        right = r;
    }

    /**
    * Mutator method for the parent node.
    *
    * @param r A reference to the node to set as the parent of this node
    */
    public void setParent(BTNode<E> p) {
        parent = p;
    }

}
