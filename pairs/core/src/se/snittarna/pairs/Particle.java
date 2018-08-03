package se.snittarna.pairs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Particle extends GameObject {

	private Vector2 velocity;
	private Color color;
	private float lifetime = .5f;
	private float size;
	
	public Particle(Vector2 position, Vector2 velocity, Color color, float size) {
		super(position, new Vector2(1, 1).scl(size), new Animation(new Sprite(AssetManager.getTexture("plot"))));
		this.velocity = velocity;
		this.color = color;
		getSprite().setColor(color);
		this.size = size;
	}
	
	public void update(float dt) {
		super.update(dt);
		setPosition(getPosition().add(velocity.cpy().scl(dt)));
		lifetime -= dt;
		if (lifetime <= 0) {
			getScene().removeObject(this);
			System.out.println("particle died");
		}
		
		setScale(size * lifetime / .5f);
	}
}
