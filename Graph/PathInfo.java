/**
 * represents a node in a graph with getters and setters for 
 * name, length of path, a string representation of the path, 
 * and the node index. Contains a toString method to contents of node
 * @author Eli and Pranav
 *
 */
public class PathInfo implements Comparable<PathInfo>{
	
	String name;
	int length;
	String pathString;
	int index;
	
	/**
	 * creates new PathInfo object
	 * @param n name
	 * @param l length
	 * @param ps string rep of path
	 * @param i index
	 */
	public PathInfo(String n, int l, String ps, int i) {
		setName(n);
		setLength(l);
		setPathString(ps);
		setIndex(i);
	}
	
	/**
	 * sets index
	 * @param i index
	 */
	public void setIndex(int i) {
		index = i;
	}
	
	/**
	 * gets index
	 * @return index
	 */
	public int getIndex() {
		return index;
	}
	
	/**
	 * sets name
	 * @param n name
	 */
	public void setName(String n) {
		name = n;
	}
	
	/**
	 * sets length
	 * @param l length
	 */
	public void setLength(int l) throws IllegalArgumentException{
		if(l < 0) {
			throw new IllegalArgumentException("length has to be greater than 0");
		}
		length = l;
	}
	
	/**
	 * sets pathString
	 * @param ps string of path representation
	 */
	public void setPathString(String ps) {
		pathString = ps;
	}
	
	
	/**
	 * gets name
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	
	/**
	 * gets length
	 * @return length
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * gets pathString
	 * @return pathString
	 */
	public String getPathString() {
		return pathString;
	}
	
	/**
	 *@return string of this node
	 */
	public String toString() {
		return " to " + name + " is " + length + " [" + pathString + "]";
	}

	/**
	 * implement compareTo method
	 */
	public int compareTo(PathInfo o) {
		if(o.getLength() > length) {
			return -1;
		} else if (o.length < length) {
			return 1;
		} else {
		return 0;
		}
	}
	
	

}
