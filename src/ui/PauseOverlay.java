package ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import static utils.Constants.UI.PauseButtons.*;
import static utils.Constants.UI.URMButtons.*;
import com.tbonegames.main.Game;

import utils.LoadSave;

public class PauseOverlay {

	private BufferedImage backgroundImage;
	private int backgroundX, backgroundY, backgroundWidth, backgroundHeight;
	private SoundButton musicButton, sfxButton;
	private UrmButton menuB, replayB, unpauseB;
	
	
	public PauseOverlay() {
	loadBackground();
	createSoundButtons();
	createUrmButtons();
		
	}
	
	private void createUrmButtons() {
		int menuX = (int)(313 * Game.SCALE);
		int replayX  = (int)(387 * Game.SCALE);
		int unpauseX = (int)(462 * Game.SCALE);
		int bY = (int)(325 * Game.SCALE);
		
		menuB = new UrmButton(menuX, bY, URM_SIZE, URM_SIZE, 2);
		replayB = new UrmButton(replayX, bY, URM_SIZE, URM_SIZE, 1);
		unpauseB = new UrmButton(unpauseX, bY, URM_SIZE, URM_SIZE, 0);
		
	}

	private void createSoundButtons() {
		
		int soundX = (int)(450 * Game.SCALE);
		int musicY = (int)(140 * Game.SCALE);
		int sfxY = (int) (186 * Game.SCALE);
		musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
		sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
	}
	
	private void loadBackground() {
		backgroundImage = LoadSave.getSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
		backgroundWidth = (int)(backgroundImage.getWidth() * Game.SCALE);
		backgroundHeight = (int)(backgroundImage.getHeight() * Game.SCALE);
		backgroundX = Game.GAME_WIDTH/2 - backgroundWidth/2;
		backgroundY = (int)(25 * Game.SCALE);
		
	}

	public void update() {
		musicButton.update();
		sfxButton.update();
		menuB.update();
		replayB.update();
		unpauseB.update();
	}
	
	public void draw(Graphics g) {
		//Background
		g.drawImage(backgroundImage,backgroundX, backgroundY, backgroundWidth, backgroundHeight, null);
		
		//Sound buttons
		musicButton.draw(g);
		sfxButton.draw(g);
		
		//Urm Buttons
		menuB.draw(g);
		replayB.draw(g);
		unpauseB.draw(g);
	}
	
	public void mouseDragged(MouseEvent e) {
		
	}
	
	public void mouseMoved(MouseEvent e) {
		musicButton.setMouseOver(false);
		sfxButton.setMouseOver(false);
		
		if (isIn(e, musicButton))
			musicButton.setMouseOver(true);
		else if(isIn(e, sfxButton))
				sfxButton.setMouseOver(true);
		
	}
	
	public void mousePressed(MouseEvent e) {
		if (isIn(e, musicButton))
			musicButton.setMousePressed(true);
		else if(isIn(e, sfxButton))
				sfxButton.setMousePressed(true);
	}
	
	public void mouseReleased(MouseEvent e) {
		if (isIn(e, musicButton)) {
			if (musicButton.isMousePressed()) 
				musicButton.setMuted(!musicButton.isMuted());
			
		} else if (isIn(e, sfxButton))
			if (sfxButton.isMousePressed())
				sfxButton.setMuted(!sfxButton.isMuted());
		
		musicButton.resetBools();
		sfxButton.resetBools();
	}
	
	private boolean isIn(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(), e.getY());
		
		
	}
	

}
