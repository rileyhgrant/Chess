package chessgame.Pieces;

import java.util.ArrayList;

import chessgame.IPlayer;

public class Blank extends APiece {
  protected PieceType type;

  public Blank(IPlayer owner) {
    super(owner);
    this.type = PieceType.BLANK;
  }


  @Override
  public String drawTypeAsString() {
    return "blank";
  }

  @Override
  public ArrayList<int[]> returnLegalMoveCoords(int row, int col) {
    ArrayList<int[]> toReturn = new ArrayList<int[]>();

    //TODO this is currently just for debug, bare bones right now
    return toReturn;
  }
}
