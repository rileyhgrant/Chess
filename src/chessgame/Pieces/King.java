package chessgame.Pieces;

import java.util.ArrayList;

import chessgame.IPlayer;

public class King extends APiece {
  protected PieceType type;

  public King(IPlayer owner) {
    super(owner);
    this.type = PieceType.KING;
  }

  @Override
  public String drawTypeAsString() {
    return "king";
  }

  @Override
  public ArrayList<int[]> returnLegalMoveCoords(int row, int col) {
    ArrayList<int[]> toReturn = new ArrayList<int[]>();

    //TODO this is currently just for debug, bare bones right now

    int[] pos1 = new int[2];
    pos1[0] = row + 2;
    pos1[1] = col + 1;
    toReturn.add(pos1);

    int[] pos2 = new int[2];
    pos2[0] = row + 2;
    pos2[1] = col - 1;
    toReturn.add(pos2);

    return toReturn;
  }
}
