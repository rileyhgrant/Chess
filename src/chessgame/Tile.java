package chessgame;

import java.util.ArrayList;

import chessgame.Pieces.Blank;
import chessgame.Pieces.IPiece;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Tile extends StackPane {
  private ChessApplication game;
  private Text text = new Text();
  private IPiece piece;
  private int row;
  private int col;
  private Color color;

  public Tile(int row, int col, Color color, ChessApplication game) {
    this.row = row;
    this.col = col;
    this.color = color;
    this.game = game;
    //
    Rectangle border = new Rectangle(75, 75);
    border.setFill(this.color);
    border.setStroke(Color.BLACK);
    //
    setAlignment(Pos.CENTER);
    getChildren().addAll(border, text);

    setOnMouseClicked(e -> {
      this.tileGotClicked();
    });
  }

  private void tileGotClicked() {

    //this.piece.setActive();
    this.game.setActiveTile(this);

    // this.text.setText("clicked");
    // this is kinda janky but like, it can work
    this.drawAll();
    // this will draw this as the new active one, maybe just have a board draw all method?
    //this.draw();

    System.out.println(this.game.board[this.row][this.col]);
  }

  public void drawAll() {
    System.out.println("ran drawAll()");
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        this.game.board[i][j].draw();
      }
    }
  }


  protected void draw() {
    String typeText = Integer.toString(this.row)
            + ", " + Integer.toString(this.col) + "\n";

    if (this.piece.drawTypeAsString().equals("blank")) {
      typeText = typeText.concat("\n");
    } else {
      typeText = typeText.concat(this.piece.drawTypeAsString());
      typeText = typeText.concat("\n");
      typeText = typeText.concat(this.piece.drawOwnerAsString());
    }

    typeText = typeText.concat("\n");

    if (!this.piece.drawStatusAsString().equals("nothing")) {
      typeText = typeText.concat(this.piece.drawStatusAsString());
    }

    typeText = typeText.concat("\n");

    if (!this.piece.drawMovedHuhAsString().equals("no moved")) {
      typeText = typeText.concat(this.piece.drawMovedHuhAsString());
    }


    this.text.setText(typeText);
  }

  protected void update() {
    this.text.setText("updated");
  }

  protected void setPiece(IPiece piece) {
    this.piece = piece;
  }

  public int getRow() {
    return this.row;
  }

  public int getCol() {
    return this.col;
  }

  public IPiece getPiece() {
    return this.piece;
  }

  public void setPieceInTileToActive() {
    this.piece.setActive();
  }

  public void setPieceInTileToInactive() {
    this.piece.setInactive();
  }


  // ArrayList<Tile> getLegalMoves() {
  ArrayList<int[]> getLegalMoves() {
    ArrayList<Tile> toReturn = new ArrayList<Tile>();

    // handoff to piece in the tile, piece will return a list of coordinates that are legal
    ArrayList<int[]> legals = this.piece.returnLegalMoveCoords(this.row, this.col);

    for (int[] aLegalCoord : legals) {
      //TODO
      //  x : check if any are out of bounds and squash
      //  o : check if its another (friendly) tile and remove
      //  o : check if its another (enemy tile) and add to kill-list

      if (aLegalCoord[0] < 0 || aLegalCoord[0] > 7 || aLegalCoord[1] < 0 || aLegalCoord[1] > 7) {
        ;
      } else {
        toReturn.add(this.game.getBoard()[aLegalCoord[0]][aLegalCoord[1]]);
      }

    }

    //TODO
    // we then trim this list based on if any are filled, or if any are out of bounds

    // will this also be todo?

    // return toReturn;
    return legals;
  }

  public void setPieceInTileToLegal() {
    this.piece.setLegal();
  }

  public void setPieceInTileToIllegal() {
    this.piece.setIllegal();
  }

  public void swapPiece(Tile givenTile) {
    IPiece oldPiece = givenTile.getPiece();
    oldPiece.setIllegal();

    givenTile.setPiece(this.getPiece());
    givenTile.setPieceInTileToIllegal();

    this.setPiece(oldPiece);
  }

  public void takePiece(Tile givenTile) {
    IPlayer attacker = this.piece.getPlayer();
    IPlayer defender = givenTile.getPiece().getPlayer();
    // fuck me
    IPiece pieceToDie = givenTile.getPiece();
    attacker.addCapturedPiece(pieceToDie);

    // saved all data from the piece, initialize as a new blank
    givenTile.setPiece(new Blank(defender));
    // now just swap attacker and defender
    this.swapPiece(givenTile);

  }

  public boolean isFriendly(Tile that) {
    return this.piece.drawOwnerAsString().equals(that.piece.drawOwnerAsString());
  }

  public void setPieceInTileToMoved() {
    this.piece.setToMoved();
  }


}