/**
 * Represents a player. Technically, a player could be a board item, but we keep 
 * her/him separate. 
 */
final class Player
{

  /** @informal a player always has a position */
  public Position position;

  /** @informal based on valid parameters the constructor creates a valid player object */
  Player (Position position) {
    this.position = position;
  }


  /** @informal a player can only change position to a valid new position */
  public void setPosition (Position newPosition) {
	    this.position = newPosition;
  }

  //@ skipesc
  public /*@ pure non_null @*/ String toString () {
    return "Player(" + position.x + "," + position.y + ")";
  }

}
