package se.snittarna.pairs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.math.Vector2;

abstract class Player extends GameObject {
	protected float ACC = 100;
	protected float Cd = .001f;
	protected float ALPHA = 3f;
	protected float CdA = 1;
	protected float baseSideFriction = 2;

	private Vector2 speed;
	private float omega;
	
	private int controllerIndex; // -1 för keyboard

	
	public Player(Vector2 position, Vector2 size, Animation sprite, int controllerIndex) {
		super(position, size, sprite);
		this.speed = new Vector2();
		
		this.controllerIndex = controllerIndex;
	}
	
	/**
	 * do physics stuff according to input
	 */
	protected void move(float dt) {
		if (controllerIndex == -1) {
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
		} else {
			Controller c = Controllers.getControllers().get(controllerIndex);
			
			if (c.getAxis(5) != 0) {
				speed.add(new Vector2((float)Math.cos(getRotation()), (float)Math.sin(getRotation())).scl((c.getAxis(5) + 1) / 2 * ACC * dt));
			}
			if (c.getAxis(2) != 0) {
				speed.sub(new Vector2((float)Math.cos(getRotation()), (float)Math.sin(getRotation())).scl((c.getAxis(2) + 1) / 2 * ACC * dt));
			}
			
			if (Math.abs(c.getAxis(0)) > 0.15f) {
				omega -= c.getAxis(0) * ALPHA * dt;
			}
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
	}

	protected void death() {}
}