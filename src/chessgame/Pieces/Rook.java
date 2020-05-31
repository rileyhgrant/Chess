package chessgame.Pieces;

import java.util.ArrayList;

import chessgame.IPlayer;

public class Rook extends APiece {
  protected PieceType type;

  public Rook(IPlayer owner) {
    super(owner);
    this.type = PieceType.ROOK;
  }

  @Override
  public String drawTypeAsString() {
    return "rook";
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
    util.addToList(row-2, col+1, toReturn);
    //
    util.addToList(row+1, col+2, toReturn);
    util.addToList(row+1, col-2, toReturn);
    //
    util.addToList(row-1, col+2, toReturn);
    util.addToList(row-1, col-2, toReturn);

    //int[] pos1 = new int[2];
    //pos1[0] = row + 2;
    //pos1[1] = col + 1;
    //toReturn.add(pos1);

    //int[] pos2 = new int[2];
    //pos2[0] = row + 2;
    //pos2[1] = col - 1;
    //toReturn.add(pos2);

    return toReturn;
  }
}
