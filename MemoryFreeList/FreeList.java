
public class FreeList {

	private DNode<FreeSlot> current;

	public FreeList() {

	}

	/**
	 * constructor
	 * @param f the size of the initial free memory area
	 */
	public FreeList(int f) {
		current = new DNode<FreeSlot>(new FreeSlot(0, f));
		current.setNext(current);
		current.setPrev(current);
	}

	/**
	 * 
	 */

	public int size() {
		if(current == null) {
			return 0;
		}
		int count = 0;
		DNode<FreeSlot> temp = current;
		do {
			count++;
			temp = temp.getNext();
		} while(temp != current);
		return count;
	}

	/**
	 * 
	 */

	public String toString() {
		if(current == null) {
			return "Empty list";
		}
		String output = "";
		DNode<FreeSlot> temp = current;
		while(temp.getPrev().getData().getAddress() < temp.getData().getAddress()) {
			temp = temp.getPrev();
		}
		DNode<FreeSlot> stop = temp;
		do {
			output += temp.getData().getAddress() + ", " + temp.getData().getSize() + "\n";
			temp = temp.getNext();
		} while(temp != stop);
		return output;
	}
	/**
	 * 
	 * @param m the amount of memory requested
	 * @return 	the	starting	address	of	the	allocated	area	of	memory,	or	returns	-1	if	the	amount	of	free	
memory	is	less	than	the	amount	requested
	 */
	public int allocate(int m) {

		DNode<FreeSlot> temp = current;
		DNode<FreeSlot> saved;
		DNode<FreeSlot> found = new DNode<FreeSlot>(new FreeSlot(0, 1));
		boolean done = false;
		do {

			if(current == null) {
				return -1;
			}
			if(temp.getData().getSize() >= m) {
				found = temp;
				done = true;

			}

			temp = temp.getNext();

		} while(temp != current && done == false);

		if(found.getData().getSize() == m) {
			if(found.getNext() == found) {
				current = null;
				return found.getData().getAddress();
			} else {
				found.getPrev().setNext(found.getNext());
				found.getNext().setPrev(found.getPrev());
				current=found.getNext();
				return found.getData().getAddress();
			}

		} else if(found.getData().getSize() > m) {

			int returned = found.getData().getAddress();
			FreeSlot f = new FreeSlot(found.getData().getAddress() + m,
					found.getData().getSize() - m);
			found.setData(f);
			current = found;
			return returned;

		} 

		return -1;


	}

	public void free(int a, int s) {
		DNode <FreeSlot> temp = current;
		DNode <FreeSlot> found = null;
		boolean done = false;
		


		if (current==null) {
			found = new DNode<FreeSlot>(new FreeSlot(a,s));
			found.setNext(found);
			found.setPrev(found);
			current = found;
			done = true;
		} else {
			while(temp.getPrev().getData().getAddress() < temp.getData().getAddress()) {
				temp = temp.getPrev();
			}
			DNode<FreeSlot> stop = temp;
			do {
				
				if (a<=temp.getData().getAddress()) {
					found= new DNode<FreeSlot>(new FreeSlot(a,s));
					temp.getPrev().setNext(found);
					found.setPrev(temp.getPrev());
					found.setNext(temp);
					temp.setPrev(found);
					done = true;
					break;

				} 
				temp=temp.getNext();
				// HAD TO CHANGE THE WHILE LOOP CONDITION
			} while (temp!=stop);
			
			temp = current;
			
			if(!done) {
				// loop until we find the biggest address in the list to insert into
				while(temp.getData().getAddress() < temp.getNext().getData().getAddress()) {
					temp = temp.getNext();
				}
				found = new DNode<FreeSlot>(new FreeSlot(a,s));
				temp.getNext().setPrev(found);
				found.setNext(temp.getNext());
				temp.setNext(found);
				found.setPrev(temp);
			}
		}
		temp = current;
		
		DNode<FreeSlot> removed = null;
		
		// if the inserted node's address plus its size equals the next node's address, then coalesce
		if(found.getData().getAddress() + found.getData().getSize() 
				== found.getNext().getData().getAddress()) {
			// create new FreeSlot object of address that is the inserted node and 
			// size of the inserted node plus the next node's size
			FreeSlot f = new FreeSlot(found.getData().getAddress(), 
					found.getData().getSize() + found.getNext().getData().getSize());
			removed = found.getNext();
			found.setData(f);
			found.setNext(found.getNext().getNext());
			found.getNext().setPrev(found);

		}
		
		// if found's previous address plus size equals founds address
		DNode<FreeSlot> removed1 = null;
		if(found.getData().getAddress() 
				== found.getPrev().getData().getAddress() + found.getPrev().getData().getSize()) {
			// create new FreeSlot object of address that was found's previous and 
			// size that was found's prev size plus found's size
			FreeSlot fs = new FreeSlot(found.getPrev().getData().getAddress(), 
					found.getPrev().getData().getSize() + found.getData().getSize());
			// ADDED THIS CAUSE WE REMOVE CURRENT IN THE LAST CASE
			removed1 = found.getPrev();
			found.setData(fs);

			// PUT THIS IF STATEMENT HERE FOR THE CASE WHEN WE COALESCE DOWN TO ONE NODE
			if(found.getNext().getNext() == found) {
				found.setNext(found);
				found.setPrev(found);
			} else {
			found.setPrev(found.getPrev().getPrev());
			found.getPrev().setNext(found);
			}
		}
		
		// if the node that was coalesced (and therefore removed) was the current node
		// then set current's reference to found
		// UPDATED THIS
		if(removed == current || removed1 == current) {
			current = found;
		}

	}


}
