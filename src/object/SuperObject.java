package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    GamePanel gp;
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public boolean interactable = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,64,64);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public boolean horizontalFlip = false;

    String dialogues[] = new String[20];
    int dialogueIndex = 0;
    public void speakObject(GamePanel gp) {
        // Access the UI through the GamePanel reference
        if(dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
    }
    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = (int)(worldX - gp.player.worldX + gp.player.screenX);
        int screenY = (int)(worldY - gp.player.worldY + gp.player.screenY);

        int width = image.getWidth();
        int height = image.getHeight();

        if("candle".equals(name)) {
            width /= 1.15;
            height /= 1.20;
        }
        else if("coin".equals(name)) {
            width /= 2.5;
            height /= 2.7;
        }
        else if("Torch".equals(name)) {
            width /= 1.5;
            height /= 1.55;
        }
        else if("Table".equals(name)) {
            height *= 1.2;
        }
        else if("Chair2".equals(name)) {
            width *= 1.15;
        }
        else if("Rug".equals(name)) {
            width *= 1.5;
            height *= 1.5;
        }


        if(worldX + width > gp.player.worldX - gp.player.screenX
                && worldX - width < gp.player.worldX + gp.player.screenX
                && worldY + height > gp.player.worldY - gp.player.screenY
                && worldY - height < gp.player.worldY + gp.player.screenY) {

            if (horizontalFlip) {
                g2.drawImage(image, screenX + width, screenY, -width, height, null);
            } else {
                g2.drawImage(image, screenX, screenY, width, height, null);
            }
        }
    }
}
