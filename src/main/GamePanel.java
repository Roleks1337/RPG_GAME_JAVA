package main;

import entity.Player;
import main.KeyHandler;
import object.SuperObject;
import tile.TileManager;

import java.awt.*;

public class GamePanel extends javax.swing.JPanel implements Runnable {

    //SCREEN SETTINGS
    public final int originalTileSize = 32;// 16 x 16
    public final int scale = 2;

    public final int tileSize = originalTileSize * scale; // scaled 64 x 64
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768px
    public final int screenHeight = tileSize * maxScreenRow; // 576px

    //WORLD SETTINGS
    public final int maxWorldCol = 80;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    //FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Thread gameThred;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public Player player = new Player(this,keyH);
    public SuperObject obj[] = new SuperObject[20];

    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        aSetter.setObject();
        gameState = playState;
    }

    public void startGameThread() {

        gameThred = new Thread(this);
        gameThred.start();
    }

    @Override
    public void run() {

        double drawInterval  = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThred != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                // 1.UPDATE
                update();

                // 2.DRAW
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }

    }
    public void update() {
        if(gameState == playState) {
            player.update();
        }
        if(gameState == pauseState) {
            //nothing
        }
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g); //super is a parent class of the class (so paintCompnent.JPanel)

        Graphics2D g2 = (Graphics2D)g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        tileM.draw(g2);
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null && !obj[i].name.equals("Torch") ) {
                obj[i].draw(g2, this);
            }
        }
        player.draw(g2);
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null && obj[i].name.equals("Torch") ) {
                obj[i].draw(g2, this);
            }
        }

        ui.draw(g2);

        g2.dispose();
    }
}
