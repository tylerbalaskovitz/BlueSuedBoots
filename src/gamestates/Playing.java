package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.tbonegames.main.Game;

import entities.Player;
import levels.LevelManager;
import ui.PauseOverlay;

public class Playing extends State implements StateMethods{

	private Player player;
	private LevelManager levelManager;
	private PauseOverlay pauseOverlay;
	private boolean paused = true; 
	
	public Playing(Game game) {
		super(game);
		initClasses();
	}

	private void initClasses() {

		levelManager = new LevelManager(game);
		player = new Player(200, 200, (int)(64* Game.SCALE), (int)(40*Game.SCALE));
		player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
		pauseOverlay = new PauseOverlay();
	}
	
	public Player getPlayer() {
		return player;
	}

	public void windowFocusLost() {
		player.resetDirBooleans();
		
	}

	@Override
	public void update() {
		levelManager.update();
		player.update();
		pauseOverlay.update();
		
	}

	@Override
	public void draw(Graphics g) {
		levelManager.draw(g);
		player.render(g);
		pauseOverlay.draw(g);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			player.setAttacking(true);
		}
		
		
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
		case KeyEvent.VK_BACK_SPACE:Gamestate.state = Gamestate.MENU;
	
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

}
