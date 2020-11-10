import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DrawPanel extends JPanel {

    BufferedImage drawImage;

    Window window;

    Board board;
    Piece piece;


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

        int squareSize = 2;
        int sepearation = 4;

        for(int i = 0; i < board.square.length; i++) {
            for(int j = 0; j < board.square[i].length; j++) {
                g2d.fillRect((i*sepearation - squareSize/2), (j*sepearation - 1) , squareSize, squareSize);
            }
        }

        repaint();
    }

}
