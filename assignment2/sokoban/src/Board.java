/**
 * Represents the board of the game. For simplicity the board is always square.
 */
final class Board {

  /** @informal The board has a positive size, is a square, and all items are defined. */
  /** @informal All marked locations are in the valid playing area (that is, on the ground) */
  /** @informal All crates are in the valid playing area */
	
	/*@ spec_public @*/int xSize; 
	//@ public invariant xSize > 0 && xSize == ySize;
	  
		/*@ spec_public @*/int ySize; 
	//@ public invariant ySize > 0 && xSize == ySize;
	  
	  public BoardItem[][] items;
		//@ public invariant items.length == xSize &&  items.length == ySize;
	  /*@ public invariant 
	  @  (\forall int i; 0 <= i && i < items.length ==> 
	  @    (items[i].length == items.length));
	  @*/
  
  /** @informal based on valid parameters the constructor creates an "all wall" board */
	  /*@ normal_behavior
      @   requires  xSize > 0 && ySize > 0 ;
      @   ensures  true;
      @ also
      @ exceptional_behavior
      @   requires  xSize <= 0 || ySize <= 0;
      @   signals_only NegativeArraySizeException;
      @   signals  (NegativeArraySizeException) true;
      @*/
  Board (int xSize, int ySize) {
    this.xSize = xSize;
    this.ySize = ySize;
    items = new BoardItem[xSize][ySize];
    for (int x = 0; x < xSize; x++) {
        for (int y = 0; y < ySize; y++) {
            items[x][y] = new BoardItem();
        }
    }
  }
  
  /** @informal auxiliary method to establish that a position is on the board */
  /*@ public normal_behavior
  @   ensures  \result == (0 <= p.x && p.x < xSize && 0 <= p.y && p.y < ySize);
  @*/
  public boolean onBoard(Position p) {
      return 0 <= p.x && p.x < xSize && 0 <= p.y && p.y < ySize;
  }

  /** @informal same as above for explicit coordinates */
  /*@ public normal_behavior
  @   ensures  \result == (0 <= x && x < xSize && 0 <= y && y < ySize);
  @*/
  public boolean onBoard(int x, int y) {
      return 0 <= x && x < xSize && 0 <= y && y < ySize;
  }

  /** @informal auxiliary method to establish that a position is on board and is open
   *     (the player can stand on it) */
  public boolean isOpen(/*@ non_null @*/Position p) {
	  if(!onBoard(p)) return false;
	  return items[p.x][p.y].ground && !items[p.x][p.y].crate; 
  }

  /** @informal same as above for explicit coordinates */
  public boolean isOpen(int x, int y) {
	  if(!onBoard(x, y)) return false;
	  return items[x][y].ground && !items[x][y].crate;   
  }

  //@ skipesc
  public /*@ pure non_null @*/ String toString () {
	String r = "";
	for(int y=0; y<ySize; y++) {
	    for(int x=0; x<xSize; x++)
			r += items[x][y];
		r += "\n";
	}
    return r;
  }
  
}
