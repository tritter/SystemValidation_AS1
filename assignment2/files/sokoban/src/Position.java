/**
 * Stores coordinates of the position on the board.
 */
final class Position
{

  /** @informal only allow positive positions */
  final int x;
  final int y;

  /** @informal based on valid parameters the constructor creates a valid position object */
  Position (int x, int y) {
    this.x = x;
    this.y = y;
  }

  /** @informal to be equal positions need to agree on both coordinates */ 
  public boolean equals (Object o) {
    if (o instanceof Position) {
      Position q = (Position) o;
      return x == q.x && y == q.y;
    }
    return false;
  }

  /** @informal a valid next position is away one move horizontally
   *    or vertically from the current one */
  boolean isValidNextPosition (Position newPosition) {
	  int dX = newPosition.x - x;
	  int dY = newPosition.y - y;
	  if( dX >= -1 && dX <= 1 && dY >= -1 && dY <= 1) return true;
	  return false;
	  }

  //@ skipesc
  public /*@ pure non_null @*/ String toString () {
    return "position(" + this.x + "," + this.y + ")";
  }

}
