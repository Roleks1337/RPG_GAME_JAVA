package object;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class OBJ_Table extends SuperObject{
    public OBJ_Table() {
        name="Table";
        collision = true;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/TableObject.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }

        int width = 118;
        int height = 48;
        int offsetX = 8;
        int offsetY = 6;

        solidArea = new Rectangle(offsetX, offsetY, width, height);

        solidAreaDefaultX = offsetX;
        solidAreaDefaultY = offsetY;
    }
}
