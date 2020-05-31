package chessgame.Pieces;

import chessgame.IPlayer;
import chessgame.Player;

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

}
