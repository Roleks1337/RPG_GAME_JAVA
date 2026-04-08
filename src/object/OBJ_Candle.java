package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Candle extends SuperObject{
    public OBJ_Candle() {
        name="candle";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/CandleObject.png"));
        }catch(
                IOException e) {
            e.printStackTrace();
        }
    }
}
