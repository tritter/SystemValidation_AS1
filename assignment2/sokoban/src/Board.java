/**
 * Represents the board of the game. For simplicity the board is always square.
 */
final class Board {

  /** @informal The board has a positive size, is a square, and all items are defined. */
  //@ public invariant xSize > 0 && ySize > 0;
	//@ public invariant xSize < Integer.MAX_VALUE && ySize < Integer.MAX_VALUE;
	//@ public invariant xSize == ySize;
	/*@ public invariant
	  @ (\forall int x; x >= 0 && x < xSize;
		@ 	(\forall int y; y >= 0 && y < ySize;
		@			items[x][y] != null));
	@*/	 
	
  /** @informal All marked locations are in the valid playing area (that is, on the ground) */
	/*@ public invariant 
  	@ (\forall int x; x >= 0 && x < xSize;
		@ 	(\forall int y; y >= 0 && y < ySize;
		@			(items[x][y].marked  && items[x][y].ground) || !items[x][y].marked));
	@*/

  /** @informal All crates are in the valid playing area */
	/*@ public invariant 
	  @ (\forall int x; x >= 0 && x < xSize;
		@ 	(\forall int y; y >= 0 && y < ySize;
		@			(items[x][y].crate  && items[x][y].ground) || !items[x][y].crate));
	@*/
	
  /*@ spec_public */int xSize; 
  /*@ spec_public */int ySize; 

  public BoardItem[][] items;

  /** @informal based on valid parameters the constructor creates an "all wall" board */
  /*@ 
  	@ requires xSize > 0 && ySize > 0;
  	@ requires xSize < Integer.MAX_VALUE && ySize < Integer.MAX_VALUE;
  	@ requires xSize == ySize;
  	@ ensures this.xSize == xSize;
  	@ ensures this.ySize == ySize;
  	@ ensures this.ySize == this.xSize;
  	@ ensures this.items.length == xSize;
  	@ ensures 
  	@ (\forall int x; x >= 0 && x < xSize;
		@ 	(\forall int y; y >= 0 && y < ySize;
		@			items[x][y] != null && !items[x][y].ground));
  @*/
  Board (int xSize, int ySize) {
    this.xSize = xSize;
    //@assert(this.xSize == xSize);
    this.ySize = ySize;
    //@assert(this.ySize == ySize);
    items = new BoardItem[xSize][ySize];
    for (int x = 0; x >= 0 && x < xSize; x++) {
      for (int y = 0; y >= 0 && y < ySize; y++) {
          items[x][y] = new BoardItem();
      }
  }
  }
  
  /** @informal auxiliary method to establish that a position is on the board */
  /*@ normal_behavior
		@ requires p != null;
		@ ensures \result == (0 <= p.x && p.x < this.xSize && 0 <= p.y && p.y < this.ySize);
	@*/
  public boolean onBoard(Position p) {
      return 0 <= p.x && p.x < xSize && 0 <= p.y && p.y < ySize;
  }

  /** @informal same as above for explicit coordinates */
  /*@ normal_behavior
		@ ensures \result == (0 <= x && x < this.xSize && 0 <= y && y < this.ySize);
	@*/
  public boolean onBoard(int x, int y) {
      return 0 <= x && x < xSize && 0 <= y && y < ySize;
  }

  /** @informal auxiliary method to establish that a position is on board and is open
   *     (the player can stand on it) */
  /*@ normal_behavior
		@ requires items != null && p.x > 0 && p.x < items.length && p.y > 0 && p.y < items[p.x].length;
		@ ensures \result == (onBoard(p) && items[p.x][p.y].ground && !items[p.x][p.y].crate);
		@ also
		@ exceptional_behavior
		@ requires p != null || items != null || p.x < 0 || p.x > items.length || p.y < 0 || p.y > items[p.x].length;
		@ signals (IndexOutOfBoundsException) p.x > items.length || p.y > items[p.x].length;
	@*/
  public boolean isOpen(/*@ non_null @*/ Position p) {
	  if(!onBoard(p)) return false;
	  return items[p.x][p.y].ground && !items[p.x][p.y].crate; 
  }

  /** @informal same as above for explicit coordinates */
  /*@ normal_behavior
    @ requires x >= 0 && y >= 0 && x < Integer.MAX_VALUE && y < Integer.MAX_VALUE;
		@ ensures \result == (onBoard(x,y) && items[x][y].ground && !items[x][y].crate);
	@*/
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
