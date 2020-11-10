import java.awt.*;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Driver {

    public static void main(String[] args) {


        Board board = new Board(100);
        Piece piece = new Piece(board);
        Window window = new Window((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight(), board, piece);
        makeMoves(board, piece);
    }

    public static void makeMoves(Board board, Piece piece) {
        int[] checkedNumbers = new int[piece.validXs.length];
        int lowestIndex = -1;
        piece.visitedSquares.add(board.square[piece.x][piece.y]);
        boolean ableToAdvance = false;
        for(int i = 0; i < checkedNumbers.length; i++) {
            if(!piece.visitedSquares.contains(board.square[piece.x + piece.validXs[i]][piece.y + piece.validYs[i]])){
                checkedNumbers[i] = board.square[piece.x + piece.validXs[i]][piece.y + piece.validYs[i]];
                ableToAdvance = true;
            }

        }
        if(!ableToAdvance) {
            System.out.println("piece was trapped at square " + board.square[piece.x][piece.y]);
            return;
        }

        lowestIndex = 0;
        for(int i = 0; i < checkedNumbers.length; i++) {
            if(checkedNumbers[i] < checkedNumbers[lowestIndex] &&
                    !piece.visitedSquares.contains(board.square[piece.x + piece.validXs[i]][piece.y + piece.validYs[i]]))
                lowestIndex = i;
        }
        piece.x += piece.validXs[lowestIndex];
        piece.y += piece.validYs[lowestIndex];
        piece.visitedSquares.add(board.square[piece.x][piece.y]);

        System.out.println("piece currently at square " + board.square[piece.x][piece.y]);

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        makeMoves(board, piece);

    }

}
