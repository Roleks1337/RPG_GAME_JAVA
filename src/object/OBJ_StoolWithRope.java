package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_StoolWithRope extends SuperObject {
    public OBJ_StoolWithRope() {
        name="StoolWithRope";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/StoolWithRopeObject.png"));
        }catch(
                IOException e) {
            e.printStackTrace();
        }
    }
}
