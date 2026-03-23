package tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    public BufferedImage image;
    public boolean collision = false;
    public Rectangle collisionBox = new Rectangle(0, 0, 64, 64);

    public boolean horizontalFlip = false;
    public boolean verticalFlip = false;

    public BufferedImage getImage() {
        if ((!horizontalFlip && !verticalFlip) || image == null) {
            return image;
        }

        BufferedImage flipped = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                image.getType()
        );

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                // Determine target X and Y based on flags
                int targetX = horizontalFlip ? (image.getWidth() - 1 - x) : x;
                int targetY = verticalFlip ? (image.getHeight() - 1 - y) : y;

                flipped.setRGB(targetX, targetY, image.getRGB(x, y));
            }
        }
        return flipped;
    }
}