package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Coin extends SuperObject{
    public OBJ_Coin() {
        name="coin";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/CoinObject.png"));
        }catch(
                IOException e) {
            e.printStackTrace();
        }
    }
}
