package chessgame.Pieces;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import chessgame.IPlayer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bishop extends APiece {
  protected PieceType type;
  protected Image sprite;

  public Bishop(IPlayer owner) {
    super(owner);
    this.type = PieceType.BISHOP;
    this.tryCatchSprite();
  }

  private void tryCatchSprite() {
    // initialize data as Null
    FileInputStream inputstream = null;
    // try catch for IOException
    try {
      // if statement to determine if drawing a black or white chess piece
      if (this.owner.getIsSmoke() ) {
        inputstream = new FileInputStream("/Users/RileyGrant46/Downloads/blackBishop.png");
      } else {
        inputstream = new FileInputStream("/Users/RileyGrant46/Downloads/whiteBishop.png");
      }
      // if an exception is caught, throw error to user
    } catch (IOException e) {
      System.err.println("Caught IOException:" + e.getMessage());
    }

    // if it correctly gets an image, it will then use that
    if (inputstream != null) {
      this.sprite = new Image(inputstream);
    }

  }

  @Override
  public String drawTypeAsString() {
    return "bishop";
  }

  @Override
  public ArrayList<int[]> returnLegalMoveCoords(int row, int col) {
    ArrayList<int[]> toReturn = new ArrayList<int[]>();

    this.addOnLine(toReturn, row, col, 1, 1);
    this.addOnLine(toReturn, row, col, -1, 1);
    this.addOnLine(toReturn, row, col, 1, -1);
    this.addOnLine(toReturn, row, col, -1, -1);

    return toReturn;
  }

  @Override
  public void drawPiece(GraphicsContext gc) {
    gc.clearRect(0, 0, 100, 100);
    gc.drawImage(this.sprite, 9, 15, 50, 55);
  }
}
