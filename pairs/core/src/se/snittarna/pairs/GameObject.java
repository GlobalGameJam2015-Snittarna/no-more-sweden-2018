package se.snittarna.pairs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {
	private Vector2 position, size, origin;
	private Scene scene;
	
	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	/**
	 * 
	 * @return a copy of the size vector.
	 */
	public Vector2 getSize() {
		return size.cpy();
	}

	/**
	 * 
	 * @return a copy of the origin vector.
	 */
	public Vector2 getOrigin() {
		return origin.cpy();
	}

	private Animation sprite;
	
	public GameObject(Vector2 position, Vector2 size, Animation sprite) {
		this.sprite = sprite;
		setOriginCenter();
		setPosition(position);
		setSize(size);
	}
	
	public Rectangle getHitbox() {
		//System.out.println("hitbox size " + size);
		return new Rectangle(this.getSprite().getBoundingRectangle().x, this.getSprite().getBoundingRectangle().y, this.getSprite().getBoundingRectangle().width, this.getSprite().getBoundingRectangle().height);
		//return new Rectangle(this.position.cpy().sub(this.size.cpy().scl(.5f)), this.size);
	}
	
	public void update(float dt) {
		
	}
	
	public void setScale(float s) {
		sprite.setScale(s);
	}
	
	protected void setOriginCenter() {
		getSprite().setOriginCenter();
		origin = new Vector2(getSprite().getOriginX(), getSprite().getOriginY());
	}
	
	protected Animation getSprite() {
		return sprite;
	}

	public void setSprite(TextureRegion texture) {
		sprite.setRegion(texture);
	}
	
	public void setSprite(Animation sprite) {
		this.setSprite(sprite, false);
	}
	
	public void setSprite(Animation sprite, boolean discardInfo) {
		this.sprite = sprite;
		if (!discardInfo) {
			setPosition(position);
			setSize(size);
			setOrigin(origin);
		}
	}
	
	public void setSize(Vector2 s) { 
		size = s.cpy();
		if (sprite != null) sprite.setSize(s.x, s.y); 
	}
	
	protected void setOrigin(Vector2 origin) {
		this.origin = origin.cpy();
		if (sprite != null) sprite.setOrigin(origin.x, origin.y);
	}
	
	protected void setRotation(float rotation) {
		sprite.setRotation((float) Math.toDegrees(rotation));
	}
	
	protected float getRotation() {
		return (float) Math.toRadians(sprite.getRotation());
	}
	
	public void draw(SpriteBatch batch) {
		if(sprite != null) sprite.draw(batch);
	}
	
	public void drawUi(SpriteBatch batch) {
		
	}
	
	public Vector2 getPosition() {
		return position.cpy();
	}
	
	public float lerp(float s, float e, float t)
	{	    return s + t * (e - s);
	}
	
	public void setPosition(Vector2 position) {
		this.position = position.cpy();
		this.sprite.setPosition(this.position.x, this.position.y);
	}

	public void onAdd() {
		
	}
}
