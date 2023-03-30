package com.tbonegames.main;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.tbonegames.inputs.KeyboardInputs;
import com.tbonegames.inputs.MouseInputs;

public class GamePanel extends JPanel{

	private MouseInputs mouseInputs;
	private float xDelta = 100, yDelta = 100;
	private float xDir = .001f, yDir = .001f;
	private int frames = 0;
	private long lastCheck = 0;
	
	//the panel is the picture
	public GamePanel() {
		
		mouseInputs = new MouseInputs(this);
		
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}
	
	public void changeXDelta(int value) {
		this.xDelta += value;
		
	}
	
	public void changeYDelta(int value) {
		this.yDelta += value;
	}
	
	public void setRectPos(int x, int y) {
		this.xDelta = x;
		this.yDelta = y;
	}
	
	//This gets called whenever we press the play button.
	//Graphics allows us to draw
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		updateRectangle();
		
		g.fillRect((int)xDelta, (int)yDelta, 200, 50);
		frames++;
		if(System.currentTimeMillis() - lastCheck>= 1000) {
			lastCheck = System.currentTimeMillis();
			System.out.println("FPS: " + frames);
			frames = 0;
		}
		repaint();
	}
	
	public void updateRectangle() {
		xDelta+= xDir;
		if (xDelta>400||xDelta<0) {
			xDir*=-1;
		}
		
		yDelta+= yDir;
		if (yDelta > 400||yDelta<0) {
			yDir*=-1;
		}
	}
	
}
