package entity;

import java.util.Random;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    private Random rand = new Random();

    long lastIdleTime = 0;


    final long cooldownTimeMillis = 500;
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public String lastDirection = "down";


    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize /2);
        screenY = gp.screenHeight/2 - (gp.tileSize /2);

        this.spriteWidth = gp.tileSize;
        this.spriteHeight = 40 * gp.scale;

        int hitboxWidth = 48;
        int hitboxHeight = 30;

        solidAeaDefaultX = (64 - hitboxWidth) / 2;
        solidAeaDefaultY = 40;

        soildArea = new Rectangle((64-hitboxWidth)/2, 40, hitboxWidth, hitboxHeight);


        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 9 - (gp.tileSize /2) + 32;
        worldY = gp.tileSize * 9 - (gp.tileSize /2);
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            idle1 = ImageIO.read(getClass().getResourceAsStream("/res/player/knight_idle_1.png"));
            idle2 = ImageIO.read(getClass().getResourceAsStream("/res/player/knight_idle_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/knight_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/knight_down_2.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/knight_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/knight_up_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/knight_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/knight_right_2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/res/player/knight_right_3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/res/player/knight_right_4.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/knight_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/knight_left_2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/res/player/knight_left_3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/res/player/knight_left_4.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        long currentTime = System.currentTimeMillis();

        // 1. USTALANIE KIERUNKU NA PODSTAWIE WEJŚCIA (Input)
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }
            lastIdleTime = currentTime;
        } else {
            // Logika przejścia w tryb Idle
            if (currentTime - lastIdleTime >= cooldownTimeMillis) {
                // Zapisujemy ostatni kierunek przed wejściem w idle,
                // aby CollisionChecker wiedział, gdzie patrzymy przy interakcji
                if (!direction.equals("idle")) {
                    lastDirection = direction;
                }
                direction = "idle";
                if (currentIdle == null) {
                    currentIdle = rand.nextBoolean() ? idle1 : idle2;
                }
            }
        }

        // 2. RUCH I KOLIZJE (Tylko jeśli nie jesteśmy w IDLE lub jeśli chcemy sprawdzić interakcję)
        if (!direction.equals("idle")) {
            double currentSpeed = speed;

            // Korekta prędkości diagonalnej (0.707 to ok. pierwiastek z 2 przez 2)
            boolean isDiagonal = (keyH.upPressed || keyH.downPressed) && (keyH.leftPressed || keyH.rightPressed);
            if (isDiagonal) {
                currentSpeed *= 0.707;
            }

            // --- KOLIZJA OSIOWA (Separated Axis Collision) ---
            // Pozwala ślizgać się wzdłuż ścian, gdy idziemy na ukos.

            // A. Oś Pionowa (Y)
            if (keyH.upPressed || keyH.downPressed) {
                String tempDir = keyH.upPressed ? "up" : "down";
                collisionOn = false;

                // Sprawdzamy kafelki i obiekty dla osi Y
                gp.cChecker.checkTile(this);
                gp.cChecker.checkObject(this, true, tempDir);

                if (!collisionOn) {
                    if (keyH.upPressed) worldY -= currentSpeed;
                    if (keyH.downPressed) worldY += currentSpeed;
                }
            }

            // B. Oś Pozioma (X)
            if (keyH.leftPressed || keyH.rightPressed) {
                String tempDir = keyH.leftPressed ? "left" : "right";
                collisionOn = false;

                // Sprawdzamy kafelki i obiekty dla osi X
                gp.cChecker.checkTile(this);
                gp.cChecker.checkObject(this, true, tempDir);

                if (!collisionOn) {
                    if (keyH.leftPressed) worldX -= currentSpeed;
                    if (keyH.rightPressed) worldX += currentSpeed;
                }
            }

            // Ograniczenie granic świata
            worldX = Math.max(0, Math.min(worldX, gp.worldWidth - gp.tileSize));
            worldY = Math.max(0, Math.min(worldY, gp.worldHeight - gp.tileSize));
        }

        // 3. INTERAKCJA Z OBIEKTAMI (Klawisz E)
        if (gp.keyH.enterPressed) {
            String checkDir = direction.equals("idle") ? lastDirection : direction;

            // Tworzymy hitbox interakcji (powiększony o 16px)
            Rectangle interactionRect = new Rectangle(
                    (int)worldX + (int)solidAeaDefaultX,
                    (int)worldY + (int)solidAeaDefaultY,
                    soildArea.width,
                    soildArea.height
            );
            interactionRect.grow(16, 16);

            int objIndex = 999;
            for(int i = 0; i < gp.obj.length; i++) {
                if(gp.obj[i] != null) {
                    // Hitbox obiektu w świecie
                    Rectangle objRect = new Rectangle(
                            gp.obj[i].worldX + gp.obj[i].solidAreaDefaultX,
                            gp.obj[i].worldY + gp.obj[i].solidAreaDefaultY,
                            gp.obj[i].solidArea.width,
                            gp.obj[i].solidArea.height
                    );

                    // 1. Sprawdzamy, czy prostokąty się przecinają
                    if (interactionRect.intersects(objRect)) {

                        // 2. Sprawdzamy, czy gracz jest zwrócony w stronę obiektu
                        boolean facingObject = false;

                        // Środki obiektów do porównania
                        int playerCenterX = (int)worldX + gp.tileSize / 2;
                        int playerCenterY = (int)worldY + gp.tileSize / 2;
                        int objCenterX = gp.obj[i].worldX + gp.obj[i].solidArea.width / 2;
                        int objCenterY = gp.obj[i].worldY + gp.obj[i].solidArea.height / 2;

                        switch(checkDir) {
                            case "up":    if(objCenterY < playerCenterY) facingObject = true; break;
                            case "down":  if(objCenterY > playerCenterY) facingObject = true; break;
                            case "left":  if(objCenterX < playerCenterX) facingObject = true; break;
                            case "right": if(objCenterX > playerCenterX) facingObject = true; break;
                        }

                        if (facingObject && gp.obj[i].interactable) {
                            objIndex = i;
                            break;
                        }
                    }
                }
            }

            interactObject(objIndex);
            gp.keyH.enterPressed = false;
        }

        // 4. ANIMACJA SPRITE'ÓW
        spriteCounter++;
        RightLeftSpriteCounter++;

        if (spriteCounter > 20) {
            upDownSpriteNum = (upDownSpriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }

        if (RightLeftSpriteCounter > 20) {
            rightLeftSpriteNum++;
            if (rightLeftSpriteNum > 4) {
                rightLeftSpriteNum = 1;
            }
            RightLeftSpriteCounter = 0;
        }
    }
    public void interactObject(int i) {
        if (i != 999) { // 999 is standard "no object" return from CollisionChecker
            if (gp.obj[i].interactable) {
                gp.gameState = gp.dialogueState;
                gp.obj[i].speakObject(gp);
                // You can also add specific logic based on gp.obj[i].name here
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch(direction) {
            case "down":
                if (upDownSpriteNum == 1) {
                    image = down1;
                }
                if (upDownSpriteNum == 2){
                    image = down2;
                }
                break;
            case "up":
                if (upDownSpriteNum == 1) {
                    image = up1;
                }
                if (upDownSpriteNum == 2){
                    image = up2;
                }
                break;
            case "left":
                if (rightLeftSpriteNum == 1) {
                    image = left1;
                }
                if (rightLeftSpriteNum == 2) {
                    image = left2;
                }
                if (rightLeftSpriteNum == 3) {
                    image = left3;
                }
                if (rightLeftSpriteNum == 4) {
                    image = left4;
                }
                break;
            case "right":
                if (rightLeftSpriteNum == 1) {
                    image = right1;
                }
                if (rightLeftSpriteNum == 2) {
                    image = right2;
                }
                if (rightLeftSpriteNum == 3) {
                    image = right3;
                }
                if (rightLeftSpriteNum == 4) {
                    image = right4;
                }
                break;
            case "idle":
                image = currentIdle;
                break;

        }

        int yOffset = spriteHeight - gp.tileSize;

        g2.drawImage(image, screenX, screenY - yOffset, spriteWidth, spriteHeight, null);
    }
}
