package chessgame;

import java.util.ArrayList;

import chessgame.Pieces.IPiece;
import chessgame.Pieces.PieceType;

public class Player implements IPlayer {
  String name;
  boolean isSmoke;
  ArrayList<PieceType> capturedPieces;

  public Player(String name, boolean isSmoke) {
    this.name = name;
    this.isSmoke = isSmoke;
    this.capturedPieces = new ArrayList<PieceType>();
  }

  public String returnPlayerNameString() {
    return this.name;
  }

  public void addCapturedPiece(IPiece p) {
    this.capturedPieces.add(p.getPieceType());
  }



}
