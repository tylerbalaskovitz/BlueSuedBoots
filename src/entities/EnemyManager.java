package entities;

import static utils.Constants.EnemyConstants.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Playing;
import utils.LoadSave;

public class EnemyManager {

	private Playing playing;
	private BufferedImage[][] crabbyArray;
	private ArrayList<Crabby> crabbies = new ArrayList<>();
	
	public EnemyManager(Playing playing) {
		this.playing = playing;
		loadEnemyImages();
		addEnemies();
		
	}
	
	private void addEnemies() {
		crabbies = LoadSave.getCrabs();
		System.out.println("Size of crabs: " + crabbies.size());
		
	}

	public void update(int[][] levelData) {
		for (Crabby c: crabbies)
			c.update(levelData);
		
	}
	
	public void draw (Graphics g, int xLevelOffset) {
		drawCrabs(g, xLevelOffset);
		
	}

	private void drawCrabs(Graphics g, int xLevelOffset) {
		for (Crabby c: crabbies)
			g.drawImage(crabbyArray[c.getEnemyState()][c.getAnimationIndex()], (int)c.getHitBox().x - xLevelOffset, (int)c.getHitBox().y, CRABBY_WIDTH, CRABBY_HEIGHT, null);
		
	}

	private void loadEnemyImages() {
		crabbyArray = new BufferedImage[5][9];
		BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.CRABBY_SPRITE);
		for (int j = 0; j < crabbyArray.length; j++)
			for (int i = 0; i < crabbyArray[j].length; i++)
				crabbyArray[j][i] = temp.getSubimage(i * CRABBY_WIDTH_DEFAULT, j * CRABBY_HEIGHT_DEFAULT, CRABBY_WIDTH_DEFAULT, CRABBY_HEIGHT_DEFAULT);
		
	}
	
}
