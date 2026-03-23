package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public double worldX,worldY;
    public double speed;

    public int spriteWidth;
    public int spriteHeight;

    public BufferedImage currentIdle, idle1, idle2, up1, up2, down1, down2, right1, right2, right3, right4, left1, left2, left3, left4;
    public String direction;

    public int spriteCounter = 0;
    public int upDownSpriteNum = 1;
    public int rightLeftSpriteNum = 1;
    public int RightLeftSpriteCounter = 0;
    public Rectangle soildArea;
    public boolean collisionOn = false;

}
