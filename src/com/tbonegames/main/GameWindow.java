package com.tbonegames.main;

import javax.swing.JFrame;

public class GameWindow{

	private JFrame jFrame;
	
	public GameWindow(GamePanel gamePanel) {
		
		jFrame = new JFrame();
		
		
		
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.add(gamePanel);
		jFrame.setLocationRelativeTo(null);
		jFrame.pack();
		jFrame.setVisible(true);
	}
	
}
