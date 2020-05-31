package chessgame;

import java.util.ArrayList;

import chessgame.Pieces.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ChessApplication extends Application {
  static Tile[][] board = new Tile[8][8];
  static IPlayer player1;
  static IPlayer player2;
  Tile activeTile;
  Tile lastActiveTile;
  ArrayList<Tile> legalTiles = new ArrayList<Tile>();
  boolean player1TurnHuh;


  public void run(IPlayer p1, IPlayer p2) {
    ChessApplication.player1 = p1;
    ChessApplication.player2 = p2;
    // Calls the javaFX static method to launch the application
    launch();
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    this.player1TurnHuh = true;
    primaryStage.setTitle("Chess v2");
    primaryStage.setScene(new Scene(createContent() ) );
    primaryStage.show();
  }

  private Parent createContent() {
    Pane root = new Pane();
    root.setPrefSize(650, 800);
    // this draws the checkered board
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        // color logic for checkerboard pattern
        Color color = Color.LIGHTGREY;
        if ( (j + i) % 2 == 0) {
          color = Color.WHITE;
        }
        Tile tile = new Tile(i, j, color, this);
        tile.setTranslateX(j * 75 + 25);
        tile.setTranslateY(i * 75 + 25);
        this.board[i][j] = tile;

        // call helper to put pieces on the whole board


        //
        root.getChildren().add(tile);
      }
    }
    this.initializePieces();
    this.drawAll();
    return root;
  }

  private void drawAll() {
    ChessApplication.board[0][0].drawAll();
  }


  // INITIALIZING THE BOARD SHIZ
  // ========================================
  private void initializePieces() {
    // TODO: 2020-05-30 MAKE THIS MORE PROPER
    this.initializeBlanks();
    this.initializePawns();
    // back-row
    this.initializeRooks();
    this.initializeKnights();
    this.initializeBishops();
    this.initializeRoyals();
  }

  private void initializeBlanks() {
    IPlayer none = new Player("none", false);
    for (int i = 0; i < 8; i++) {
      board[2][i].setPiece(new Blank(none));
      board[3][i].setPiece(new Blank(none));
      board[4][i].setPiece(new Blank(none));
      board[5][i].setPiece(new Blank(none));
    }
  }

  private void initializePawns() {
    for (int i = 0; i < 8; i++) {
      board[1][i].setPiece(new Pawn(ChessApplication.player2));
      board[6][i].setPiece(new Pawn(ChessApplication.player1));
    }
  }

  private void initializeRooks() {
    board[0][0].setPiece(new Rook(ChessApplication.player2));
    board[0][7].setPiece(new Rook(ChessApplication.player2));
    board[7][0].setPiece(new Rook(ChessApplication.player1));
    board[7][7].setPiece(new Rook(ChessApplication.player1));
  }

  private void initializeKnights() {
    board[0][1].setPiece(new Knight(ChessApplication.player2));
    board[0][6].setPiece(new Knight(ChessApplication.player2));
    board[7][1].setPiece(new Knight(ChessApplication.player1));
    board[7][6].setPiece(new Knight(ChessApplication.player1));
  }

  private void initializeBishops() {
    board[0][2].setPiece(new Bishop(ChessApplication.player2));
    board[0][5].setPiece(new Bishop(ChessApplication.player2));
    board[7][2].setPiece(new Bishop(ChessApplication.player1));
    board[7][5].setPiece(new Bishop(ChessApplication.player1));
  }

  private void initializeRoyals() {
    board[0][4].setPiece(new Queen(ChessApplication.player2));
    board[7][4].setPiece(new Queen(ChessApplication.player1));
    board[0][3].setPiece(new King(ChessApplication.player2));
    board[7][3].setPiece(new King(ChessApplication.player1));
  }

  // ========================================



  public void setActiveTile(Tile t) {

    Tile old = this.activeTile;
    // if there was already a last active piece, wipe it
    if (old != null) {
      old.setPieceInTileToInactive();
      this.lastActiveTile = old;
    }

    // if any of these trigger, there is already a last active because
    //     legal tiles has data in it.
    if (this.legalTiles.contains(t) && !t.isFriendly(old)) {
      // if statement to take a piece if possible
      //   takes the piece and ends the turn
      this.lastActiveTile.takePiece(t);
      t.setPieceInTileToMoved();
      this.wipeLegalTiles();
      this.flipTurn();
    } else if (this.legalTiles.contains(t)) {
      // if statement to make a legal move
      //   makes a move, and ends the turn
      t.swapPiece(this.lastActiveTile);
      t.setPieceInTileToMoved();
      this.wipeLegalTiles();
      this.flipTurn();
    } else {
      // else statement, new tile selected as active, show possible legal moves
      this.wipeLegalTiles();
      this.activeTile = t;
      t.setPieceInTileToActive();
      // get raw data from tile's held piece
      ArrayList<int[]> rawLegalCoords = t.getLegalMoves();
      // pass to helper to process data, then set data
      ArrayList<Tile> newLegalTiles = this.processRawLegalCoordsToTiles(rawLegalCoords);
      this.legalTiles = newLegalTiles;
    }
    // after editing data, redraw all new data
    this.drawAll();
  }


  private ArrayList<Tile> processRawLegalCoordsToTiles(ArrayList<int[]> legalCoords) {

    ArrayList<Tile> toReturn = new ArrayList<Tile>();

    for (int[] aLCoord : legalCoords) {
      boolean passedAllChecks = true;
      //TODO
      //  o : check if its another (friendly) tile and remove
      //  o : check if its another (enemy tile) and add to kill-list

      // if to check if the thing is within bounds
      if (aLCoord[0] < 0 || aLCoord[0] > 7 || aLCoord[1] < 0 || aLCoord[1] > 7) {
        ;
      } else {
        Tile thisTile = ChessApplication.board[aLCoord[0]][aLCoord[1]];
        // check if this piece is occupied by a friendly tile
        if (!thisTile.getPiece().drawTypeAsString().equals("blank")
            && thisTile.isFriendly(this.activeTile)) {
          passedAllChecks = false;
        }
        // somehow need to check if the piece is fricking blocked off??
        if(passedAllChecks) {
          toReturn.add(thisTile);
          thisTile.setPieceInTileToLegal();

        }
      }
    }
    return toReturn;
  }

  private void flipTurn() {
    if (this.player1TurnHuh) {
      this.player1TurnHuh = false;
    } else {
      this.player1TurnHuh = true;
    }
  }


  public Tile[][] getBoard() {
    return board;
  }

  private void wipeLegalTiles() {
    for (Tile lTile : this.legalTiles) {
      lTile.setPieceInTileToIllegal();
    }
    this.legalTiles.clear();
  }


}
