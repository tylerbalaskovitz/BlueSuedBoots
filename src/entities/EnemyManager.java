package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gamestates.Playing;
import utils.LoadSave;
import static utils.Constants.EnemyConstants.*;

public class EnemyManager {

	private Playing playing;
	private BufferedImage[][] crabbyArray;
	
	public EnemyManager(Playing playing) {
		this.playing = playing;
		loadEnemyImages();
	}
	
	public void update() {
		
	}
	
	public void draw (Graphics g) {
		
	}

	private void loadEnemyImages() {
		crabbyArray = new BufferedImage[5][9];
		BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.CRABBY_SPRITE);
		for (int j = 0; j < crabbyArray.length; j++)
			for (int i = 0; i < crabbyArray[j].length; i++)
				crabbyArray[j][i] = temp.getSubimage(i * CRABBY_WIDTH_DEFAULT, j * CRABBY_HEIGHT_DEFAULT, CRABBY_WIDTH_DEFAULT, CRABBY_HEIGHT_DEFAULT);
		
	}
	
}
