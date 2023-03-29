package com.tbonegames.main;

import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel{

	//the panel is the picture
	public GamePanel() {
		
	}
	
	//This gets called whenever we press the play button.
	//Graphics allows us to draw
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.fillRect(100, 100, 200, 50);
	}
	
}
