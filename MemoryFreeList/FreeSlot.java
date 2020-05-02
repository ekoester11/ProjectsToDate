/**
* This class represents the data contained in each memory free list node.
* Objects of this class are immutable: the data contained in an object cannot
* be changed after it is constructed.
*
* DO NOT MODIFY THIS FILE
*
* @author Christine Reilly
*/
public class FreeSlot {

  /** The memory address of the start of this segment of free memory */
  final private int address;

  /** The size of this segment of free memory */
  final private int size;

  /**
  * Constructor creates a new FreeSlot object with the given address and size.
  *
  * @param a The memory address of the start of this segment of free memory.
  * @param s The size of this segment of free memory.
  * @throws IllegalArgumentException if address is less than zero, or if size
  *     is not greater than zero.
  */
  public FreeSlot(int a, int s) throws IllegalArgumentException {
    if(a < 0) {
      throw new IllegalArgumentException("Address must be zero or greater: " + a);
    }
    if(s <= 0) {
      throw new IllegalArgumentException("Size must be greater than zero: " + s);
    }

    // Set the values of the data members
    address = a;
    size = s;
  }

  /**
  * Returns the address of this free slot.
  *
  * @return The address of this free slot.
  */
  public int getAddress() {
    return address;
  }

  /**
  * Returns the size of this free slot.
  *
  * @return The size of this free slot.
  */
  public int getSize() {
    return size;
  }

}
