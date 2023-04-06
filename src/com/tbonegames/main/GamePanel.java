package com.tbonegames.main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.tbonegames.inputs.KeyboardInputs;
import com.tbonegames.inputs.MouseInputs;

public class GamePanel extends JPanel{

	private MouseInputs mouseInputs;
	private float xDelta = 100, yDelta = 100;
	private BufferedImage img;
	private BufferedImage[] idleAnimation;
	
	//the panel is the picture
	public GamePanel() {
		
		
		mouseInputs = new MouseInputs(this);
		importImg();
		loadAnimations();
		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}
	
	public void loadAnimations() {
		idleAnimation = new BufferedImage[5];
		
		for (int i = 0; i < idleAnimation.length; i++) {
			idleAnimation[i] = img.getSubimage(i*64, 0, 64, 40);
		}
	}
	
	public void importImg() {
		InputStream is = getClass().getResourceAsStream("/player_sprites.png");
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				//close the stream to free up resources.
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void setPanelSize() {
		Dimension size = new Dimension(1280, 720);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
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
		
		//with buffered images you can draw a section of the image, ie tiles for the game to work.
		//also the method with its parameters filled out can also be used to pass an image
		//128 is the width and the heeight is 80
		g.drawImage(idleAnimation[3], (int)xDelta, (int)yDelta, 128, 80, null);
	}
	
	
	
}
