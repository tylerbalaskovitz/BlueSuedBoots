package entities;
import static utils.Constants.EnemyConstants.*;
import static utils.HelpMethods.*;
import static utils.Constants.Directions.*;

import com.tbonegames.main.Game;

public abstract class Enemy extends Entity{

	
	
	protected int animationIndex, enemyState, enemyType;
	protected int animationTick, animationSpeed = 25;
	protected boolean firstUpdate = true;
	protected boolean inAir = false;
	protected float fallSpeed;
	protected float gravity = 0.04f * Game.SCALE;
	protected float walkSpeed = 0.5f * Game.SCALE;
	protected int walkDir = LEFT;
	protected int tileY;
	protected float attackDistance = (int)Game.TILES_SIZE;
	
	public Enemy(float x, float y, int width, int height, int enemyType) {
		super(x, y, width, height);
		this.enemyType = enemyType;
		initHitBox(x, y, width, height);
	}
	
	protected boolean canSeePlayer(int[][] levelData, Player player) {
		int playerTileY = (int)(player.getHitBox().y/Game.TILES_SIZE);
		if (playerTileY == tileY) {
			if(isPlayerInRange(player)) {
				if(isSightClear(levelData, hitBox, player.hitBox, tileY)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean isPlayerInRange(Player player) {
		int absValue = (int)Math.abs(player.hitBox.x - hitBox.x);
		return absValue <= attackDistance * 5;// can return boolean mathmatical comparisons in Java as well with the return statements.
	}
	
	protected void newState(int enemyState) {
		this.enemyState = enemyState;
		animationTick = 0;
		animationIndex = 0;
	}
	
	protected void updateAnimationTick() {
		animationTick++;
		if (animationTick > animationSpeed) {
			animationTick = 0;
			animationIndex++;
			if(animationIndex >= getSpriteAmount(enemyType, enemyState)) {
				animationIndex = 0;
			}
		}
	}
	
	protected void updateInAir(int [][]levelData) {
		if (canMoveHere(hitBox.x, hitBox.y, hitBox.width, hitBox.height, levelData)) {
			hitBox.y += fallSpeed;
			fallSpeed += gravity;
		}else {
			inAir = false;
			hitBox.y = getEntityYPosUnderRoofOrAboveFloor(hitBox, fallSpeed);
			tileY = (int)(hitBox.y/Game.TILES_SIZE);
		}  
	}
	
	protected void move(int[][] levelData) {
		float xSpeed = 0;
		if (walkDir == LEFT) {
			xSpeed = -walkSpeed;
		} else {
			xSpeed = walkSpeed;
		}
		if (canMoveHere(hitBox.x + xSpeed, hitBox.y, hitBox.width, hitBox.height, levelData)) {
			if (isFloor(hitBox, xSpeed, levelData)) {
				hitBox.x += xSpeed;
				return;
			}
		}
		
		changeWalkDir();
	}
	
	protected void firstUpdateCheck(int[][] levelData) {
		if (firstUpdate) {
			if(!isEntityOnFloor(hitBox, levelData)) {
				inAir = true;
			}
			firstUpdate = false;
	}
	}
	
	
	protected void changeWalkDir() {
		if (walkDir ==LEFT) {
			walkDir = RIGHT;
		} else {
			walkDir = LEFT;
		}
		
	}

	public int getAnimationIndex() {
		return animationIndex;
	}

	public int getEnemyState() {
		return enemyState;
	}
	
}
