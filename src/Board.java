public class Board {

    int[][] square;

    int startX;
    int startY;

    /**
     * Excellent P vs NP problem here. It is easy to verify the starting square but difficult to find the solving
     * square
     * @param size
     */
    Board(int size) {
        square = new int[size][size];
        int shrink = 0;
        int counter = size*size;

        if(size % 2 != 0) {
            for (int i = 0; i < size / 2; i++) {
                for(int j = size - shrink - 1; j > shrink; j--) {
                    square[j][shrink] = counter--;
                }
                for(int j = shrink; j < size - shrink - 1; j++) {
                    square[shrink][j] = counter--;
                }
                for(int j = shrink; j < size - shrink - 1; j++) {
                    square[j][size - shrink - 1] = counter--;
                }
                for(int j = size - shrink - 1; j > shrink; j--) {
                    square[size - shrink - 1][j] = counter--;
                }
                shrink++;
            }
            square[shrink][shrink] = 1;
            startX = shrink;
            startY = shrink;
        } else if(size % 2 == 0 ) {
            for(int i = 0; i < size / 2; i++) {
                for(int j = shrink; j < size - shrink - 1; j++) {
                    square[j][size - shrink - 1] = counter--;
                }
                for(int j = size - shrink - 1; j > shrink; j--) {
                    square[size - shrink - 1][j] = counter--;
                }
                for(int j = size - shrink - 1; j > shrink; j--) {
                    square[j][shrink] = counter--;
                }
                for(int j = shrink; j < size - shrink - 1; j++){
                    square[shrink][j] = counter--;
                    if(counter == 0) {
                        startX = shrink;
                        startY = j;
                    }
                }
                shrink++;
            }

        }

        //printBoard();

    }

    public void printBoard() {
        int n = 0;
        for(int i = square[0].length - 1; i >= 0; i--) {
            for (int[] j : square) {

                System.out.print(j[i] + "\t");

                if (++n % square.length == 0) System.out.println();

            }
        }
        System.out.println("Start X: " + startX);
        System.out.println("Start Y: " + startY);
        System.out.println();
    }
}
