package entities;

import static utils.HelpMethods.canMoveHere;
import static utils.Constants.PlayerConstants.IDLE;
import static utils.Constants.PlayerConstants.RUNNING;
import static utils.Constants.PlayerConstants.ATTACK_1;
import static utils.Constants.PlayerConstants.getSpriteAmount;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.tbonegames.main.Game;

import utils.LoadSave;

public class Player extends Entity{
	
	private BufferedImage[][] animations;
	private int animationTick, animationIndex, animationSpeed = 10;
	private int playerAction = IDLE;
	private boolean moving = false, attacking = false;
	private boolean left, up, right, down;
	private float playerSpeed = 2.0f;
	private int[][] levelData;
	
	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
		loadAnimations();
		
	}
	
	
	
	public void update() {
		updatePos();
		updateHitBox();
		updateAnimationTick();
		setAnimation();
		
	}
	
	public void render(Graphics g) {

		//with buffered images you can draw a section of the image, ie tiles for the game to work.
		//also the method with its parameters filled out can also be used to pass an image
		//128 is the width and the heeight is 80
		g.drawImage(animations[playerAction][animationIndex], (int)x, (int)y, (int)(Game.TILES_SIZE*1.5), Game.TILES_SIZE, null);
		drawHitBox(g);
	}
	
	
	
	private void updateAnimationTick() {
		animationTick++;
		if (animationTick >= animationSpeed) {
			animationTick = 0;
			animationIndex++;
			if (animationIndex >= getSpriteAmount(playerAction)) {
				animationIndex = 0;
				attacking = false;
			}
		}
	}
	
	private void setAnimation() {
		int startAnimation = playerAction;
		
		if (moving) {
			playerAction = RUNNING;
		} else {
			playerAction = IDLE;
		}
		if (attacking) {
			playerAction = ATTACK_1;
		}
		if (startAnimation != playerAction) {
			resetAnimationTick();
		}
	}
	
	private void resetAnimationTick() {
		animationTick = 0;
		animationIndex = 0;
	}



	private void updatePos() {
		moving = false;
		if (!left && !right && !up && !down) {
			return; //ends the method earlier.
		}
		
		float xSpeed = 0, ySpeed = 0; //temporary storage of the spped.
		
		if (left && !right) {
			xSpeed = -playerSpeed;
		} else if(right && !left){
			xSpeed = playerSpeed;
		}
		
		if (up && !down) {
			ySpeed = -playerSpeed;
		} else if (down && !up) {
			ySpeed = playerSpeed;
		}
		
		if (canMoveHere(x+xSpeed, y+ySpeed, width, height, levelData)) {
			this.x += xSpeed;
			this.y += ySpeed;
			moving = true;
		}
		
	
	}
	
	public void loadAnimations() {
			BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);
			
			animations = new BufferedImage[9][6];
			for (int j = 0; j < animations.length; j++)
			for (int i = 0; i < animations[j].length; i++) {
				animations[j][i] = img.getSubimage(i*64, j*40, 64, 40);
			}
	}
	
	public void loadLevelData(int[][] levelData) {
		this.levelData = levelData;
	}
	



	public boolean isLeft() {
		return left;
	}



	public void setLeft(boolean left) {
		this.left = left;
	}



	public boolean isUp() {
		return up;
	}



	public void setUp(boolean up) {
		this.up = up;
	}



	public boolean isRight() {
		return right;
	}



	public void setRight(boolean right) {
		this.right = right;
	}



	public boolean isDown() {
		return down;
	}



	public void setDown(boolean down) {
		this.down = down;
	}



	public void resetDirBooleans() {
		left = false;
		right = false;
		up = false;
		down = false;
		
	}
	
	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}
	
	
}
