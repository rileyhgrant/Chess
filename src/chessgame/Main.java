package chessgame;

public class Main {

  public static void main(String[] args) {
    System.out.println("it ran!");

    IPlayer player1 = new Player("Riley", true);
    IPlayer player2 = new Player("Fiona", false);

    ChessApplication app = new ChessApplication();
    app.run(player1, player2);
  }
}
