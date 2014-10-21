/**
 * Stores coordinates of the position on the board.
 */
final class Position
{

  /** @informal only allow positive positions 
	*/
  /*@ public invariant this.x > 0 && this.y > 0;
  
  /*@ spec_public */ final int x;
  
  /*@ spec_public */ final int y;
  
  /** @informal based on valid parameters the constructor creates a valid position object */ 
  /*@ 
  modifies this.x,this.y;
  requires true;
*/
  Position (int x, int y) {
    this.x = x;
    this.y = y;
  }

  /** @informal to be equal positions need to agree on both coordinates  */
  /*  normal_behavior
      @requires o != null
   	  @ensures y == q; */
  public boolean equals (Object o) {
    if (o instanceof Position) {
      Position q = (Position) o;
      return x == q.x && y == q.y;
    }
    return false;
  }

  /** @informal a valid next position is away one move horizontally
   *    or vertically from the current one */
  /*  normal_behavior
   	  @requires o != null
	  @ensures y == q; */
  boolean isValidNextPosition (Position newPosition) {
	  int dX = newPosition.x - x;
	  // @assert dx == newPosition.x - x;
	  int dY = newPosition.y - y;
	// @assert dx == newPosition.x - x;
	  if( dX >= -1 && dX <= 1 && dY >= -1 && dY <= 1) return true;
	  return false;
	  }

  //@ skipesc
  public /*@ pure non_null @*/ String toString () {
    return "position(" + this.x + "," + this.y + ")";
  }

}
