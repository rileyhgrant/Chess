package chessgame.Pieces;

import java.util.ArrayList;

import chessgame.IPlayer;
import javafx.scene.canvas.GraphicsContext;

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

  void drawPiece(GraphicsContext gc);

}
