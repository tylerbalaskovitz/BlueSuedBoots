package entities;
import static utils.Constants.EnemyConstants.*;
import static utils.HelpMethods.*;

import com.tbonegames.main.Game;

public abstract class Enemy extends Entity{

	
	
	private int animationIndex, enemyState, enemyType;
	private int animationTick, animationSpeed = 25;
	private boolean firstUpdate = true;
	private boolean inAir = false;
	private float fallSpeed;
	private float gravity = 0.04f * Game.SCALE;
	
	public Enemy(float x, float y, int width, int height, int enemyType) {
		super(x, y, width, height);
		this.enemyType = enemyType;
		initHitBox(x, y, width, height);
	}
	
	private void updateAnimationTick() {
		animationTick++;
		if (animationTick > animationSpeed) {
			animationTick = 0;
			animationIndex++;
			if(animationIndex >= getSpriteAmount(enemyType, enemyState)) {
				animationIndex = 0;
			}
		}
	}
	
	public void update(int[][] levelData) {
		updateMove(levelData);
		updateAnimationTick();
	}
	
	private void updateMove(int[][] levelData) {
		if (firstUpdate) {
			if(!isEntityOnFloor(hitBox, levelData)) {
				inAir = true;
			}
		}
		if (inAir) {
			if (canMoveHere(hitBox.x, hitBox.y, hitBox.width, hitBox.height, levelData)) {
				hitBox.y += fallSpeed;
				fallSpeed += gravity;
			}
		} else {
			inAir = false;
			hitBox.y = getEntityYPosUnderRoofOrAboveFloor(hitBox, fallSpeed);
			
		}
		
	}
	
	public int getAnimationIndex() {
		return animationIndex;
	}

	public int getEnemyState() {
		return enemyState;
	}
	
}
