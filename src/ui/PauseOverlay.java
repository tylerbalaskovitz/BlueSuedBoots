package ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.tbonegames.main.Game;

import utils.LoadSave;

public class PauseOverlay {

	private BufferedImage backgroundImage;
	private int backgroundX, backgroundY, backgroundWidth, backgroundHeight;
	
	
	public PauseOverlay() {
	loadBackground();
		
	}
	
	private void loadBackground() {
		backgroundImage = LoadSave.getSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
		backgroundWidth = (int)(backgroundImage.getWidth() * Game.SCALE);
		backgroundHeight = (int)(backgroundImage.getHeight() * Game.SCALE);
		backgroundX = Game.GAME_WIDTH/2 - backgroundWidth/2;
		backgroundY = (int)(25 * Game.SCALE);
		
	}

	public void update() {
		
	}
	
	public void draw(Graphics g) {
		g.drawImage(backgroundImage,backgroundX, backgroundY, backgroundWidth, backgroundHeight, null);
	}
	
	public void mouseDragged(MouseEvent e) {
		
	}
	
	public void mouseMoved(MouseEvent e) {
		
	}
	
	public void mousePressed(MouseEvent e) {
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	

}
