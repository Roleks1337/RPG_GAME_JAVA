package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;

    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[90];
        mapTileNum = new int [gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadmap("/res/maps/map4.txt");
    }

    public void getTileImage() {
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/VoidTile.png"));
            tile[0].collision = true;
            tile[0].collisionBox = new Rectangle(0,0, 48, 64); //custom hb

            tile[28] = new Tile();
            tile[28].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/FillertstTile.png"));
            tile[28].collision = true;
            tile[28].horizontalFlip = true;

            tile[27] = new Tile();
            tile[27].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/FillertstTile.png"));
            tile[27].collision = true;

            tile[26] = new Tile();
            tile[26].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/RightHalfWall3DVoidFilled.png"));
            tile[26].collision = true;
            tile[26].horizontalFlip = true;

            tile[25] = new Tile();
            tile[25].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/CurvedFullBrickWall.png"));
            tile[25].collision = true;
            tile[25].horizontalFlip = true;

            tile[24] = new Tile();
            tile[24].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/FullBrickWallTile.png"));
            tile[24].collision = true;

            tile[23] = new Tile();
            tile[23].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/LeftHalfWall3DVoidFilled.png"));

            tile[22] = new Tile();
            tile[22].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/HalfWallTileRightVoidFilled.png"));
            tile[22].collision = true;
            tile[22].collisionBox = new Rectangle(16,0, 48, 64);

            tile[15] = new Tile();
            tile[15].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/HalfWallTileRightVoidFilled.png"));
            tile[15].collision = true;
            tile[15].horizontalFlip = true;

            tile[16] = new Tile();
            tile[16].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/LeftHalfWall3D.png"));
            tile[16].collision = true;
            tile[16].collisionBox = new Rectangle(0,0, 48, 64);

            tile[17] = new Tile();
            tile[17].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/RightHalfWall3DVoidFilled.png"));
            tile[17].collision = true;
            tile[17].collisionBox = new Rectangle(16,0, 48, 64);

            tile[18] = new Tile();
            tile[18].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/MudTile_two.png"));

            tile[19] = new Tile();
            tile[19].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/MudTile_three.png"));

            tile[20] = new Tile();
            tile[20].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/CurvedFullBrickWall.png"));
            tile[20].collision = true;

            tile[21] = new Tile();
            tile[21].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/VoidTile.png"));
            tile[21].collision = true;

            tile[14] = new Tile();
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/BrickWallTile3D.png"));
            tile[14].collision = true;

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/GrassTileAllNoLeft.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/WaterTile.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/GrassTileAllNoRight.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/MudTile.png")); //original grass tile

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/StrawTileUp.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/StrawTileDown.png"));

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/StrawTileRight.png"));

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/StrawTileLeft.png"));

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/StrawTileFiller.png"));

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/RightCornerStraw.png"));

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/LeftCornerStraw.png"));

            tile[12] = new Tile();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/BottomRightCornerStraw.png"));

            tile[13] = new Tile();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/BottomLeftCornerStraw.png"));

            //HellTiles

            tile[29] = new Tile();
            tile[29].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/hell/HorrorBrickTile3D.png"));
            tile[29].collision = true;

            tile[30] = new Tile();
            tile[30].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/hell/HorrorHalfWallTile.png"));
            tile[30].collision = true;

            tile[31] = new Tile();
            tile[31].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/hell/HorrorHalfWallTile.png"));
            tile[31].collision = true;
            tile[31].horizontalFlip = true;

            tile[32] = new Tile();
            tile[32].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/hell/HorrorHalfWallTile3D.png"));
            tile[32].collision = true;

            tile[33] = new Tile();
            tile[33].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/hell/HorrorHalfWallTile3D.png"));
            tile[33].collision = true;
            tile[33].horizontalFlip = true;

            tile[34] = new Tile();
            tile[34].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/basicbiome/32pxFloorTIle.png"));

            tile[35] = new Tile();
            tile[35].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/basicbiome/32pxBrickWallVoidFilled.png"));
            tile[35].collision = true;

            tile[36] = new Tile();
            tile[36].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/basicbiome/32pxBrickWall3D2.png"));
            tile[36].collision = true;

            tile[37] = new Tile();
            tile[37].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/basicbiome/32pxBrickWall3.png"));
            tile[37].collision = true;

            tile[38] = new Tile();
            tile[38].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/basicbiome/32pxBrickWallVoidFilled2.png"));
            tile[38].collision = true;

            tile[39] = new Tile();
            tile[39].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/basicbiome/32pxBrickWallVoidFilled2.png"));
            tile[39].horizontalFlip = true;
            tile[39].collision = true;

            tile[40] = new Tile();
            tile[40].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/basicbiome/32pxBrickWall3DVoidFilled2.png"));
            tile[40].collision = true;

            tile[41] = new Tile();
            tile[41].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/basicbiome/32pxBrickWall3DVoidFilled2.png"));
            tile[41].horizontalFlip = true;
            tile[41].collision = true;

            tile[42] = new Tile();
            tile[42].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/basicbiome/GrassTile.png"));

            tile[43] = new Tile();
            tile[43].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/basicbiome/FullGrassTile.png"));

            tile[44] = new Tile();
            tile[44].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/basicbiome/BottomFacingGrassTile.png"));

            tile[45] = new Tile();
            tile[45].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/basicbiome/BottomFacingGrassTile.png"));
            tile[45].verticalFlip = true;

            tile[46] = new Tile();
            tile[46].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/basicbiome/RightFacingGrassTile.png"));

            tile[47] = new Tile();
            tile[47].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/basicbiome/RightCornerGrassTile.png"));

            tile[48] = new Tile();
            tile[48].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/basicbiome/RightCornerGrassTile.png"));
            tile[48].verticalFlip = true;

            tile[49] = new Tile();
            tile[49].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/basicbiome/32pxBrickWallGrassFilled2.png"));
            tile[49].horizontalFlip = true;
            tile[49].collision = true;

            tile[50] = new Tile();
            tile[50].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/basicbiome/32pxBrickWallGrassFilled2.png"));
            tile[50].collision = true;

            tile[51] = new Tile();
            tile[51].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/basicbiome/32pxBrickWall3DGrassFilled.png"));
            tile[51].collision = true;
            tile[51].horizontalFlip = true;

            tile[52] = new Tile();
            tile[52].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/basicbiome/HalfMudHalfFloorTile.png"));

            tile[53] = new Tile();
            tile[53].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/basicbiome/32pxBrickTIleWIthFIlledGrass.png"));
            tile[53].horizontalFlip = true;

            tile[54] = new Tile();
            tile[54].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/basicbiome/32pxBrickTIleWIthFIlledGrass.png"));
            tile[54].horizontalFlip = true;
            tile[54].verticalFlip = true;



        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void loadmap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while(col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }

                if(col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch(Exception e) {

        }
    }
    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow =0;


        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum =  mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            double screenX = (worldX - gp.player.worldX + gp.player.screenX);
            double screenY = (worldY - gp.player.worldY + gp.player.screenY);

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
                    && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                    && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
                    && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                    g2.drawImage(tile[tileNum].getImage(), (int)screenX, (int)screenY, gp.tileSize +1, gp.tileSize +1, null);
            }

            worldCol++;

            if(worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
