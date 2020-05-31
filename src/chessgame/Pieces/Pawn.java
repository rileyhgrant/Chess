package chessgame.Pieces;

import java.util.ArrayList;

import chessgame.IPlayer;

public class Pawn extends APiece {
  protected PieceType type;

  public Pawn(IPlayer owner) {
    super(owner);
    this.type = PieceType.PAWN;
  }



  @Override
  public String drawTypeAsString() {
    return "pawn";
  }

  @Override
  public ArrayList<int[]> returnLegalMoveCoords(int row, int col) {
    ArrayList<int[]> toReturn = new ArrayList<int[]>();

    //TODO this is currently just for debug, bare bones right now

    int[] pos1 = new int[2];
    pos1[0] = row + 1;
    pos1[1] = col;
    toReturn.add(pos1);

    if (! this.movedHuh) {
      int[] pos2 = new int[2];
      pos2[0] = row + 2;
      pos2[1] = col;
      toReturn.add(pos2);
    }

    return toReturn;
  }
}
