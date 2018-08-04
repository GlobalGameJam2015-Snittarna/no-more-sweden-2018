package se.snittarna.pairs;

import com.badlogic.gdx.math.Vector2;

import se.snittarna.pairs.GameObject;

public class Powerup extends GameObject {

	public Powerup(Vector2 position) {
		super(position, new Vector2(32, 32), new Animation(AssetManager.getTexture("clock"), new Vector2(32, 32)));
	}

}
