package ui;

import static utils.Constants.UI.PauseButtons.SOUND_SIZE_DEFAULT;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utils.LoadSave;

public class SoundButton extends PauseButton{

	private BufferedImage[][] soundImages;
	
	public SoundButton(int x, int y, int width, int height) {
		super(x, y, width, height);
		
		loadSoundImages();
		
	}
	
	private void loadSoundImages() {
		BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.SOUND_BUTTONS);
		soundImages = new BufferedImage[2][3];
		
		for (int j = 0; j < soundImages.length; j++) {
			for (int i = 0; i < soundImages[j].length; i++) {
				soundImages[j][i] = temp.getSubimage(i * SOUND_SIZE_DEFAULT, j * SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT);
			}
		}
		
		
	}
	
	public void update() {
		
	}
	
	public void draw(Graphics g) {
		g.drawImage(soundImages[0][0], x, y, width, height, null);
	}

}
