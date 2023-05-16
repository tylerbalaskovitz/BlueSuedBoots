package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.tbonegames.main.Game;

import entities.Player;
import levels.LevelManager;

public class Playing extends State implements StateMethods{
	public Playing(Game game) {
		super(game);
		initClasses();
	}

	private Player player;
	private LevelManager levelManager;
	
	private void initClasses() {

		levelManager = new LevelManager(game);
		player = new Player(200, 200, (int)(64* Game.SCALE), (int)(40*Game.SCALE));
		player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
	}
	
	public Player getPlayer() {
		return player;
	}

	public void windowFocusLost() {
		player.resetDirBooleans();
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
