package object;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class OBJ_SideChair extends SuperObject{
    public OBJ_SideChair() {
        name="sidechair";
        collision=true;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/SideChairObject.png"));
        }catch(
                IOException e) {
            e.printStackTrace();
        }
    }
}
