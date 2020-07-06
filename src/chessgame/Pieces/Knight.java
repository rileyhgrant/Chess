package chessgame.Pieces;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import chessgame.IPlayer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Knight extends APiece {
  protected PieceType type;
  protected Image sprite;

  public Knight(IPlayer owner) {
    super(owner);
    this.type = PieceType.KNIGHT;
    this.tryCatchSprite();
  }

  private void tryCatchSprite() {
    // initialize data as N ull
    FileInputStream inputstream = null;
    // try catch for IOException
    try {
      // if statement to determine if drawing a black or white chess piece
      if (this.owner.getIsSmoke() ) {
        inputstream = new FileInputStream("/Users/RileyGrant46/Downloads/blackKnight.png");
      } else {
        inputstream = new FileInputStream("/Users/RileyGrant46/Downloads/whiteKnight.png");
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

  @Override
  public void drawPiece(GraphicsContext gc) {
    gc.clearRect(0, 0, 100, 100);
    gc.drawImage(this.sprite, 9, 15, 50, 55);
  }

}
