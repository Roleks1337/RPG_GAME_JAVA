package object;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class OBJ_Chair2 extends SuperObject{
    public OBJ_Chair2() {
        name = "Chair2";
        collision=true;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/Chair2Object.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
        int width = 48;
        int height = 64;
        int offsetX = 0;
        int offsetY = 0;

        solidArea = new Rectangle(offsetX, offsetY, width, height);

        solidAreaDefaultX = offsetX;
        solidAreaDefaultY = offsetY;
    }
}
