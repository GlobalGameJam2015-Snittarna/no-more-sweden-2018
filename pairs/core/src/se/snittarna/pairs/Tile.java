package se.snittarna.pairs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Tile extends GameObject {
	private float speed;

	public Tile(Vector2 position, String type) {
		super(position, new Vector2(64, 64), new Animation(AssetManager.getTexture("sand"), new Vector2(64, 64)));
	}
	
	public void update(float dt) {
		this.getPosition().add(0, -speed).scl(dt);
	}
	
	public void draw(SpriteBatch batch) {
		super.draw(batch);
	}
}
