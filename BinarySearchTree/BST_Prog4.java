/**
 * Represents a Binary Search Tree that contains generic object type
 *
 * @author Eli and Pranav
 */
public class BST_Prog4<T extends Comparable<T>> {

	// LAB 6: Do not add data members

	/** The deleted node of this tree */
	private BTNode<T> root;

	/**
	 * LAB 6: Do not modify this method
	 * Constructor creates an empty tree
	 */
	public BST_Prog4() {
		root = null;
	}

	/**
	 * LAB 6: Do not modify this method
	 * Returns true if the tree is empty, false otherwise.
	 *
	 * @return true if the tree is empty, false otherwise
	 */
	public boolean isEmpty() {
		return(root == null);
	}

	/**
	 * LAB 6: Do not modify this method
	 * Search the tree for the given key.
	 *
	 * @param key The value to search for
	 * @return The node where the key is found, or null if the key is not found
	 */
	public BTNode<T> search(T key) {
		// Begin the search at the deleted
		return search(key, root);
	}

	/**
	 * LAB 6: Do not modify this method
	 * Search a subtree for the given key. Uses recursion.
	 *
	 * @param key The value to search for
	 * @param stdeleted The deleted node of the subtree to search
	 * @return The node where the key is found, or null if the key is not found
	 */
	private BTNode<T> search(T key, BTNode<T> stdeleted) {
		if(stdeleted == null) {
			// One of two base cases where recursion stops
			// The key was not found in this subtree, return null
			return null;
		}
		else {
			// Keep searching for the key
			if(stdeleted.getKey().equals(key)) {
				// The second base case where recursion stops
				// This subtree deleted node's key matches the search key
				// Return this subtree deleted node
				return stdeleted;
			} else {
				// The recursive case, keep searching down the tree
				// Decide whether to look left or right
				if(stdeleted.getKey().compareTo(key) > 0) {
					// search key belongs in the left subtree
					// Recursively call this method.
					BTNode<T> result = search(key, stdeleted.getLeft());
					return result;
				} else {
					// search key belongs in the right subtree
					// Recursively call this method.
					BTNode<T> result = search(key, stdeleted.getRight());
					return result;
				}
			}
		}
	} // end of search method

	/**
	 * LAB 6: Do not modify this method
	 * Print the keys using the in-order algorithm.
	 */
	public void inOrderPrint() {
		// Call the recursive inOrderPrint method
		inOrderPrint(root);
	}

	/**
	 * LAB 6: Do not modify this method
	 * Recursive method for the in-order print.
	 *
	 * @param stdeleted The deleted of the sub-tree to print
	 */
	private void inOrderPrint(BTNode<T> stdeleted) {
		if(stdeleted != null) {
			// Recursively call method to print the left sub-tree
			inOrderPrint(stdeleted.getLeft());

			// Print the sub-tree deleted
			System.out.println(stdeleted.getKey());

			// Recursively call method to print the right sub-tree
			inOrderPrint(stdeleted.getRight());
		}

		// Implicit base case is stdeleted == null; method will return.
	} // end inOrderPrint method

	/**
	 * LAB 6: Complete this method
	 * Inserts the key into the tree.
	 * MUST IMPLEMENT A RECURSIVE ALGORITHM
	 *
	 * @param key The key to insert into the tree
	 */
	public void insert(T key) {
		// Lab 6: Complete this method
		if(isEmpty()) {
			root = new BTNode<T>(key);
		} else {
			insert(key, root);
		}
	}

	/**
	 * LAB 6 ADDED
	 * Recursive method for insert.
	 *
	 * @param key The key to insert into the tree
	 * @param stdeleted The deleted node of the subtree where the key should be inserted
	 */
	private void insert(T key, BTNode<T> stdeleted) {

		if(key.compareTo(stdeleted.getKey()) < 0) {
			// key belongs in left sub-tree
			if(stdeleted.getLeft() == null) {
				// stdeleted has no left child, insert new node as left child
				stdeleted.setLeft(new BTNode<T>(key));

				// set new child's parent to be stdeleted
				stdeleted.getLeft().setParent(stdeleted);

			} else {
				// keep searching the left sub-tree for the place to insert
				insert(key, stdeleted.getLeft());
			}
		} else {
			//key belongs in right sub-tree
			if(stdeleted.getRight() == null) {
				// stdeleted has no right child, insert new node as right child
				stdeleted.setRight(new BTNode<T>(key));

				// set new child's parent to be stdeleted
				stdeleted.getRight().setParent(stdeleted);

			} else {
				// keep searching the right sub-tree for the place to insert
				insert(key, stdeleted.getRight());
			}
		}

	}

