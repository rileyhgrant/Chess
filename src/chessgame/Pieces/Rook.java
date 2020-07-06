package chessgame.Pieces;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import chessgame.IPlayer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Rook extends APiece {
  protected PieceType type;
  protected Image sprite;

  public Rook(IPlayer owner) {
    super(owner);
    this.type = PieceType.ROOK;
    this.tryCatchSprite();
  }

  private void tryCatchSprite() {
    // initialize data as Null
    FileInputStream inputstream = null;
    // try catch for IOException
    try {
      // if statement to determine if drawing a black or white chess piece
      if (this.owner.getIsSmoke() ) {
        inputstream = new FileInputStream("/Users/RileyGrant46/Downloads/blackRook.png");
      } else {
        inputstream = new FileInputStream("/Users/RileyGrant46/Downloads/whiteRook.png");
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
    return "rook";
  }

  @Override
  public ArrayList<int[]> returnLegalMoveCoords(int row, int col) {
    ArrayList<int[]> toReturn = new ArrayList<int[]>();
    PieceUtil util = new PieceUtil();


    this.addOnLine(toReturn, row, col, 1, 0);
    this.addOnLine(toReturn, row, col, -1, 0);
    this.addOnLine(toReturn, row, col, 0, 1);
    this.addOnLine(toReturn, row, col, 0, -1);


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

  @Override
  public void drawPiece(GraphicsContext gc) {
    gc.clearRect(0, 0, 100, 100);
    gc.drawImage(this.sprite, 14, 15, 50, 55);
  }

}
