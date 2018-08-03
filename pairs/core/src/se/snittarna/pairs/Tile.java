package se.snittarna.pairs;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Tile extends GameObject {
	

	public Tile(Vector2 position) {
		super(position, new Vector2(32, 32), new Animation(new Sprite()));
	}
	
	public void draw(SpriteBatch batch) {
		super.draw(batch);
	}

}
