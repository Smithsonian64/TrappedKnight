import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class DrawPanel extends JPanel {

    public BufferedImage drawImage;

    Window window;

    Board board;
    Piece piece;

    static int squareSize = 4;
    static int separation = 5;

    static boolean badDraw = false;

    public DrawPanel(int width, int height, Window window, Board board, Piece piece) {

        drawImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        this.window = window;

        this.board = board;
        this.piece = piece;


    }

    @Override
    public void paint(Graphics g) {
        draw();
        g.drawImage(drawImage, 0, 0, null);
    }

    public void draw() {
        Graphics2D g2d = drawImage.createGraphics();
        g2d.setColor(Color.YELLOW);

        Window.drawPanel.drawStart(board.startX, board.startY);

        /*while(true) {
            if(board.square.length * separation > Window.height) {
                separation--;
                continue;
            }
            break;
        }*/


        g2d.drawRect(Window.width/2 - (board.square.length * separation)/2,
                Window.height/2 - (board.square.length * separation)/2,
                separation * board.square.length,
                separation * board.square.length);

        /*for(int i = 0; i < board.square.length; i++) {
            for(int j = 0; j < board.square[i].length; j++) {
                g2d.fillOval(i*separation + Window.width/2 - (board.square.length * separation)/2,
                             j*separation + Window.height/2 - (board.square.length * separation)/2,
                                2,
                                2);
            }
        }*/

        repaint();
    }

    public void drawPiece(int x1, int y1, int x2, int y2) {
        Graphics2D g2d = drawImage.createGraphics();
        g2d.setColor(Color.MAGENTA);

        g2d.drawLine(x1*separation + squareSize/2+ Window.width/2 - (board.square.length * separation)/2,
                     y1*separation + squareSize/2 + Window.height/2 - (board.square.length * separation)/2,
                (x2*separation + squareSize/2) + Window.width/2 - (board.square.length * separation)/2,
                (y2*separation + squareSize/2) + Window.height/2 - (board.square.length * separation)/2);

    }

    public void drawStop(int x, int y) {
        Graphics2D g2d = drawImage.createGraphics();
        g2d.setColor(Color.WHITE);

        g2d.fillOval(x*separation + squareSize/2+ Window.width/2 - (board.square.length * separation)/2,
                y*separation + squareSize/2 + Window.height/2 - (board.square.length * separation)/2,
                9, 9);
        this.repaint();
    }

    public void drawStart(int x, int y) {
        Graphics2D g2d = drawImage.createGraphics();
        g2d.setColor(Color.GREEN);

        g2d.fillOval(x*separation + squareSize/2+ Window.width/2 - (board.square.length * separation)/2,
                y*separation + squareSize/2 + Window.height/2 - (board.square.length * separation)/2,
                9, 9);
        this.repaint();
    }

    public void drawHeader() {
        Graphics2D g2d = drawImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2d.drawString(Arrays.toString(piece.validXs) , 20, 20);
        g2d.drawString(Arrays.toString(piece.validYs), 20, 40);
    }

}
