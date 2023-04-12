package entities;

// a class you can never create an object of, so it's meant to be extended.
public abstract class Entity {
	
	protected float x, y;
	
	public Entity(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
}
