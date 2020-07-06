package chessgame.Pieces;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import chessgame.ChessApplication;
import chessgame.IPlayer;
import chessgame.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class King extends APiece {
  protected PieceType type;
  protected Image sprite;

  public King(IPlayer owner) {
    super(owner);
    this.type = PieceType.KING;
    this.tryCatchSprite();
  }

  private void tryCatchSprite() {
    // initialize data as Null
    FileInputStream inputstream = null;
    // try catch for IOException
    try {
      // if statement to determine if drawing a black or white chess piece
      if (this.owner.getIsSmoke() ) {
        inputstream = new FileInputStream("/Users/RileyGrant46/Downloads/blackKing.png");
      } else {
        inputstream = new FileInputStream("/Users/RileyGrant46/Downloads/whiteKing.png");
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
    return "king";
  }

  @Override
  public ArrayList<int[]> returnLegalMoveCoords(int row, int col) {
    ArrayList<int[]> toReturn = new ArrayList<>();

    this.tryAdd(toReturn, row, col, row-1, col-1);
    this.tryAdd(toReturn, row, col, row-1, col+0);
    this.tryAdd(toReturn, row, col, row-1, col+1);
    this.tryAdd(toReturn, row, col, row+0, col-1);
    this.tryAdd(toReturn, row, col, row+0, col+1);
    this.tryAdd(toReturn, row, col, row+1, col-1);
    this.tryAdd(toReturn, row, col, row+1, col+0);
    this.tryAdd(toReturn, row, col, row+1, col+1);

    return toReturn;
  }

  private void tryAdd(ArrayList<int[]> addTo, int row, int col, int nRow, int nCol) {
    PieceUtil util = new PieceUtil();
    if (nRow < 0 || nRow > 7 || nCol < 0 || nCol > 7) {
      return;
    } else {
      Tile check = ChessApplication.getBoard()[nRow][nCol];
      if (!check.hasBlankPiece() && !check.isFriendly(ChessApplication.getBoard()[row][col])) {
        util.addToList(nRow, nCol, addTo);
      } else if (!check.hasBlankPiece() ) {
        return;
      } else {
        util.addToList(nRow, nCol, addTo);
      }
    }
  }

  @Override
  public void drawPiece(GraphicsContext gc) {
    gc.clearRect(0, 0, 100, 100);
    gc.drawImage(this.sprite, 9, 15, 50, 55);
  }

}
