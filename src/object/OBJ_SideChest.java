package object;

import main.GamePanel;
import main.UI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class OBJ_SideChest extends SuperObject{
    GamePanel gp;
    public OBJ_SideChest() {
        setDialogue();
        interactable = true;
        name = "SideChest";
        collision=true;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/SideChestObject.png"));
        }catch(
                IOException e) {
            e.printStackTrace();
        }

        int width = 64;
        int height = 80;
        int offsetX = 0;
        int offsetY = 8;

        solidArea = new Rectangle(offsetX, offsetY, width, height);

        solidAreaDefaultX = offsetX;
        solidAreaDefaultY = offsetY;
    }
    public void setDialogue() {
        dialogues[0] = "1";
        dialogues[1] = "2";
        dialogues[2] = "3";
    }
    public void speakObject(GamePanel gp) {
        super.speakObject(gp);
    }
}
