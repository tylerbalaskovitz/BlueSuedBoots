package com.tbonegames.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
		case KeyEvent.VK_W:gamePanel.changeYDelta(-5);break;
		case KeyEvent.VK_A:gamePanel.changeXDelta(-5);break;
		case KeyEvent.VK_S:gamePanel.changeYDelta(5);break;
		case KeyEvent.VK_D:gamePanel.changeXDelta(5);;break;
			
		}
		
	}
	

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

}
