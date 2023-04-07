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

//static import to get the idle value
import static utils.Constants.PlayerConstants.*;
import static utils.Constants.Directions.*;

public class GamePanel extends JPanel{

	private MouseInputs mouseInputs;
	private float xDelta = 100, yDelta = 100;
	private BufferedImage img;
	private BufferedImage[][] animations;
	private int animationTick, animationIndex, animationSpeed = 10;
	private int playerAction = IDLE;
	private int playerDir = -1;
	private boolean moving = false;
	
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
		animations = new BufferedImage[9][6];
		
		for (int j = 0; j < animations.length; j++)
		for (int i = 0; i < animations[j].length; i++) {
			animations[j][i] = img.getSubimage(i*64, j*40, 64, 40);
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
	
	
	public void setDirection(int direction) {
		this.playerDir = direction;
		moving = true;
	}
	
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	private void updateAnimationTick() {
		animationTick++;
		if (animationTick >= animationSpeed) {
			animationTick = 0;
			animationIndex++;
			if (animationIndex >= getSpriteAmount(playerAction)) {
				animationIndex = 0;
			}
		}
	}
	
	private void setAnimation() {
		if (moving) {
			playerAction = RUNNING;
		} else {
			playerAction = IDLE;
		}
	}
	
	private void updatePos() {
		if (moving) {
			switch(playerDir) {
			case LEFT:
				xDelta -= 5;
				break;
			case UP:
				yDelta -= 5;
				break;
			case RIGHT:
				xDelta += 5;
				break;
			case DOWN:
				yDelta += 5;
				break;
			
			}
		}
	}
	
	//This gets called whenever we press the play button.
	//Graphics allows us to draw
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		updateAnimationTick();
		
		setAnimation();
		updatePos();
		
		//with buffered images you can draw a section of the image, ie tiles for the game to work.
		//also the method with its parameters filled out can also be used to pass an image
		//128 is the width and the heeight is 80
		g.drawImage(animations[playerAction][animationIndex], (int)xDelta, (int)yDelta, 256, 160, null);
	}
	
	
	
}