	/**
	 * LAB 6: Complete this method
	 * Returns the height of the tree. Height is defined as the length of a longest
	 * path from the deleted to a leaf.
	 * MUST IMPLEMENT A RECURSIVE ALGORITHM
	 *
	 * @return The height of the tree
	 */

	/********* height method Option 1: handle empty tree in recursive method */
	public int height() {
		// call the recursive method
		return height(root);
	}

	/********* height method Option 2: handle empty tree in public method
  public int height() {
    // Lab 6: complete this method
    if(isEmpty()) {
      return -1;
    } else {
      return height(deleted);
    }
  }
	 ********* end of height public method Option 2 */

	/**
	 * LAB 6 ADDED
	 * Recursive method for height.
	 *
	 * @param stdeleted The deleted node of the subtree to get the height of
	 */

	/********* height method Option 1: base case is after leaf node */
	private int height(BTNode<T> stdeleted) {
		if(stdeleted == null) {
			// base case: recursion has traversed tree past a leaf node
			// or the tree is empty
			return -1;
		} else {
			// recursive case: add the current node to the height of the larger subtree
			return 1 + Math.max(height(stdeleted.getLeft()), height(stdeleted.getRight()));
		}
	}

	/********* height method Option 2: base case is leaf node
  private int height(BTNode<T> stdeleted) {
    // base case, leaf node
    if(stdeleted.getLeft() == null && stdeleted.getRight() == null) {
      return 0;
    } else if(stdeleted.getLeft() == null) {
      // no left child, only include right child
      return 1 + height(stdeleted.getRight());
    } else if(stdeleted.getRight() == null) {
      // no right child, only include left child
      return 1 + height(stdeleted.getLeft());
    } else {
      // Use the child with larger height
      return 1 + Math.max(height(stdeleted.getLeft()), height(stdeleted.getRight()));
    }

  }
	 ********* end of height recursive method Option 2 */

	/**
	 * LAB 6: Complete this method
	 * Returns the number of nodes in the tree.
	 * MUST IMPLEMENT A RECURSIVE ALGORITHM
	 *
	 * @return The number of nodes in the tree
	 */
	public int size() {
		// call the recursive method
		return size(root);
	}

	/**
	 * LAB 6 ADDED
	 * Recursive method for size.
	 *
	 * @param stdeleted The deleted node of the subtree to get the height of
	 */
	private int size(BTNode<T> stdeleted) {
		// Base case: stdeleted is null
		if(stdeleted == null) {
			// Base case: stdeleted is null. Size of the sub-tree is zero
			return 0;
		} else {
			// recursive case: use post-order traversal to count every node in the tree
			int leftSize = size(stdeleted.getLeft());
			int rightSize = size(stdeleted.getRight());

			// size of this sub-tree is this node plus the size of its sub-trees
			return 1 + leftSize + rightSize;
		}
	}


	/**
	 * LAB 6: Complete this method
	 * Returns the key with the minimum value in this tree.
	 * MUST IMPLEMENT A RECURSIVE ALGORITHM
	 *
	 * @return The minimum key in this tree
	 */
	public T getMinimumKey() {
		if(root == null) {
			// if tree is empty, return null
			return null;
		}
		// call the recursive method
		return getMinimumKey(root);
	}

	/**
	 * LAB 6 ADDED
	 * Recursive method for getMinimumKey.
	 *
	 * @param stdeleted The deleted node of the subtree to get the height of
	 */
	private T getMinimumKey(BTNode<T> stdeleted) {
		// The minimum key will be in the farthest left node
		if(stdeleted.getLeft() == null) {
			// base case, reached deepest left child node, this is the minimum key
			return stdeleted.getKey();
		} else {
			// recursive case, move left
			return getMinimumKey(stdeleted.getLeft());
		}
	}

