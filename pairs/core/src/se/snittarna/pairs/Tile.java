package se.snittarna.pairs;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Tile extends GameObject {
	
	private TileType type;

	public TileType getType() {
		return type;
	}

	public Tile(Vector2 position, TileType type) {
		super(position, new Vector2(32, 32), new Animation(new Sprite(type.getTexture())));
		this.type = type;
	}
	
	public void draw(SpriteBatch batch) {
		super.draw(batch);
	}

}
