package se.snittarna.pairs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Horse extends GameObject implements Player {
	private final float ACC = 80;
	private final float Cd = .001f;
	private final float ALPHA = 5f;
	private final float CdA = 1;
	private final float baseSideFriction = 1;
	
	private Vector2 speed;
	private float omega;

	public Horse(Vector2 position) {
		super(position, new Vector2(64, 64), new Animation(AssetManager.getTexture("horsie"), new Vector2(64, 64)));
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
		
		Vector2 left = new Vector2((float)Math.cos(Math.PI / 2  + this.getRotation()), (float)Math.sin(Math.PI / 2 + this.getRotation()));
		Vector2 right = new Vector2((float)Math.cos(-Math.PI / 2  + this.getRotation()), (float)Math.sin(-Math.PI / 2 + this.getRotation()));

		if (left.dot(speed) > 0) {
			speed.sub(left.scl(dt * baseSideFriction * speed.len()));
		}
		
		if (right.dot(speed) > 0) {
			speed.sub(right.scl(dt * baseSideFriction * speed.len()));
		}
		
		super.update(dt);
	}


	@Override
	public void death() {
		// TODO Auto-generated method stub
		
	}
	
}
