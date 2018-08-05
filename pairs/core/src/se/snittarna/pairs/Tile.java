package se.snittarna.pairs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Tile extends GameObject {
	private float speed;
	
	public Tile(Vector2 position, String type) {
		super(position, new Vector2(64, 66), new Animation(AssetManager.getTexture(type), new Vector2(64, 66)));
		setOrder(-1);
	}
	
	public void update(float dt) {
		this.setPosition(new Vector2(getPosition().x, getPosition().y-speed*dt));
		for(GameObject g : getScene().getObjects()) {
			if(g instanceof Level) {
				speed = ((Level) g).getSpeed();
			}
		}
		
		if(getPosition().y <= -64) getScene().removeObject(this);
		super.update(dt);
	}
	
	public void draw(SpriteBatch batch) {
		super.draw(batch);
	}
}
