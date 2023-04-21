package entities;

import static utils.Constants.PlayerConstants.IDLE;
import static utils.Constants.PlayerConstants.RUNNING;
import static utils.Constants.PlayerConstants.ATTACK_1;
import static utils.Constants.PlayerConstants.getSpriteAmount;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Player extends Entity{
	
	private BufferedImage[][] animations;
	private int animationTick, animationIndex, animationSpeed = 10;
	private int playerAction = IDLE;
	private boolean moving = false, attacking = false;
	private boolean left, up, right, down;
	private float playerSpeed = 2.0f;
	
	public Player(float x, float y) {
		super(x, y);
		loadAnimations();
		
	}
	
	
	
	public void update() {
		updatePos();
		updateAnimationTick();
		setAnimation();
		
	}
	
	public void render(Graphics g) {

		//with buffered images you can draw a section of the image, ie tiles for the game to work.
		//also the method with its parameters filled out can also be used to pass an image
		//128 is the width and the heeight is 80
		g.drawImage(animations[playerAction][animationIndex], (int)x, (int)y, 256, 160, null);

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
		
		if (left && !right) {
			x-=playerSpeed;
			moving = true;
		} else if(right && !left){
			x += playerSpeed;
			moving = true;
		}
		
		if (up && !down) {
			y -= playerSpeed;
			moving = true;
		} else if (down && !up) {
			y += playerSpeed;
			moving = true;
		}
		
	
	}
	
	public void loadAnimations() {
		InputStream is = getClass().getResourceAsStream("/player_sprites.png");
		try {
			BufferedImage img = ImageIO.read(is);
			
			animations = new BufferedImage[9][6];
			
			for (int j = 0; j < animations.length; j++)
			for (int i = 0; i < animations[j].length; i++) {
				animations[j][i] = img.getSubimage(i*64, j*40, 64, 40);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				//close the stream to free up resources.
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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
