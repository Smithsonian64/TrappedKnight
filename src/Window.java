import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window extends JFrame {


    static DrawPanel drawPanel;

    static int width;
    static int height;

    public Window(int width, int height, Board board, Piece piece) {

        Window.width = width;
        Window.height = height;

        drawPanel = new DrawPanel(width, height, this, board, piece);

        this.add(drawPanel);

        this.setTitle("Trapped Knight");
        this.setUndecorated(true);
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(width, height);
        this.setLocation(   (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2 - width/2,
                (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2 - height/2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                    return;
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

}