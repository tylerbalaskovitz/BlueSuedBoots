package com.tbonegames.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

import com.tbonegames.inputs.KeyboardInputs;
import com.tbonegames.inputs.MouseInputs;

public class GamePanel extends JPanel{

	private MouseInputs mouseInputs;
	private float xDelta = 100, yDelta = 100;
	private float xDir = .001f, yDir = .001f;
	private Color color;
	private int frames = 0;
	private long lastCheck = 0;
	private Random random;
	
	//the panel is the picture
	public GamePanel() {
		random = new Random();
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
		g.setColor(color);
		
		g.fillRect((int)xDelta, (int)yDelta, 200, 50);
		frames++;
		if(System.currentTimeMillis() - lastCheck>= 1000) {
			lastCheck = System.currentTimeMillis();
			System.out.println("FPS: " + frames);
			frames = 0;
		}
	}
	
	public void updateRectangle() {
		xDelta+= xDir;
		if (xDelta>400||xDelta<0) {
			xDir*=-1;
			color = getRndColor();
		}
		
		yDelta+= yDir;
		if (yDelta > 400||yDelta<0) {
			yDir*=-1;
			color = getRndColor();
		}
	}
	
	private Color getRndColor() {
		int r=random.nextInt(255);
		int g=random.nextInt(255);
		int b=random.nextInt(255);
		
		return new Color(r,g,b);
		
	}
	
}
