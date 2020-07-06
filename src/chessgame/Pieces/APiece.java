package chessgame.Pieces;

import java.util.ArrayList;

import chessgame.ChessApplication;
import chessgame.IPlayer;
import chessgame.Player;
import chessgame.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class APiece implements IPiece {
  protected boolean movedHuh;
  protected boolean activeHuh;
  protected boolean legalHuh;

  protected PieceType type;
  protected IPlayer owner;

  APiece(IPlayer owner) {
    this.movedHuh = false;
    this.activeHuh = false;
    this.legalHuh = false;
    this.owner = owner;
  }

  @Override
  public String drawOwnerAsString() {
    return this.owner.returnPlayerNameString();
  }

  @Override
  public String drawStatusAsString() {
    if (this.activeHuh) {
      return "active";
    } else if (legalHuh) {
      return "legal";
    } else {
      return "nothing";
    }
  }

  @Override
  public String drawMovedHuhAsString() {
    if (movedHuh) {
      return "has moved";
    }
    return "no moved";
  }

  @Override
  public void setActive() {
    this.activeHuh = true;
  }

  @Override
  public void setInactive() {
    this.activeHuh = false;
  }

  @Override
  public void setLegal() {
    this.legalHuh = true;
  }

  @Override
  public void setIllegal() {
    this.legalHuh = false;
  }

  @Override
  public void setToMoved() {
    this.movedHuh = true;
  }

  @Override
  public IPlayer getPlayer() {
    return this.owner;
  }

  @Override
  public PieceType getPieceType() {
    return this.type;
  }

  @Override
  public void drawPiece(GraphicsContext gc) {
    gc.setFill(Color.LAVENDER);
    gc.fillOval(25, 25, 30, 30);
  }

  //
  public void addOnLine(ArrayList<int[]> addTo, int row, int col, int xUnit, int yUnit) {
    // use util
    PieceUtil util = new PieceUtil();
    // loop through all possible values in line
    for (int i = 1; i < 9; i++) {
      // save new coords to check IOB
      int newRow = row + (i * xUnit);
      int newCol = col + (i * yUnit);
      // if any of these cause IOB, just terminate right away
      if (newRow < 0 || newRow > 7 || newCol < 0 || newCol > 7) {
        break;
      //otherwise save the piece and do logic
      } else {
        Tile check = ChessApplication.getBoard()[newRow][newCol];
        // if piece is an enemy, add to list and stop loop
        if (!check.hasBlankPiece() && !check.isFriendly(ChessApplication.getBoard()[row][col]) ) {
          util.addToList(newRow, newCol, addTo);
          break;
        // if piece is occupied, end loop without adding piece
        } else if (!check.hasBlankPiece() ) {
          break;
        } else {
          // otherwise, call helper to add to list and continue looping
          util.addToList(newRow, newCol, addTo);
        }
      }
    }
  }



}
