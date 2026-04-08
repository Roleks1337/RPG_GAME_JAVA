package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Rug extends SuperObject{
    public OBJ_Rug() {
        name="Rug";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/RugObject.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
