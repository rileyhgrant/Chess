package chessgame.Pieces;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import chessgame.IPlayer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Blank extends APiece {
  protected PieceType type;
  protected Image sprite;

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

  @Override
  public void drawPiece(GraphicsContext gc) {
    gc.clearRect(0, 0, 100, 100);
  }
}
