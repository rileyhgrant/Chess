package chessgame.Pieces;

import java.util.ArrayList;

public class PieceUtil {

  PieceUtil() {

  }

  protected void addToList(int row, int col, ArrayList<int[]> givenList) {
    int[] toAdd = new int[2];
    toAdd[0] = row;
    toAdd[1] = col;
    givenList.add(toAdd);
  }

}
