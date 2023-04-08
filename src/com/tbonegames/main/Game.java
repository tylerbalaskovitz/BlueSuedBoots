package com.tbonegames.main;

public class Game implements Runnable{

	private GamePanel gamePanel;
	private GameWindow gameWindow;
	private Thread gameThread;
	private final int FPS_SET = 120;
	private final int UPS_SET = 200;
	
	public Game() {
		
		gamePanel = new GamePanel();
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();
		startGameLoop();
		
	}
	
	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {

		double timePerFrame = 1000000000.0/FPS_SET;
		
		//time of the frequency
		double timePerUpdate = 1000000000.0/UPS_SET;
		long lastFrame = System.nanoTime();
		long now = System.nanoTime();
		
		long previousTime = System.nanoTime();
		
		int updates = 0;
		int frames = 0;
		long lastCheck = System.currentTimeMillis();
		
		double deltaU = 0;
		while(true) {

			now = System.nanoTime();
			
			//currently this game loop slowly loses time over a longer period of time due to the fact that 
			//now - lastFrame can exceed the timePerFrame double, and creating code inefficies that need to be
			//corrected. 
			long currentTime = System.nanoTime();
			
			deltaU += (currentTime - previousTime) / timePerUpdate;
			
			//this allows the variable to be held and controlled so there isn't any data loss, and allows the next update
			// to come sooner creating an offset in the update loop regarding deltaU.
			if (deltaU >= 1) {
				//update();
				updates++;
					deltaU--;
			}
			
		if(now - lastFrame >= timePerFrame) {
			
			gamePanel.repaint();
			lastFrame = now;
			frames++;
			
		}
		
		if(System.currentTimeMillis() - lastCheck>= 1000) {
			lastCheck = System.currentTimeMillis();
			System.out.println("FPS: " + frames);
			frames = 0;
		}
			
		}
		
	}
	
}
