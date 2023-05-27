package ui;

import static utils.Constants.UI.URMButtons.URM_DEFAULT_SIZE;
import static utils.Constants.UI.URMButtons.URM_SIZE;
import static utils.Constants.UI.VolumeButtons.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utils.LoadSave;

public class VolumeButton extends PauseButton{

	
	private BufferedImage[] images;
	private BufferedImage slider;
	private int index = 0;
	private boolean mouseOver, mousePressed;
	private int buttonX;
	
	public VolumeButton(int x, int y, int width, int height) {
		super(x + width/2, y, VOLUME_WIDTH, height);
		//the bounds are created in the super class from passing the values above into the VolumeButton constructor. 
		loadImages();
	}
	
	private void loadImages() {
		BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.URM_BUTTONS);
		images = new BufferedImage[3];
		for (int i = 0; i < images.length; i++)
			images[i] = temp.getSubimage(i * VOLUME_DEFAULT_WIDTH, 0, VOLUME_DEFAULT_WIDTH, VOLUME_DEFAULT_HEIGHT);
		
		slider = temp.getSubimage(3 * VOLUME_DEFAULT_WIDTH, 0, SLIDER_DEFAULT_WIDTH, VOLUME_DEFAULT_HEIGHT);
	}

	public void update() {
		index = 0;
		if (mouseOver)
			index = 1;
		if (mousePressed)
			index = 2;
		
	}
	
	public void draw(Graphics g) {
		
	}
	
	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
		
	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public boolean isMousePressed() {
		return mousePressed;
	}

	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}
	
	

}
