package gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.tbonegames.main.Game;

import entities.Player;
import levels.LevelManager;
import ui.PauseOverlay;
import utils.LoadSave;

public class Playing extends State implements StateMethods{

	private Player player;
	private LevelManager levelManager;
	private PauseOverlay pauseOverlay;
	private boolean paused = false; 
	
	//critical for offsetting and scrolling within the game. 
	private int xLevelOffset;
	private int leftBorder = (int)(0.2*Game.GAME_WIDTH);
	private int rightBorder = (int)(0.8 * Game.GAME_WIDTH);
	private int levelTilesWide = LoadSave.getLevelData()[0].length;
	private int maxTilesOffset = levelTilesWide * Game.TILES_IN_WIDTH;
	private int maxLevelOffsetX = maxTilesOffset * Game.TILES_SIZE;
	
	private BufferedImage backgroundImage, bigCloud;
	
	public Playing(Game game) {
		super(game);
		initClasses();
		backgroundImage = LoadSave.getSpriteAtlas(LoadSave.PLAYING_BACKGROUND_IMAGE);
	}

	private void initClasses() {

		levelManager = new LevelManager(game);
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
		
		levelManager.draw(g, xLevelOffset);
		player.render(g, xLevelOffset);
		if (paused) {
			g.setColor(new Color(0, 0, 0, 100));
			g.fillRect(0, 0, game.GAME_WIDTH, Game.GAME_HEIGHT);
			pauseOverlay.draw(g);
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
