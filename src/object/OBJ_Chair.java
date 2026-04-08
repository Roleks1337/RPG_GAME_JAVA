package object;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class OBJ_Chair extends SuperObject{
    public OBJ_Chair() {
        name="chair";
        collision=true;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/ChairObject.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
        int width = 48;
        int height = 48;
        int offsetX = 0;
        int offsetY = 0;

        solidArea = new Rectangle(offsetX, offsetY, width, height);

        solidAreaDefaultX = offsetX;
        solidAreaDefaultY = offsetY;
    }
}
