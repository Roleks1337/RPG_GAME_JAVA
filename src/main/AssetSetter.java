package main;

import object.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        gp.obj[0] = new OBJ_Rug();
        gp.obj[0].worldX = 6*64 -32;
        gp.obj[0].worldY = 11*64;

        gp.obj[1] = new OBJ_Bookshelf();
        gp.obj[1].worldX = 8*64;
        gp.obj[1].worldY = 7*60;

        gp.obj[2] = new OBJ_Door();
        gp.obj[2].worldX = 8*64;
        gp.obj[2].worldY = 14*64-7;

        gp.obj[3] = new OBJ_SideChair();
        gp.obj[3].worldX = 5*64+12 ;
        gp.obj[3].worldY = 11*64-16;

        gp.obj[4] = new OBJ_Chair();
        gp.obj[4].worldX = 6*64 + 8;
        gp.obj[4].worldY = 10*64;

        gp.obj[5] = new OBJ_SideChest();
        gp.obj[5].worldX = 10*64 ;
        gp.obj[5].worldY = 10*64 + 16;

//        gp.obj[6] = new OBJ_StoolWithRope();
//        gp.obj[6].worldX = 7*64 + 8;
//        gp.obj[6].worldY = 13*65-32;

        gp.obj[7] = new OBJ_Table();
        gp.obj[7].worldX = 6*64;
        gp.obj[7].worldY = 11*64;

        gp.obj[8] = new OBJ_Coin();
        gp.obj[8].worldX = 6*64+94;
        gp.obj[8].worldY = 11*64 +12;

        gp.obj[9] = new OBJ_Candle();
        gp.obj[9].worldX = 6*64+46;
        gp.obj[9].worldY = 11*64 -12;

        gp.obj[10] = new OBJ_Torch();
        gp.obj[10].worldX = 8*64;
        gp.obj[10].worldY = 9*60;

        gp.obj[11] = new OBJ_Torch();
        gp.obj[11].worldX = 4*64;
        gp.obj[11].worldY = 12*64 - 16;

        gp.obj[12] = new OBJ_Torch();
        gp.obj[12].worldX = 10*64 + 24;
        gp.obj[12].worldY = 12*64 + 12;
        gp.obj[12].horizontalFlip = true;

        gp.obj[13] = new OBJ_Chair2();
        gp.obj[13].worldX =6*64 + 32;
        gp.obj[13].worldY = 12*64 - 24;

        gp.obj[14] = new OBJ_Key();
        gp.obj[14].worldX = 10*64;
        gp.obj[14].worldY = 8*60;

    }
}
