package se.snittarna.pairs;

import java.util.Random;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Camera extends OrthographicCamera {

	/**
	 * an object to follow. Null if none set.
	 */
	private GameObject follow;
	private float deadZoneX, deadZoneY;
	private Vector2 position, oldPosition;
	private float shake;
	private final float GAIN = 10;
	private Random r;
	
	/**
	 * construct a camera with a given viewport size.
	 * @param width
	 * @param height
	 */
	public Camera(int width, int height) {
		super(width, height);
		r = new Random();
		position = new Vector2(0, 0);
	}
	
	/**
	 * 
	 * @param g
	 * @param deadZoneX goes in both directions.
	 * @param deadZoneY goes in both directions.
	 */
	public void setFollow(GameObject g, float deadZoneX, float deadZoneY) {
		this.follow = g;
		this.deadZoneX = deadZoneX;
		this.deadZoneY = deadZoneY;
	}
	
	public void translate(float x, float y) {
		super.translate(x, y);
		position.add(x, y);
	}
	
	public void setPosition(float x, float y) {
		this.translate(x - position.x, y - position.y);
	}
	
	public void shake(float level) {
		if (shake <= 0) oldPosition = position.cpy();
		shake += level;
	}
	
	public void update(float dt) {
		if (shake > 0) {
			this.setPosition(oldPosition.x + (r.nextFloat() - .5f) * shake * GAIN, oldPosition.y + (r.nextFloat() - .5f) * shake * GAIN);
		} else if (oldPosition != null) {
			this.setPosition(oldPosition.x, oldPosition.y);
		}
		shake -= dt * (shake * 2 * shake + .1f);
		if (shake < 0) shake = 0;
	}
	
	public void update() {
		if (follow != null) {
			float dx = follow.getPosition().x - position.x;
			//float dy = position.y - follow.getPosition().y;
			if (Math.abs(dx) > deadZoneX) {
				position.x = MathUtils.lerp(position.x, dx - deadZoneX * Math.signum(dx), .1f);
				//System.out.println(dx - deadZoneX * Math.signum(dx) - position.x);
			}
		}
		
		super.update();
	}
}
