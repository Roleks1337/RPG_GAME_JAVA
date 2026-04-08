package object;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class OBJ_Key extends SuperObject {
    public OBJ_Key() {
        name = "Key";
        collision =true;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/BedObject.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }

        int width = 60;
        int height = 110;
        int offsetX = 0;
        int offsetY = 0;

        solidArea = new Rectangle(offsetX, offsetY, width, height);

        solidAreaDefaultX = offsetX;
        solidAreaDefaultY = offsetY;
    }
}
