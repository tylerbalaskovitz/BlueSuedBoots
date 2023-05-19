package ui;

import java.awt.image.BufferedImage;

import gamestates.Gamestate;

public class MenuButton {

	private int xPos, yPos, rowIndex;
	private Gamestate state;
	private BufferedImage[] images;
	
	
	public MenuButton(int xPos, int yPos, int rowIndex, Gamestate state) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.rowIndex = rowIndex;
		this.state = state;
		loadImages();
	}
	
	private void loadImages() {
		images = new BufferedImage[3];
	}
	
}
