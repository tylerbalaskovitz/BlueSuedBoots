package utils;

import java.awt.geom.Rectangle2D;

import com.tbonegames.main.Game;

//static methods to take in data and return values
public class HelpMethods {
	
	public static boolean canMoveHere(float x, float y, float width, float height, int [][] levelData) {
		
		if (!isSolid(x, y, levelData)) {
			if (!isSolid(x + width, y + height, levelData)) {
				if (!isSolid(x+width, y , levelData)) {
					if (!isSolid(x, y+height, levelData)) {
						return true;
					}
				}
			}
		} return false;
		
		
	}
	
	public static boolean isAllTilesWalkable(int xStart, int xEnd, int y, int[][] levelData) {
		
		for (int i = 0; i < xEnd - xStart; i++) {
			if (isTileSolid(xStart + i, y, levelData)) {
				return false;
			}
			if (!isTileSolid(xStart + i, y+1, levelData)) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isSightClear(int[][] levelData, Rectangle2D.Float firstHitbox, Rectangle2D.Float secondHitbox, int yTile) {
		int firstXTile = (int)(firstHitbox.x / Game.TILES_SIZE);
		int secondXTile = (int)(secondHitbox.x / Game.TILES_SIZE);
		
		if (firstXTile > secondXTile) {
			return isAllTilesWalkable(secondXTile, firstXTile, yTile, levelData);
		} else {
			return isAllTilesWalkable(firstXTile, secondXTile, yTile, levelData);
		}
	}
	
	private static boolean isSolid(float x, float y, int[][] levelData) {
		int maxWidth = levelData[0].length * Game.TILES_SIZE;
		if (x < 0 || x >= maxWidth) {
			return true;
		}
		if (y < 0 || y >= Game.GAME_HEIGHT) {
			return true;
		}
		
		float xIndex = x / Game.TILES_SIZE;
		float yIndex = y / Game.TILES_SIZE;
		
		return isTileSolid((int)xIndex, (int)yIndex, levelData);
		
	}
	
	public static boolean isTileSolid(int xTile, int yTile, int[][]levelData) {
		int value = levelData[(int)yTile][(int)xTile];
		
		if (value >= 48 || value < 0 || value != 11) {
			return true;
		} else {
			return false;
		}
	}
	
	public static float getEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
		int currentTile = (int)(hitbox.x/ Game.TILES_SIZE);
		
		if (xSpeed > 0) {
			//right
			int tileXPos = currentTile * Game.TILES_SIZE;
			int xOffset = (int)(Game.TILES_SIZE - hitbox.width);
			return tileXPos + xOffset -1;
		} else {
			//left
			return currentTile * Game.TILES_SIZE;
		}
		
	}
	
	public static float getEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
		int currentTile = (int)(hitbox.y/ Game.TILES_SIZE);
		if (airSpeed > 0) {
			//falling down
			int tileYPos = currentTile * Game.TILES_SIZE;
			int yOffSet = (int)(Game.TILES_SIZE - hitbox.height);
			return tileYPos + yOffSet -1;//the minus one prevents you from going into the tile.
			
		} else {
			//jumping
			return currentTile * Game.TILES_SIZE;
		}
	}
	
	public static boolean isEntityOnFloor(Rectangle2D.Float hitbox, int[][] levelData) {
		//check the pixel below bottom left, and bottom right
		if(!isSolid(hitbox.x, hitbox.y +hitbox.height+1, levelData)) {
			if(!isSolid(hitbox.x + hitbox.width, hitbox.y +hitbox.height+1, levelData)) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isFloor(Rectangle2D.Float hitbox, float xSpeed, int[][]levelData) {
		return isSolid(hitbox.x + xSpeed, hitbox.y + hitbox.height + 1, levelData);
		
	}

}
