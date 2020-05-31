package chessgame;

import chessgame.Pieces.IPiece;

public interface IPlayer {

  String returnPlayerNameString();

  void addCapturedPiece(IPiece p);
}
