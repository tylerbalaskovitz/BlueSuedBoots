package com.tbonegames.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static utils.Constants.Directions.*;

import com.tbonegames.main.GamePanel;

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
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:gamePanel.getGame().getPlayer().setDirection(UP);break;
		case KeyEvent.VK_A:gamePanel.getGame().getPlayer().setDirection(LEFT);break;
		case KeyEvent.VK_S:gamePanel.getGame().getPlayer().setDirection(DOWN);break;
		case KeyEvent.VK_D:gamePanel.getGame().getPlayer().setDirection(RIGHT);break;
			
		}
		
	}
	

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:;
		case KeyEvent.VK_A:
		case KeyEvent.VK_S:
		case KeyEvent.VK_D:
			gamePanel.getGame().getPlayer().setMoving(false);
			break;
			
		}
		
	}

}
