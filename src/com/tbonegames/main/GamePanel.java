package com.tbonegames.main;

//static imports have to include the entire package name, the name of the class, and the members or the * to import
//everything.
import static com.tbonegames.main.Game.*;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.tbonegames.inputs.KeyboardInputs;
import com.tbonegames.inputs.MouseInputs;


public class GamePanel extends JPanel{

	private MouseInputs mouseInputs;
	private Game game;
	//the panel is the picture
	public GamePanel(Game game) {
		this.game = game;
		
		mouseInputs = new MouseInputs(this);
		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}
	
	
	
	public void setPanelSize() {
		Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
		System.out.println("SIZE: "+ GAME_WIDTH + ":" + GAME_HEIGHT);
	}
	
	public void updateGame() {
		
	}
	
	
	//This gets called whenever we press the play button.
	//Graphics allows us to draw
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		game.render(g);

	}
	
	public Game getGame() {
		return game;
	}
	
	
}
