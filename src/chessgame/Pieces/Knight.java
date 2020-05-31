package chessgame.Pieces;

import java.util.ArrayList;

import chessgame.IPlayer;

public class Knight extends APiece {
  protected PieceType type;

  public Knight(IPlayer owner) {
    super(owner);
    this.type = PieceType.KNIGHT;
  }

  @Override
  public String drawTypeAsString() {
    return "knight";
  }

  @Override
  public ArrayList<int[]> returnLegalMoveCoords(int row, int col) {
    ArrayList<int[]> toReturn = new ArrayList<int[]>();
    PieceUtil util = new PieceUtil();

    //TODO this is currently just for debug, bare bones right now
    //
    util.addToList(row+2, col+1, toReturn);
    util.addToList(row+2, col-1, toReturn);
    //
    util.addToList(row-2, col+1, toReturn);
    util.addToList(row-2, col-1, toReturn);
    //
    util.addToList(row+1, col+2, toReturn);
    util.addToList(row+1, col-2, toReturn);
    //
    util.addToList(row-1, col+2, toReturn);
    util.addToList(row-1, col-2, toReturn);

    return toReturn;
  }
}
