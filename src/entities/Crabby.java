package entities;

import static utils.Constants.Directions.LEFT;
import static utils.Constants.EnemyConstants.*;
import static utils.HelpMethods.canMoveHere;
import static utils.HelpMethods.getEntityYPosUnderRoofOrAboveFloor;
import static utils.HelpMethods.isEntityOnFloor;
import static utils.HelpMethods.isFloor;

import com.tbonegames.main.Game;

public class Crabby extends Enemy{

	public Crabby(float x, float y) {
		super(x, y, CRABBY_WIDTH, CRABBY_HEIGHT, CRABBY);
		initHitBox(x, y, (int)(22 * Game.SCALE), (int)(Game.SCALE* 19));
		
	}
	
	public void update(int[][] levelData) {
		updateMove(levelData);
		updateAnimationTick();
	}

	private void updateMove(int[][] levelData) {
		if (firstUpdate) {
			firstUpdateCheck(levelData);
		}
		if (inAir) {
			updateInAir(levelData);
			
		} else {
			switch(enemyState) {
			case IDLE: enemyState = RUNNING; break;
			case RUNNING: 
				move(levelData);
				break;
			}
		}
		
	}
	
}
