package main;

import entity.Entity;
import tile.Tile;

import java.awt.*;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
// Get the Entity's current hitbox position in the world
        int entityLeftWorldX = (int) (entity.worldX + entity.soildArea.x);
        int entityRightWorldX = (int) (entity.worldX + entity.soildArea.x + entity.soildArea.width);
        int entityTopWorldY = (int) (entity.worldY + entity.soildArea.y);
        int entityBottomWorldY = (int) (entity.worldY + entity.soildArea.y + entity.soildArea.height);

        // Create a temporary rectangle for the entity's PREDICTED movement
        Rectangle predictedHitbox = new Rectangle(entityLeftWorldX, entityTopWorldY, entity.soildArea.width, entity.soildArea.height);

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (int) (entityTopWorldY - entity.speed) / gp.tileSize;
                predictedHitbox.y -= entity.speed;
                if (entityTopRow >= 0) {
                    tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                    tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];

                    if (gp.tileM.tile[tileNum1].collision) checkIntersection(entity, predictedHitbox, entityLeftCol, entityTopRow);
                    if (gp.tileM.tile[tileNum2].collision) checkIntersection(entity, predictedHitbox, entityRightCol, entityTopRow);
                } else { entity.collisionOn = true; }
                break;// Treat map edge as collision
            case "down":
                entityBottomRow = (int) (entityBottomWorldY + entity.speed) / gp.tileSize;
                predictedHitbox.y += entity.speed;
                if (entityBottomRow < gp.maxWorldRow) {
                    tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                    tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

                    if (gp.tileM.tile[tileNum1].collision) checkIntersection(entity, predictedHitbox, entityLeftCol, entityBottomRow);
                    if (gp.tileM.tile[tileNum2].collision) checkIntersection(entity, predictedHitbox, entityRightCol, entityBottomRow);
                } else { entity.collisionOn = true; }
                break;
            case "left":
                entityLeftCol = (int) (entityLeftWorldX - entity.speed) / gp.tileSize;
                predictedHitbox.x -= entity.speed;
                if (entityLeftCol >= 0) {
                    tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                    tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];

                    if (gp.tileM.tile[tileNum1].collision) checkIntersection(entity, predictedHitbox, entityLeftCol, entityTopRow);
                    if (gp.tileM.tile[tileNum2].collision) checkIntersection(entity, predictedHitbox, entityLeftCol, entityBottomRow);
                } else { entity.collisionOn = true; }
                break;
            case "right":
                entityRightCol = (int) (entityRightWorldX + entity.speed) / gp.tileSize;
                predictedHitbox.x += entity.speed;
                if (entityRightCol < gp.maxWorldCol) {
                    tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                    tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

                    if (gp.tileM.tile[tileNum1].collision) checkIntersection(entity, predictedHitbox, entityRightCol, entityTopRow);
                    if (gp.tileM.tile[tileNum2].collision) checkIntersection(entity, predictedHitbox, entityRightCol, entityBottomRow);
                } else { entity.collisionOn = true; }
                break;
        }

    }
    private void checkIntersection(Entity entity, Rectangle predictedHitbox, int col, int row) {
        Tile tile = gp.tileM.tile[gp.tileM.mapTileNum[col][row]];

        // Calculate the tile's solid area in World Coordinates
        Rectangle tileWorldHitbox = new Rectangle(
                (col * gp.tileSize) + tile.collisionBox.x,
                (row * gp.tileSize) + tile.collisionBox.y,
                tile.collisionBox.width,
                tile.collisionBox.height
        );

        if (predictedHitbox.intersects(tileWorldHitbox)) {
            entity.collisionOn = true;
        }
    }

}
