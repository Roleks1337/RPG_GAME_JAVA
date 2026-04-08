package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Torch extends SuperObject{
    public OBJ_Torch() {
        name="Torch";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/TorchObject.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }

    }
}
