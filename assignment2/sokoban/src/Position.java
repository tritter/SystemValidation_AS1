/**
 * Stores coordinates of the position on the board.
 */
final class Position
{

  /** @informal only allow positive positions */
  //@ public invariant x >= 0 && y >= 0;

  final /*@ spec_public */ int x;
  final /*@ spec_public */ int y;

  /** @informal based on valid parameters the constructor creates a valid position object */
  /*@ requires x >= 0 && y >= 0;
    @ ensures this.x == x;
    @ ensures this.y == y;
  @*/
//@ skipesc
  Position (int x, int y) {
    this.x = x;
    this.y = y;
  }

  /** @informal to be equal positions need to agree on both coordinates */ 
  /*@ requires true;
    @ ensures \result ==  ( o != null && (o instanceof Position) && x == ((Position)o).x && y == ((Position)o).y );
  @*/
//@ skipesc
  public boolean equals (Object o) {
    if (o instanceof Position) {
      Position q = (Position) o;
      //@ assert (q != null);
      return x == q.x && y == q.y;
    }
    return false;
  }

  /** @informal a valid next position is away one move horizontally or vertically from the current one */
  //@ ghost int gdX;
  //@ ghost int gdY; 
  
  /*@ requires newPosition != null;
    @ ensures \result == 
    			((gdX == 0) && (gdY == 1)) ||
    			((gdX == -1) && (gdY == 0))||
    			((gdX == 1) && (gdY == 0)) ||
    			((gdX == 0) && (gdY == -1));
  @*/
//@ skipesc
  boolean isValidNextPosition (Position newPosition) {
  	//@ set gdX = (newPosition.x - x);
    //@ set gdY = newPosition.y - y;
	  int dX = newPosition.x - x;
	  int dY = newPosition.y - y;
	  //@ assert(dX == (newPosition.x - x));
	  //@ assert(dY == (newPosition.y - y));
	  return ((dX == 0) && (dY == 1)) ||
  			((dX == -1) && (dY == 0))||
  			((dX == 1) && (dY == 0)) ||
  			((dX == 0) && (dY == -1));
	  }

  //@ skipesc
  public /*@ pure non_null @*/ String toString () {
    return "position(" + this.x + "," + this.y + ")";
  }

}
