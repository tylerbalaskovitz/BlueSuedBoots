package gamestates;

import static utils.Constants.Environment.BIG_CLOUD_HEIGHT;
import static utils.Constants.Environment.BIG_CLOUD_WIDTH;
import static utils.Constants.Environment.SMALL_CLOUD_HEIGHT;
import static utils.Constants.Environment.SMALL_CLOUD_WIDTH;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.tbonegames.main.Game;

import entities.EnemyManager;
import entities.Player;
import levels.LevelManager;
import ui.PauseOverlay;
import utils.LoadSave;

public class Playing extends State implements StateMethods{

	private Player player;
	private LevelManager levelManager;
	private EnemyManager enemyManager;
	private PauseOverlay pauseOverlay;
	private boolean paused = false; 
	
	//critical for offsetting and scrolling within the game. 
	private int xLevelOffset;
	private int leftBorder = (int)(0.2*Game.GAME_WIDTH);
	private int rightBorder = (int)(0.8 * Game.GAME_WIDTH);
	private int levelTilesWide = LoadSave.getLevelData()[0].length;
	private int maxTilesOffset = levelTilesWide - Game.TILES_IN_WIDTH;
	private int maxLevelOffsetX = maxTilesOffset * Game.TILES_SIZE;
	
	private BufferedImage backgroundImage, bigCloud, smallCloud;
	private int[]smallCloudsPos;
	private Random random = new Random();
	
	public Playing(Game game) {
		super(game);
		initClasses();
		backgroundImage = LoadSave.getSpriteAtlas(LoadSave.PLAYING_BACKGROUND_IMAGE);
		bigCloud = LoadSave.getSpriteAtlas(LoadSave.BIG_CLOUDS);
		smallCloud = LoadSave.getSpriteAtlas(LoadSave.SMALL_CLOUDS);
		smallCloudsPos = new int [8];
		for (int i = 0; i < smallCloudsPos.length; i++)
			smallCloudsPos[i] = (int)(90* Game.SCALE) + random.nextInt((int)(100*Game.SCALE));
	}

	private void initClasses() {

		levelManager = new LevelManager(game);
		enemyManager = new EnemyManager(this);
		player = new Player(200, 200, (int)(64* Game.SCALE), (int)(40*Game.SCALE));
		player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
		pauseOverlay = new PauseOverlay(this);
	}
	
	public Player getPlayer() {
		return player;
	}

	public void windowFocusLost() {
		player.resetDirBooleans();
		
	}

	@Override
	public void update() {
		if (!paused) {
			levelManager.update();
			player.update();
			enemyManager.update();
			checkCloseToBorder();
		} else {
			pauseOverlay.update();
		}
	}

	private void checkCloseToBorder() {
		int playerX = (int)player.getHitBox().x;
		int diff = playerX - xLevelOffset;
		
		if (diff > rightBorder) {
			xLevelOffset += diff - rightBorder;
		} else if (diff < leftBorder){
			xLevelOffset += diff - leftBorder;
		}
		
		if(xLevelOffset > maxLevelOffsetX) {
			xLevelOffset = maxLevelOffsetX;
		} else if (xLevelOffset < 0) {
			xLevelOffset = 0;
		}
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(backgroundImage, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
		
		drawClouds(g);
		
		levelManager.draw(g, xLevelOffset);
		player.render(g, xLevelOffset);
		enemyManager.draw(g, xLevelOffset);
		
		if (paused) {
			g.setColor(new Color(0, 0, 0, 100));
			g.fillRect(0, 0, game.GAME_WIDTH, Game.GAME_HEIGHT);
			pauseOverlay.draw(g);
		}
	}

	private void drawClouds(Graphics g) {
		
		for (int i = 0; i < 3; i++)
		g.drawImage(bigCloud, i * BIG_CLOUD_WIDTH - (int)(xLevelOffset * 0.3), (int)(204 * Game.SCALE), BIG_CLOUD_WIDTH, BIG_CLOUD_HEIGHT, null);
		
		for (int i = 0; i < smallCloudsPos.length; i++) {
		g.drawImage(smallCloud, SMALL_CLOUD_WIDTH * 4 * i - (int)(xLevelOffset * 0.6), smallCloudsPos[i], SMALL_CLOUD_WIDTH, SMALL_CLOUD_HEIGHT, null);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			player.setAttacking(true);
		}
		
		
	}
	
	public void mouseDragged(MouseEvent e) {
		if(paused)
			pauseOverlay.mouseDragged(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(paused)
			pauseOverlay.mousePressed(e);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(paused)
			pauseOverlay.mouseReleased(e);
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(paused)
			pauseOverlay.mouseMoved(e);
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:	player.setUp(true);break;
		case KeyEvent.VK_A:	player.setLeft(true);break;
		case KeyEvent.VK_S:	player.setDown(true);break;
		case KeyEvent.VK_D:	player.setRight(true);break;
		case KeyEvent.VK_SPACE: player.setJump(true);break;
		case KeyEvent.VK_BACK_SPACE:Gamestate.state = Gamestate.MENU;break;
		case KeyEvent.VK_ESCAPE:paused = !paused;break;
	
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:	player.setUp(false);break;
		case KeyEvent.VK_A:	player.setLeft(false);break;
		case KeyEvent.VK_S:	player.setDown(false);break;
		case KeyEvent.VK_D:	player.setRight(false);break;
		case KeyEvent.VK_SPACE: player.setJump(false);break;
			
		
		}
		
	}
	
	public void unpauseGame() {
		paused = false;
	}

}
