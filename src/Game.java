import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Game extends JFrame{
    public static void main(String[] args){
        Game game = new Game();
        game.run();
        System.exit(0);
    }

    Insets insets;
    BufferedImage backBuffer;
    InputHandler input;

    int windowHeight = 400;
    int windowWidth = 400;
    int ballX = 0;
    int ballY = 50;
    ArrayList<BufferedImage> heroSprites;
    ImageTypes images;

    public void run(){
        initialize();
        boolean isRunning = true;
        while(isRunning)
        {
            long time = System.currentTimeMillis();

            update();
            draw();

            //  delay for each frame  -   time it took for one frame
            time = (1000 / 60) - (System.currentTimeMillis() - time);

            if (time > 0)
            {
                try
                {
                    Thread.sleep(time);
                }
                catch(Exception e){}
            }
        }

        setVisible(false);
    }

    void initialize(){
        backBuffer = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB);
        input = new InputHandler(this);
        insets = getInsets();
        setSize(insets.left + windowWidth + insets.right,
                insets.top + windowHeight + insets.bottom);
         //splitSprites("\"resources/graphics/tiny-rpg-town-files/tileset/grass-tile.png\"");
        setTitle("Game Tutorial");
        setSize(windowWidth, windowHeight);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    void update(){
        if(input.isKeyDown(KeyEvent.VK_RIGHT))
            ballX += 5;
        if(input.isKeyDown(KeyEvent.VK_LEFT))
            ballX -= 5;
        if(input.isKeyDown(KeyEvent.VK_UP))
            ballY -= 5;
        if(input.isKeyDown(KeyEvent.VK_DOWN))
            ballY += 5;
    }

    void draw(){
        Graphics g = getGraphics();

        Graphics bbg = backBuffer.getGraphics();

//        String iconPath = "../resources/graphics/tiny-rpg-town-files/tileset/grass-tile.png";
//        ImageIcon hero = null;
//        URL url = getClass().getClassLoader().getResource(iconPath);
//        if(url != null)
//            hero = new ImageIcon(url);
//        final Image img = hero.getImage();
        bbg.setColor(Color.WHITE);
        bbg.fillRect(0, 0, windowWidth, windowHeight);

        try{
            BufferedImage image = ImageIO.read(new File("resources/graphics/tiny-rpg-town-files/tileset/grass-tile.png"));

            bbg.setColor(Color.BLACK);
            //bbg.drawOval(ballX, ballY, 20, 20);
            bbg.drawImage(image, ballX, ballY, 20, 20, null);
            g.drawImage(backBuffer, insets.left, insets.top, this);
        }
        catch(Exception e){
            System.out.println("Exception thrown: " + e.getLocalizedMessage());
        }
    }

//    ArrayList<BufferedImage> splitSprites(String path){
//
//    }
}
