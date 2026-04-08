package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends SuperObject{
    public OBJ_Chest() {
        name = "chest";
        collision = true;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/ChestObject.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
