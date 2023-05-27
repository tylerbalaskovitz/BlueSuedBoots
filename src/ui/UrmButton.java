package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static utils.Constants.UI.URMButtons.*;
import utils.LoadSave;

public class UrmButton extends PauseButton{

	private int rowIndex, index;
	private BufferedImage[] images;
	private boolean mouseOver, mousePressed;
	
	public UrmButton(int x, int y, int width, int height, int rowIndex) {
		super(x, y, width, height);
		this.rowIndex = rowIndex;
		loadImages();
	}
	
	private void loadImages() {
		BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.URM_BUTTONS);
		images = new BufferedImage[3];
		for (int i = 0; i < images.length; i++)
			images[i] = temp.getSubimage(i * URM_DEFAULT_SIZE, rowIndex * URM_DEFAULT_SIZE, URM_DEFAULT_SIZE, URM_DEFAULT_SIZE);
		
	}

	public void update() {
		index = 0;
		if (mouseOver)
			index = 0;
		if (mousePressed)
			index = 1;
	}
	
	public void draw(Graphics g) {
		g.drawImage(images[index], x, y, URM_SIZE, URM_SIZE, null);
		
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
