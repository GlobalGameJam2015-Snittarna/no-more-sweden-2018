package se.snittarna.pairs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Car extends GameObject implements Player {
	private final float ACC = 100;
	private final float Cd = .001f;
	private final float ALPHA = 3f;
	private final float CdA = 1;
	private final float baseSideFriction = 100;
	
	private Vector2 speed;
	private float omega;

	public Car(Vector2 position) {
		super(position, new Vector2(32, 32), new Animation(AssetManager.getTexture("car"), new Vector2(32, 32)));
		this.speed = new Vector2();
	}
	
	
	public void update(float dt) {
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			speed.add(new Vector2((float)Math.cos(getRotation()), (float)Math.sin(getRotation())).scl(ACC * dt));
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			speed.sub(new Vector2((float)Math.cos(getRotation()), (float)Math.sin(getRotation())).scl(ACC * dt));
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			omega += ALPHA * dt;
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			omega -= ALPHA * dt;
		}
		
		this.setPosition(this.getPosition().add(new Vector2(speed).scl(dt)));
		this.setRotation(this.getRotation() + omega * dt);
		
		Vector2 drag = speed.cpy().nor().scl(-speed.len() * speed.len() * Cd * dt);
		speed.add(drag);
		
		omega -= Math.signum(omega) * omega * omega * CdA * dt;
		
		
		
		super.update(dt);
	}


	@Override
	public void death() {
		// TODO Auto-generated method stub
		
	}
	
}
