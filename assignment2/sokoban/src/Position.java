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
  Position (int x, int y) {
    this.x = x;
    this.y = y;
  }

  /** @informal to be equal positions need to agree on both coordinates */ 
  /*@ requires true;
    @ ensures \result ==  ( o != null && (o instanceof Position) && x == ((Position)o).x && y == ((Position)o).y );
  @*/
  public boolean equals (Object o) {
    if (o instanceof Position) {
      Position q = (Position) o;
      //@ assert (q != null);
      return x == q.x && y == q.y;
    }
    return false;
  }

  /** @informal a valid next position is away one move horizontally or vertically from the current one */
  /*@ requires newPosition != null;
    @ ensures \result == ((newPosition.x - x) >= -1 && (newPosition.x - x) <= 1 && (newPosition.y - y) >= -1 && (newPosition.y - y) <= 1);
  @*/
  boolean isValidNextPosition (Position newPosition) {
	  int dX = newPosition.x - x;
	  int dY = newPosition.y - y;
	  //@ assert(dX == (newPosition.x - x));
	  //@ assert(dY == (newPosition.y - y));
	  if( dX >= -1 && dX <= 1 && dY >= -1 && dY <= 1) return true;
	  return false;
	  }

  //@ skipesc
  public /*@ pure non_null @*/ String toString () {
    return "position(" + this.x + "," + this.y + ")";
  }

}
