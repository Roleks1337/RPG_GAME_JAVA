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


    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize /2);
        screenY = gp.screenHeight/2 - (gp.tileSize /2);

        this.spriteWidth = gp.tileSize;
        this.spriteHeight = 40 * gp.scale;

        int hitboxWidth = 48;
        int hitboxHeight = 30;

        soildArea = new Rectangle((64-hitboxWidth)/2, 40, hitboxWidth, hitboxHeight);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 8 - (gp.tileSize /2);
        worldY = gp.tileSize * 12 - (gp.tileSize /2);
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

        // 1. SET DIRECTION BASED ON INPUT
        // We prioritize Up/Down for the collision checker direction,
        // but the actual movement will handle both axes.
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
            // Handle Transition to Idle
            if (currentTime - lastIdleTime >= cooldownTimeMillis) {
                direction = "idle";
                if (currentIdle == null) {
                    currentIdle = rand.nextBoolean() ? idle1 : idle2;
                }
            }
        }

        // 2. CHECK TILE COLLISION
        collisionOn = false;
        gp.cChecker.checkTile(this);

        // 3. IF COLLISION IS FALSE, MOVE THE PLAYER
        if (!direction.equals("idle")) {
            double currentSpeed = speed;
            boolean isDiagonal = (keyH.upPressed || keyH.downPressed) && (keyH.leftPressed || keyH.rightPressed);
            if (isDiagonal) {
                currentSpeed *= 0.707;
            }

            // --- IMPROVED COLLISION LOGIC ---

            // A. Handle Vertical Movement (Y-Axis)
            if (keyH.upPressed || keyH.downPressed) {
                // Temporarily set direction for the checker to understand where we are looking
                String originalDirection = direction;
                direction = keyH.upPressed ? "up" : "down";

                collisionOn = false;
                gp.cChecker.checkTile(this);

                if (!collisionOn) {
                    if (keyH.upPressed) worldY -= currentSpeed;
                    if (keyH.downPressed) worldY += currentSpeed;
                }
                direction = originalDirection; // Restore for animation
            }

            // B. Handle Horizontal Movement (X-Axis)
            if (keyH.leftPressed || keyH.rightPressed) {
                String originalDirection = direction;
                direction = keyH.leftPressed ? "left" : "right";

                collisionOn = false;
                gp.cChecker.checkTile(this);

                if (!collisionOn) {
                    if (keyH.leftPressed) worldX -= currentSpeed;
                    if (keyH.rightPressed) worldX += currentSpeed;
                }
                direction = originalDirection;
            }

            // Map Boundary Safeguards
            worldX = Math.max(0, Math.min(worldX, gp.worldWidth - gp.tileSize));
            worldY = Math.max(0, Math.min(worldY, gp.worldHeight - gp.tileSize));
        }

        // 4. SPRITE ANIMATION LOGIC
        spriteCounter++;
        RightLeftSpriteCounter++;

        if (spriteCounter > 20) {
            upDownSpriteNum = (upDownSpriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }

        if (RightLeftSpriteCounter > 20) {
            if (rightLeftSpriteNum == 1) rightLeftSpriteNum = 2;
            else if (rightLeftSpriteNum == 2) rightLeftSpriteNum = 3;
            else if (rightLeftSpriteNum == 3) rightLeftSpriteNum = 4;
            else if (rightLeftSpriteNum == 4) rightLeftSpriteNum = 1;
            RightLeftSpriteCounter = 0;
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

        g2.drawImage(image, screenX,screenY - yOffset, spriteWidth, spriteHeight, null);
    }
}
