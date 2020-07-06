package chessgame.Pieces;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import chessgame.ChessApplication;
import chessgame.IPlayer;
import chessgame.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Pawn extends APiece {
  protected PieceType type;
  protected Image sprite;

  public Pawn(IPlayer owner) {
    super(owner);
    this.type = PieceType.PAWN;
    this.tryCatchSprite();
  }

  private void tryCatchSprite() {
    // initialize data as Null
    FileInputStream inputstream = null;
    // try catch for IOException
    try {
      // if statement to determine if drawing a black or white chess piece
      if (this.owner.getIsSmoke() ) {
        inputstream = new FileInputStream("/Users/RileyGrant46/Downloads/blackPawn.png");
      } else {
        inputstream = new FileInputStream("/Users/RileyGrant46/Downloads/whitePawn.png");
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
    return "pawn";
  }

  @Override
  public ArrayList<int[]> returnLegalMoveCoords(int row, int col) {
    // initialize some basic data to act on
    ArrayList<int[]> toReturn = new ArrayList<int[]>();
    PieceUtil util = new PieceUtil();

    // set the direction that the pawn moves
    int unit = +1;
    if (this.owner.getIsSmoke()) {
      unit = -1;
    }

    Tile thisTile = ChessApplication.getBoard()[row][col];


    // logic to see if one tile in front is legal
    Tile directInFront = ChessApplication.getBoard()[row+unit][col];
    if(directInFront.hasBlankPiece() ) {
      util.addToList(row + unit, col, toReturn);
    }

    // logic to see if two tiles in front is legal
    Tile twoInFront = ChessApplication.getBoard()[row+unit][col];
    // if the piece has never moved, and two spaces are blank, add the second square ahead too
    if (! this.movedHuh && directInFront.hasBlankPiece() && twoInFront.hasBlankPiece() ) {
      util.addToList(row + (2 * unit), col, toReturn );
    }

    // logic to see if pawn can take a piece on diagonal
    Tile diag1 = ChessApplication.getBoard()[row+unit][col+1];
    if (!diag1.isFriendly(thisTile) && !diag1.hasBlankPiece()) {
      util.addToList(row+unit, col+1, toReturn);
    }

    // logic to see if pawn can take a piece on other diagonal
    Tile diag2 = ChessApplication.getBoard()[row+unit][col-1];
    if (!diag2.isFriendly(thisTile) && !diag2.hasBlankPiece()) {
      util.addToList(row+unit, col-1, toReturn);
    }

    this.addOnLine(toReturn, row, col, 1, 1);


    // return list to application to trim and avoid IOB errors
    return toReturn;
  }

  @Override
  public void drawPiece(GraphicsContext gc) {
    gc.clearRect(0, 0, 100, 100);
    gc.drawImage(this.sprite, 9, 15, 50, 55);
  }

}
