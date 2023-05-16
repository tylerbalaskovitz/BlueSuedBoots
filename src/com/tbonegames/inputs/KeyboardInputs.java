package com.tbonegames.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static utils.Constants.Directions.*;

import com.tbonegames.main.GamePanel;

import gamestates.Gamestate;

public class KeyboardInputs implements KeyListener{
	
	
//ctrl + 1 is to add unimplemented methods easily without having to click anything

	private GamePanel gamePanel;
	public KeyboardInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(Gamestate.state) {
		case MENU:
			gamePanel.getGame().getMenu().keyReleased(e);
			break;
		case PLAYING:
			gamePanel.getGame().getPlaying().keyReleased(e);
			break;
		default:
			break;
		
		}
		
	}
	

	@Override
	public void keyReleased(KeyEvent e) {
			switch(Gamestate.state) {
			case MENU:
				gamePanel.getGame().getMenu().keyPressed(e);
				break;
			case PLAYING:
				gamePanel.getGame().getPlaying().keyPressed(e);
				break;
			default:
				break;
			
			}
		}
		

}