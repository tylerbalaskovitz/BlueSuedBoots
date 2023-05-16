package com.tbonegames.main;

import java.awt.Graphics;

import gamestates.Gamestate;
import gamestates.Menu;
import gamestates.Playing;

public class Game implements Runnable{

	private GamePanel gamePanel;
	private GameWindow gameWindow;
	private Thread gameThread;
	private final int FPS_SET = 120;
	private final int UPS_SET = 200;
	
	private Playing playing;
	private Menu menu;
	
	public final static int TILES_DEFAULT_SIZE = 32;
	public final static float SCALE = 1.5f;
	public final static int TILES_IN_WIDTH = 26;
	public final static int TILES_IN_HEIGHT = 14;
	public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
	public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
	public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
	
	public Game() {
		initClasses();
		
		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();
		
		
		
		startGameLoop();
	}
	
	private void initClasses() {
		menu = new Menu(this);
		playing = new Playing(this);
	}
	
	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void update() {

		switch(Gamestate.state) {
		case MENU:
			menu.update();
			break;
		case PLAYING:
			playing.update();
			break;
		default:
			break;
		
		}
		
	}
	
	public void render(Graphics g) {
		
		switch(Gamestate.state) {
		case MENU:
			menu.draw(g);
			break;
		case PLAYING:
			playing.draw(g);
			break;
		default:
			break;
		
		}

		
	}

	@Override
	public void run() {

		double timePerFrame = 1000000000.0/FPS_SET;
		//time of the frequency
		double timePerUpdate = 1000000000.0/UPS_SET;
		
		long previousTime = System.nanoTime();
		
		int updates = 0;
		int frames = 0;
		long lastCheck = System.currentTimeMillis();
		
		double deltaU = 0;
		double deltaF = 0;
		while(true) {

			
			//currently this game loop slowly loses time over a longer period of time due to the fact that 
			//now - lastFrame can exceed the timePerFrame double, and creating code inefficies that need to be
			//corrected. 
			long currentTime = System.nanoTime();
			
			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;
			
			//this allows the variable to be held and controlled so there isn't any data loss, and allows the next update
			// to come sooner creating an offset in the update loop regarding deltaU.
			if (deltaU >= 1) {
				update();
				updates++;
				deltaU--;
			}
			
			if (deltaF >= 1) {
				gamePanel.repaint();
				deltaF--;
				frames++;
			}
			
//		if(now - lastFrame >= timePerFrame) {
//			gamePanel.repaint();
//			lastFrame = now;
//			frames++;
//		}
		
		if(System.currentTimeMillis() - lastCheck>= 1000) {
			lastCheck = System.currentTimeMillis();
			System.out.println("FPS: " + frames + "UPS Updates: " + updates);
			frames = 0;
			updates = 0;
		}
			
		}
		
	}
	

	public void windowFocusLost() {
		if(Gamestate.state == Gamestate.PLAYING) {
			playing.getPlayer().resetDirBooleans();
		}
	}
	
	public Menu getMenu() {
		return menu;
	}
	
	public Playing getPlaying() {
		return playing;
	}
	
	
	
}
