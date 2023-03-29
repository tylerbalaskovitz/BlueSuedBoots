package com.tbonegames.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener{
//ctrl + 1 is to add unimplemented methods easily without having to click anything
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:System.out.println("W was pressed");break;
		case KeyEvent.VK_A:System.out.println("A was pressed");break;
		case KeyEvent.VK_S:System.out.println("S was pressed");break;
		case KeyEvent.VK_D:System.out.println("D was pressed");break;
			
		}
		
	}
	

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

}
