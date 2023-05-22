package gamestates;

import java.awt.event.MouseEvent;

import com.tbonegames.main.Game;

import ui.MenuButton;

//Designed to be the super class to all of our game states.
public class State {

	protected Game game;
	
	public State(Game game) {
		this.game = game;
		
	}
	
	public boolean isIn(MouseEvent e, MenuButton mb) {
		return mb.getBounds().contains(e.getX(), e.getY());
	}
	
	public Game getGame() {
		return game;
	}
	
}
