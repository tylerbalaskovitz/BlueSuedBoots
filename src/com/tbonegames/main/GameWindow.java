package com.tbonegames.main;

import javax.swing.JFrame;

public class GameWindow{

	private JFrame jFrame;
	
	public GameWindow() {
		
		jFrame = new JFrame();
		jFrame.setSize(400, 400);
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
}
