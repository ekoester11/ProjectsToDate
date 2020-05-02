import java.util.LinkedList;

/**
* Represents one  Binary Tree node that contains generic data
*
* @author Eli and Pranav
 * @param <E>
*/
public class BTNodeDup<E> {

    /** The data in this node */
    private LinkedList<E> key;

    /** Reference to the left child node */
    private BTNodeDup<E> left;

    /** Reference to the right child node */
    private BTNodeDup<E> right;

    /** Reference to the parent of this node */
    private BTNodeDup<E> parent;

    /**
    * Initialize the data and set the children nodes to null.
    *
    * @param d The data to put into this node
    */
    public BTNodeDup(E d) {
    	key = new LinkedList<E>();
        key.add(d);
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
        return key.get(0);
        
        
    }
    
    /**
     * gets the data
     * @return the linked list of nodes
     */
    public LinkedList<E> getKeyList() {
    	return key;
    }

    /**
    * Accessor method for the left child node.
    *
    * @return A reference to the left child node
    */
    public BTNodeDup<E> getLeft() {
        return left;
    }

    /**
    * Accessor method for the right child node.
    *
    * @return A reference to the right child node
    */
    public BTNodeDup<E> getRight() {
        return right;
    }

    /**
    * Accessor method for the parent node.
    *
    * @return A reference to the parent node
    */
    public BTNodeDup<E> getParent() {
        return parent;
    }

    /**
     * adds data to the linkedList
     * @param e data to add to the linkedList
     * @throws IllegalArgumentException
     */
   public void addKey(E e) throws IllegalArgumentException{
	   if(!e.equals(key.getFirst())) {
		   throw new IllegalArgumentException("item to add is not equal in items in linkedList");
	   }
	   key.addFirst(e);
	   
   }
   
   /**
    * removes item from linkedList
    * @return true if last item is removed from list false if otherwise
    * @throws IndexOutOfBoundsException
    */
   public boolean removeKey() throws IndexOutOfBoundsException{
	   if(key.size() == 0) {
		   throw new IndexOutOfBoundsException("the linkedList is empty");
	   }
	   key.removeLast();
	   if(key.size() == 0) {
		   return true;
	   } else {
		   return false;
	   }
   }
   
   /**
    * sets key
    * @param l linkedList to set to key
    */
   public void setKeyList(LinkedList<E> l) {
	   key = l;
   }

    /**
    * Mutator method for the left child node.
    *
    * @param l A reference to the node to set as the left child
    */
    public void setLeft(BTNodeDup<E> l) {
        left = l;
    }

    /**
    * Mutator method for the right child node.
    *
    * @param r A reference to the node to set as the right child
    */
    public void setRight(BTNodeDup<E> r) {
        right = r;
    }

    /**
    * Mutator method for the parent node.
    *
    * @param r A reference to the node to set as the parent of this node
    */
    public void setParent(BTNodeDup<E> p) {
        parent = p;
    }

}
