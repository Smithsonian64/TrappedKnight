import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.Vector;

public class Piece {
    int x;
    int y;

    int stuckNumber;

    int[] validXs;
    int[] validYs;

    Vector<Integer> visitedSquares;

    Board board;

    String type;

    Piece(Board board, String type) {
        this.board = board;

        this.type = type;

        if(type.equals("knight")) {
            validXs = new int[]{-2, -1, 1, 2, 2, 1, -1, -2};
            validYs = new int[]{1, 2, 2, 1, -1, -2, -2, -1};
        } else if (type.equals("random")) {
            validXs = new int[]{(int)Math.round(Math.random() * 20 - 10),
                                (int)Math.round(Math.random() * 20 - 10),
                                (int)Math.round(Math.random() * 20 - 10),
                                (int)Math.round(Math.random() * 20 - 10),
                                (int)Math.round(Math.random() * 20 - 10),
                                (int)Math.round(Math.random() * 20 - 10),
                                (int)Math.round(Math.random() * 20 - 10),
                                (int)Math.round(Math.random() * 20 - 10)};

            validYs = new int[]{(int)Math.round(Math.random() * 20 - 10),
                                (int)Math.round(Math.random() * 20 - 10),
                                (int)Math.round(Math.random() * 20 - 10),
                                (int)Math.round(Math.random() * 20 - 10),
                                (int)Math.round(Math.random() * 20 - 10),
                                (int)Math.round(Math.random() * 20 - 10),
                                (int)Math.round(Math.random() * 20 - 10),
                                (int)Math.round(Math.random() * 20 - 10)};
        } else if (type.equals("megaKnight")) {
            validXs = new int[]{-1, -4, 5, -6, -2, -4, 1, -8};
            validYs = new int[]{7, -10, -7, 9, 4, 1, 2, 1};
        } else if (type.equals("iterate")) {
            validXs = new int[]{0,0,0,0};
            validYs = new int[]{0,0,0,0};
        }

        visitedSquares = new Vector<>();

        x = board.startX;
        y = board.startY;

        this.visitedSquares.add(board.square[this.x][this.y]);



    }

    public boolean makeMoves() {
        int[] checkedNumbers = new int[this.validXs.length];
        int lowestIndex = -1;



        boolean ableToAdvance = false;
        try {
            for (int i = 0; i < checkedNumbers.length; i++) {

                checkedNumbers[i] = board.square[this.x + this.validXs[i]][this.y + this.validYs[i]];

            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Board not big enough");
            System.out.println("Xs: " + Arrays.toString(validXs));
            System.out.println("Ys: " + Arrays.toString(validYs));
            System.out.println("\n\n");
            return false;
        }

        for(int i = 0; i < checkedNumbers.length; i++) {
            if(!visitedSquares.contains(checkedNumbers[i])) {
                lowestIndex = i;
                break;
            }
        }

        if(lowestIndex == -1) {
            System.out.println("piece was trapped at square " + board.square[this.x][this.y]);
            System.out.println("Board size: " + board.square.length);
            System.out.println("piece used movement rules: ");
            System.out.println("Xs: " + Arrays.toString(validXs));
            System.out.println("Ys: " + Arrays.toString(validYs));
            System.out.println("\n\n");

            Window.drawPanel.drawStop(x, y);
            return true;
        }

        for(int i = 0; i < checkedNumbers.length; i++) {

            //System.out.println("checking number: " + checkedNumbers[i]);
            //System.out.println("visited squares: " + visitedSquares.toString());
            //System.out.println(this.visitedSquares.contains(checkedNumbers[i]) + "\n");

            if(checkedNumbers[i] <= checkedNumbers[lowestIndex]) {
                if(!this.visitedSquares.contains(checkedNumbers[i])) {
                    lowestIndex = i;
                    ableToAdvance = true;
                }

            }

        }
        //System.out.println();

        if(!ableToAdvance) {
            System.out.println("piece was trapped at square " + board.square[this.x][this.y]);
            System.out.println("Board size: " + board.square.length);
            System.out.println("piece used movement rules: ");
            System.out.println("Xs: " + Arrays.toString(validXs));
            System.out.println("Ys: " + Arrays.toString(validYs));
            System.out.println("\n\n");

            Window.drawPanel.drawStop(x, y);
            return true;
        }

        Window.drawPanel.drawPiece(this.x, this.y, this.x+ this.validXs[lowestIndex], this.y + this.validYs[lowestIndex]);
        this.x += this.validXs[lowestIndex];
        this.y += this.validYs[lowestIndex];
        this.visitedSquares.add(board.square[this.x][this.y]);

        //System.out.println("piece currently at square " + board.square[this.x][this.y]);



        /*try {
            Thread.sleep(0, 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
*/
        return makeMoves();


    }

    public void reinit() {
        x = board.startX;
        y = board.startY;
        visitedSquares.clear();
    }



}
