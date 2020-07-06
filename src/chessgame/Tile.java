package chessgame;

import java.awt.*;
import java.util.ArrayList;

import chessgame.Pieces.Blank;
import chessgame.Pieces.IPiece;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class Tile extends StackPane {
  private ChessApplication game;
  private Text text = new Text();
  private Canvas canvas;
  private GraphicsContext gc;
  private IPiece piece;
  private int row;
  private int col;
  private Color color;


  public Tile(int row, int col, Color color, ChessApplication game) {
    this.row = row;
    this.col = col;
    this.color = color;
    this.game = game;
    this.canvas = new Canvas(80, 80);
    this.gc = canvas.getGraphicsContext2D();
    //this.drawShapes(gc);
    //
    Rectangle border = new Rectangle(75, 75);
    border.setFill(this.color);
    border.setStroke(Color.BLACK);
    //
    setAlignment(Pos.CENTER);
    getChildren().addAll(border, canvas, text);

    setOnMouseClicked(e -> {
      this.tileGotClicked();
    });
  }

  private void drawShapes(GraphicsContext gc) {
    // TODO: 2020-05-31 :
    //    PLAN: have each type of piece store an image
    //          just call and return the image, then you
    //          just set the image woo
    //this.piece.drawShape(gc);

    gc.setFill(Color.LIGHTCORAL);
    gc.fillOval(25, 25, 30, 30);
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

    if (this.piece != null) {
      this.piece.drawPiece(this.gc);
    }

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


  ArrayList<int[]> getLegalMoves() {
    ArrayList<Tile> toReturn = new ArrayList<Tile>();

    // handoff to piece in the tile, piece will return a list of coordinates that are legal
    ArrayList<int[]> legals = this.piece.returnLegalMoveCoords(this.row, this.col);
    // return this to the ChessApplication to use more logic to trim the list there.
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



  public boolean belongsToCurrentTurn() {
    // boolean for which turn it is
    boolean p1TurnHuh = this.game.getP1TurnHuh();
    // initialize boolean assuming this piece is p2
    boolean thisIsP1 = false;
    // do an if to check if this piece is p1
    if (this.piece.getPlayer().getIsSmoke()) {
      thisIsP1 = true;
    }

    // return true if its p1s turn, and p1s piece, or vice versa
    //   false otherwise
    return (thisIsP1 == p1TurnHuh);

  }



  public boolean hasBlankPiece() {
    boolean toReturn;
    toReturn = this.piece.drawTypeAsString().equals("blank");
    return toReturn;
  }


}
