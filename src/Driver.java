import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Driver {

    static Window window;

    public static void main(String[] args) throws IOException {

        boolean repeat = true;

        Board board = new Board(400);
        Piece piece = new Piece(board, "iterate");
        Window window = new Window((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
                                   (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight(),
                                    board,
                                    piece);

        int imageNumber = 0;
        File outputFile;

        for(int a = -2; a <= 2; a++) {
            for(int b = -2; b <= 2; b++) {
                for(int c = -2; c <= 2; c++) {
                    for(int d = -2; d <= 2; d++) {
                        for(int e = -2; e <= 2; e++) {
                            for(int f = -2; f <= 2; f++) {
                                for(int g = -2; g <= 2; g++) {
                                    for(int h = -2; h <= 2; h++) {
                                        Window.drawPanel.drawImage = new BufferedImage(Window.width, Window.height, BufferedImage.TYPE_INT_RGB);
                                        piece.reinit();
                                        piece.validXs[0] = a;
                                        piece.validXs[1] = b;
                                        piece.validXs[2] = c;
                                        piece.validXs[3] = d;


                                        piece.validYs[0] = e;
                                        piece.validYs[1] = f;
                                        piece.validYs[2] = g;
                                        piece.validYs[3] = h;


                                        /*System.out.println(Arrays.toString(piece.validXs));
                                        System.out.println(Arrays.toString(piece.validYs));*/

                                        if(piece.makeMoves()) {

                                            Window.drawPanel.drawHeader();
                                            outputFile = new File("image" + imageNumber + ".png");
                                            ImageIO.write(Window.drawPanel.drawImage, "png", outputFile);

                                        }

                                        imageNumber++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        while (piece.type.equals("random")) {
            if(!piece.makeMoves()) {
                Window.drawPanel.drawImage = new BufferedImage(Window.width, Window.height, BufferedImage.TYPE_INT_RGB);
                piece = new Piece(board, piece.type);
                //Window.drawPanel.draw();
                //window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
                //window = new Window((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight(), board, piece);
            } else  {
                File outputfile = new File("image.png");
                ImageIO.write(Window.drawPanel.drawImage, "png", outputfile);

                Window.drawPanel.drawImage = new BufferedImage(Window.width, Window.height, BufferedImage.TYPE_INT_RGB);
                piece = new Piece(board, piece.type);

                //window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
                //window = new Window((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight(), board, piece);
                //break;
            }
        }

    }



}
