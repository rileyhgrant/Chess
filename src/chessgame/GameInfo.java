package chessgame;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;

/** A class to represent the box below the game pane
 *  Includes relevant info about the game, who captured what, who's turn
 *  Maybe current score?
 *
 *  Right now mainly used for debugging tbh
 *
 */
public class GameInfo extends StackPane {
  private ChessApplication game;
  private Text text;
  private Color color;

  GameInfo(ChessApplication game) {
    this.game = game;
    this.text = new Text();
    this.color = Color.LIGHTBLUE;

    Rectangle border = new Rectangle(600, 90);
    border.setFill(this.color);
    border.setStroke(Color.BLACK);

    setAlignment(Pos.CENTER);
    getChildren().addAll(border, text);
  }

  public void setText(String newText) {
    this.text.setText(newText);
  }
}
