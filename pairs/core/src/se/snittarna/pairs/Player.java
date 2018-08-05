package se.snittarna.pairs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.math.Vector2;

abstract class Player extends GameObject {
	protected float ACC = 100;
	protected float DECC = 100;
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
		this.setOrder(1);
		this.controllerIndex = controllerIndex;
		
		if (controllerIndex >= Controllers.getControllers().size) {
			this.controllerIndex = -1;
			System.out.println("not enough controllers, using keyboard");
		}
	}
	
	/**
	 * apply acceleration according to input, depending on the vehicles direction of movement and where it is pointing
	 * @param input from -1 to 1
	 */
	private void accelerate(float input, float dt) {
		Vector2 forward = Utils.vectorFromAngle(getRotation());
		boolean goingForward = (forward.dot(speed) > 0);
		
		float a = (input >= 0 == goingForward ? ACC : DECC) * input;
		
		speed.add(forward.scl(a * dt));
	}
	
	/**
	 * do physics stuff according to input
	 */
	protected void move(float dt) {
		if (controllerIndex == -1) {
			float a = 0;
			
			if (Gdx.input.isKeyPressed(Input.Keys.W)) {
				a = 1;
			}
			
			if (Gdx.input.isKeyPressed(Input.Keys.S)) {
				a = -1;
			}
			
			accelerate(a, dt);
			
			if (Gdx.input.isKeyPressed(Input.Keys.A)) {
				omega += ALPHA * dt;
			}
			
			if (Gdx.input.isKeyPressed(Input.Keys.D)) {
				omega -= ALPHA * dt;
			}
		} else {
			Controller c = Controllers.getControllers().get(controllerIndex);
			
			float a = 0;
			if (c.getAxis(5) != 0) {
				a += c.getAxis(5);
			}
			if (c.getAxis(2) != 0) {
				a -= c.getAxis(2);
			}
			accelerate(a, dt);
			
			
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
		
		for (GameObject g : getScene().getObjects()) {
			if (g instanceof Level) {
				float s = ((Level) g).getSpeed();
				this.setPosition(this.getPosition().add(new Vector2(0, -s * dt)));
			}
		}
		
	}
	
	public void update(float dt) {
		if(GameScene.jumpToDeathScreen > 0) {
			float speed = 0;
			
			for(GameObject g : getScene().getObjects()) {
				if(g instanceof Level) 
					speed = ((Level) g).getSpeed();
			}
			
			setPosition(new Vector2(getPosition().x, getPosition().y-speed*dt));
		}
		super.update(dt);
	}
	
	public void death() {
		float score = ((GameScene) getScene()).getScore();
		Game.setCurrentScene(new GameOverScene((int)score));
	}
}