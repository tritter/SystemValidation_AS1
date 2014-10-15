public class BoardItem {

	/** ground is true if the square is valid playing area (not a wall) */
    public boolean ground; 
    
    /** marked is true if the square is a target location for a crate */
    public boolean marked;
    
    /** crate is true if the square contains a crate */
    public boolean crate;
    
    /** @informal creates a valid board item object */
    public BoardItem() {
        ground = false;
        marked = false;
        crate = false;
    }
    
    //@ skipesc
    public /*@ pure non_null @*/ String toString () {
      return ground ? (crate ? (marked ? "*" : "#") : (marked ? "." : " ")) : "@";
    }

}
