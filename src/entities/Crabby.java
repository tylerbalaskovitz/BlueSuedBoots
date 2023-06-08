package entities;

import static utils.Constants.EnemyConstants.*;

import com.tbonegames.main.Game;

public class Crabby extends Enemy{

	public Crabby(float x, float y) {
		super(x, y, CRABBY_WIDTH, CRABBY_HEIGHT, CRABBY);
		initHitBox(x, y, (int)(22 * Game.SCALE), (int)(Game.SCALE* 19));
		
	}

}
