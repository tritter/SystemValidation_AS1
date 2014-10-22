/**
 * Represents the board of the game. For simplicity the board is always square.
 */
final class Board {

  /** @informal The board has a positive size, is a square, and all items are defined. */
  //@ public invariant xSize > 0 && ySize > 0;
  //@ public invariant xSize == ySize;
	//@ public invariant items.length == xSize && items.length > 0;
	/*@ public invariant
  (\forall int x; x >= 0 && x < xSize;
             (items[x].length > 0 && items[x].length == ySize && items[x] != null)
  );
@*/  	
  /*@ public invariant
     (\forall int x; x >= 0 && x < items.length;
         (\forall int y; y >= 0 && y < items.length;
                items[x][y] != null
         )
     );
  @*/  
    
  /** @informal All marked locations are in the valid playing area (that is, on the ground) */
  /*@ public invariant 
        (\forall int x; x >= 0 && x < xSize;
            (\forall int y; y >= 0 && y < ySize;
               (items[x][y].marked  && items[x][y].ground) || !items[x][y].marked
            )
        );
  @*/
    
  /** @informal All crates are in the valid playing area */
  /*@ public invariant 
        (\forall int x; x >= 0 && x < xSize;
            (\forall int y; y >= 0 && y < ySize;
                (items[x][y].crate  && items[x][y].ground) || !items[x][y].crate
            )
        );
  @*/
	
  /*@ spec_public */int xSize; 
  /*@ spec_public */int ySize; 

  public BoardItem[][] items;

  /** @informal based on valid parameters the constructor creates an "all wall" board */
  /*@ requires sizeX > 0 && sizeY > 0;
    @ requires sizeX == sizeY;
    @ ensures this != null;
    @ ensures 
        (\forall int x; x >= 0 && x < sizeX;
           (\forall int y; y >= 0 && y < sizeY;
              items[x][y] != null && !items[x][y].ground
           )
        );
  @*/
//@ skipesc
  Board (int sizeX, int sizeY) {
    this.xSize = sizeX;
    this.ySize = sizeY;
    items = new BoardItem[sizeX][sizeY];
    for (int x = 0; x < sizeX; x++) {
        for (int y = 0; y < sizeY; y++) {
            items[x][y] = new BoardItem();
        }
    }
  }
  
  /** @informal auxiliary method to establish that a position is on the board */
  /*@ requires p != null;
    @ ensures \result == (0 <= p.x && p.x < this.xSize && 0 <= p.y && p.y < this.ySize);
    @*/
//@ skipesc
  public boolean onBoard(Position p) {
      return 0 <= p.x && p.x < xSize && 0 <= p.y && p.y < ySize;
  }

  /** @informal same as above for explicit coordinates */
  /*@ requires true;
    @ ensures \result == (0 <= x && x < this.xSize && 0 <= y && y < this.ySize);
    @*/
//@ skipesc
  public boolean onBoard(int x, int y) {
      return 0 <= x && x < xSize && 0 <= y && y < ySize;
  }

  /** @informal auxiliary method to establish that a position is on board and is open (the player can stand on it) */
  /*@ requires p!=null;
    @ ensures \result == ( onBoard(p) && items[p.x][p.y].ground && !items[p.x][p.y].crate );   
  @*/
//@ skipesc
  public boolean isOpen(/*@ non_null @*/ Position p) {
	  if(!onBoard(p)) return false;
	  return items[p.x][p.y].ground && !items[p.x][p.y].crate; 
  }

  /** @informal same as above for explicit coordinates */
  /*@ requires true;
    @ ensures \result == ( onBoard(x,y) && items[x][y].ground && !items[x][y].crate );   
  @*/
//@ skipesc
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