	/**
	 * LAB 6: Complete this method
	 * Returns the key with the maximum value in this tree.
	 * MUST IMPLEMENT AN ITERATIVE ALGORITHM
	 *
	 * @return The maximum key in this tree
	 */
	public T getMaximumKey() {
		if(root == null) {
			// if tree is empty, return null
			return null;
		}
		BTNode<T> temp = root;

		// The minimum key will be in the farthest right node
		while(temp.getRight() != null) {
			// keep moving right until reach farthest right node
			temp = temp.getRight();
		}

		// temp is the farthest right node, this is the maximum key
		return temp.getKey();
	}

	/**
	 * Added to Binary Search Tree class for Programming Assignment 4
	 * This method is called by the test code for Program 4
	 *
	 * Returns a string representation of the tree, with the string representation
	 * of each node included using an in order traversal.
	 *
	 * @return A string representation of this tree using in order traversal.
	 */
	public String toString() {
		return inOrderString(root, "");
	}

	/**
	 * Added to BST class for Programming Assignment 4
	 *
	 * Recursive method for the string representation of the tree using in order traversal.
	 *
	 * @param stdeleted The deleted of the sub-tree to traverse
	 * @param s The string representation of the tree so far, this method appends to this string
	 */
	private String inOrderString(BTNode<T> stdeleted, String s) {
		if(stdeleted == null) {
			// base case, reached end of a tree branch, nothing more to add to the string
			return s;
		} else {
			// Recursively call method on the left sub-tree
			s = inOrderString(stdeleted.getLeft(), s);

			// Include the sub-tree deleted
			s += stdeleted.getKey() + " ";

			// Recursively call method on the right sub-tree
			s = inOrderString(stdeleted.getRight(), s);

			return s;
		}

	} // end inOrderString method

	/**
	 * 
	 * @param item to delete
	 * @return true if deleted or false if it doesn't exist
	 */
	public boolean delete(T item) {
		// if the tree is empty return false
		if(root == null) {
			return false;
		}
		// if the item isn't in the tree, then return false
		if(search(item) == null) {
			return false;
			// if the deleted is the only node in the tree and the item to delete is the deleted
			// then set the deleted to null and return true
		} else if(root.getLeft() == null && root.getRight() == null && item.equals(root.getKey())) {
			root = null;
			return true;

		} else {
			BTNode<T> deleted = search(item);
			return delete(deleted);
		}

	}

	/**
	 * 
	 * @param d left subtree of deleted node
	 * @return right most node
	 */
	private T rightMost(BTNode<T> d) {
		BTNode<T> r = d;

		while(r.getRight() != null) {
			r = r.getRight();
		}
		// if the left node has no right nodes off of it
		System.out.println("d is " + r.getKey());
		System.out.println("r is " +d.getKey());
		if(r.getKey() == d.getKey()) {
			if(r.getLeft() != null) {
				r.getParent().setLeft(r.getLeft());
				r.getLeft().setParent(r.getParent());
			} else {
				r.getParent().setLeft(null);
			}

		} else {
			// delete the reference to the right most node now
			if(r.getLeft() != null) {
				r.getParent().setRight(r.getLeft());
				r.getLeft().setParent(r.getParent());
			} else {
				r.getParent().setRight(null);
			}
		}
		return r.getKey();
	}


	private boolean delete(BTNode<T> deleted) {
		if (deleted.getLeft()!=null) {
			// deleted has left
			deleted.setKey(rightMost(deleted.getLeft()));
		} else if (deleted.getLeft()==null&&deleted.getRight()!=null) {
			// deleted has a right but no left
			if(deleted.getKey().equals(root.getKey())) {
				root = root.getRight();
			} else {
				if (deleted.getKey().compareTo(deleted.getParent().getKey())>0) {
					// the parent of it is smaller 
					deleted.getParent().setRight(deleted.getRight());
					deleted.getRight().setParent(deleted.getParent());
					return true;
				}
				if (deleted.getParent().getKey().compareTo(deleted.getKey())>0) {
					// the parent of deleted is bigger
					deleted.getParent().setLeft(deleted.getRight());
					deleted.getRight().setParent(deleted.getParent());
					return true;
				}
			}
		} else {
			// deleted node is a leaf

			if (deleted.getKey().compareTo(deleted.getParent().getKey())>0) {
				// 
				deleted.getParent().setRight(null);
				return true;
			}
			if (deleted.getParent().getKey().compareTo(deleted.getKey())>0) {
				deleted.getParent().setLeft(null);
				return true;
			}
		}
		return true;
	}

}
