package chessgame.Pieces;

import java.util.ArrayList;

import chessgame.IPlayer;

public interface IPiece {

  String drawOwnerAsString();

  String drawTypeAsString();

  String drawStatusAsString();

  String drawMovedHuhAsString();

  void setActive();
  void setInactive();
  void setLegal();
  void setIllegal();
  void setToMoved();

  IPlayer getPlayer();

  PieceType getPieceType();

  ArrayList<int[]> returnLegalMoveCoords(int row, int col);

}
