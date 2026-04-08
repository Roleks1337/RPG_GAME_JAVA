package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class OBJ_Bookshelf extends SuperObject {
    public OBJ_Bookshelf() {
        setDialogue();
        interactable = true;
        name = "BookShelf";
        collision=true;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/BookshelfObject.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }

        solidArea.x = image.getWidth();
        solidArea.y = image.getHeight();

        int width = 128;
        int height = 64;
        int offsetX = 0;
        int offsetY = 64;

        solidArea = new Rectangle(offsetX, offsetY, width, height);

        solidAreaDefaultX = offsetX;
        solidAreaDefaultY = offsetY;
    }
    public void setDialogue() {
        dialogues[0] = "test1";
        dialogues[1] = "test2";
        dialogues[2] = "test3";
    }
    public void speakObject(GamePanel gp) {
        super.speakObject(gp);
    }
}
