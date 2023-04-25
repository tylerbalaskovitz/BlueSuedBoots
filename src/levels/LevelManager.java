package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.tbonegames.main.Game;

import utils.LoadSave;

public class LevelManager {

	private Game game;
	private BufferedImage levelSprite;
	
	public LevelManager(Game game) {
		this.game = game;
		levelSprite = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
		
	}
	
	public void draw(Graphics g) {
		g.drawImage(levelSprite, 0, 0, null);
	}
	
	public void update() {
		
	}
	
}
