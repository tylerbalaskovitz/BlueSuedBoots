package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import com.tbonegames.main.Game;
import utils.LoadSave;
import static com.tbonegames.main.Game.*;

public class LevelManager {

	private Game game;
	private BufferedImage levelSprite[];
	private Level levelOne;
	
	public LevelManager(Game game) {
		this.game = game;
	//	levelSprite = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
		importOutsideSprites();
		levelOne = new Level(LoadSave.getLevelData());
	}
	
	private void importOutsideSprites() {
		BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
		levelSprite = new BufferedImage[48];
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 12; i++) {
				int index = j*12 + i;
				levelSprite[index] = img.getSubimage(i*32, j*32, 32, 32);
			}
		}
		
	}

	public void draw(Graphics g) {
		for (int j = 0; j < Game.TILES_IN_HEIGHT; j++) {
			for (int i = 0; i < Game.TILES_IN_WIDTH; i++) {
				int index = levelOne.getSpriteIndex(i, j);
				g.drawImage(levelSprite[index], i*TILES_SIZE, j*TILES_SIZE, TILES_SIZE, TILES_SIZE, null);
			}
		}
		
	}
	
	public void update() {
		
	}
	
	public Level getCurrentLevel() {
		return levelOne;
	}
	
}
