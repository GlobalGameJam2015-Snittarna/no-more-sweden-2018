package se.snittarna.pairs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Car extends GameObject {
	private final float ACC = 1;
	private final float Cd = 1;
	
	private Vector2 speed;
	private float alpha;

	public Car(Vector2 position) {
		super(position, new Vector2(32, 32), new Animation(AssetManager.getTexture("car"), new Vector2(32, 32)));
		// TODO Auto-generated constructor stub
		this.speed = new Vector2();
	}
	
	
	public void update(float dt) {
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			speed.add(0, ACC);
		}
			
		this.setPosition(this.getPosition().add(new Vector2(speed).scl(dt)));
		this.setRotation(this.getRotation() + alpha);
		
		Vector2 drag = speed.cpy().nor().scl(-speed.len() * speed.len());
		speed.add(drag);
		
		super.update(dt);
	}
	
}
