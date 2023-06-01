package entities;

import static utils.HelpMethods.*;
import static utils.Constants.PlayerConstants.*;
import static utils.Constants.PlayerConstants.getSpriteAmount;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.tbonegames.main.Game;

import utils.LoadSave;

public class Player extends Entity{
	
	private BufferedImage[][] animations;
	private int animationTick, animationIndex, animationSpeed = 10;
	private int playerAction = IDLE;
	private boolean moving = false, attacking = false;
	private boolean left, up, right, down, jump;
	private float playerSpeed = 2.0f; //Can be related to the scale of the game. Originally, 2.0f
	private int[][] levelData;
	private float xDrawOffset = 21 * Game.SCALE;
	private float yDrawOffset = 4 * Game.SCALE;
	
	//jumping and gravity.
	private float airSpeed = 0f;
	private float gravity = .04f * Game.SCALE;
	private float jumpSpeed = -2.25f * Game.SCALE;
	private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
	private boolean inAir = false;
	
	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
		loadAnimations();
		initHitBox(x, y, (int)(20 * Game.SCALE),(int)(27*Game.SCALE));
		
	}
	
	
	
	public void update() {
		updatePos();
		updateAnimationTick();
		setAnimation();
		
	}
	
	public void render(Graphics g, int levelOffset) {

		//with buffered images you can draw a section of the image, ie tiles for the game to work.
		//also the method with its parameters filled out can also be used to pass an image
		//128 is the width and the heeight is 80
		g.drawImage(animations[playerAction][animationIndex], (int)(hitBox.x - xDrawOffset) - levelOffset, (int)(hitBox.y - yDrawOffset), width, height, null);
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
		
		if (inAir) {
			if(airSpeed < 0) {
				playerAction = JUMP;
			} else {
				playerAction = FALLING;
			}
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
		if(jump) {
			jump();
		}
//		if (!left && !right && !inAir) {
//			return; //ends the method earlier.
//		}
	
		if(!inAir)
			if((!left && !right) || (right && left) )
				return;
		
		float xSpeed = 0; //temporary storage of the spped.
		
		if (left) {
			xSpeed -= playerSpeed;
		}
		if(right){
			xSpeed += playerSpeed;
		}
		
		if(!inAir) {
			if (!isEntityOnFloor(hitBox, levelData)) {
				inAir = true;
			}
		}
		
		if(inAir) {
			//first to check up and down points and then left to right
			if(canMoveHere(hitBox.x, hitBox.y + airSpeed, hitBox.width, hitBox.height, levelData)) {
				hitBox.y += airSpeed;
				airSpeed += gravity;
				updateXPos(xSpeed);
			} else {
				hitBox.y = getEntityYPosUnderRoofOrAboveFloor(hitBox, airSpeed);
				if (airSpeed > 0) {
					resetInAir();
				} else {
					airSpeed = fallSpeedAfterCollision;
				}
				updateXPos(xSpeed);
			}
			
		} else {
			updateXPos(xSpeed);
		}
		
		moving = true;
	
	}
	
	private void jump() {
		if (inAir) {
			return;
		} else {
			inAir = true;
			airSpeed = jumpSpeed;
		}
		
	}



	private void resetInAir() {
		inAir = false;
		airSpeed = 0;
		
	}



	private void updateXPos(float xSpeed) {
		if (canMoveHere(hitBox.x+xSpeed, hitBox.y, hitBox.width, hitBox.height, levelData)) {
			hitBox.x += xSpeed;
		} else {
			hitBox.x = getEntityXPosNextToWall(hitBox, xSpeed);
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
		if (!isEntityOnFloor(hitBox, levelData)) {
			inAir = true;
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
	
	public void setJump(boolean jump) {
		this.jump = jump;
	}
	
	
}
