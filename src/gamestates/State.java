package gamestates;

import com.tbonegames.main.Game;

//Designed to be the super class to all of our game states.
public class State {

	protected Game game;
	
	public State(Game game) {
		this.game = game;
		
	}
	
	public Game getGame() {
		return game;
	}
	
}
