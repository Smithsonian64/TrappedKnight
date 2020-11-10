import java.util.Vector;

public class Piece {
    int x;
    int y;

    int stuckNumber;

    int[] validXs;
    int[] validYs;

    Vector<Integer> visitedSquares;


    Piece(Board board) {
        validXs = new int[]{-2, -1, 1, 2, 2, 1, -1, -2};
        validYs = new int[]{1, 2, 2, 1, -1, -2, -2, -1};

        visitedSquares = new Vector<>();

        x = board.startX;
        y = board.startY;
    }



}
