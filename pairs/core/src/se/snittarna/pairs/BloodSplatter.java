package se.snittarna.pairs;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class BloodSplatter extends GameObject {
	boolean hasHitGround;
	
	private float height;
	private float speed;
	private Vector2 delta;
	
	Random random = new Random();
	
	public BloodSplatter(Vector2 position) {
		super(position, new Vector2(32, 32), new Animation(new Sprite(AssetManager.getTexture("bloodSplatter1"))));
		
		speed = 20;
		float angle = random.nextFloat() * 180;
		delta = new Vector2((float)Math.cos(angle), (float)Math.sin(angle));
	}
	
	public void update(float dt) {
		if (!hasHitGround) {
			speed -= 40 * dt;
			height += speed * dt;
			
			if (height <= 0) {
				hasHitGround = true;
				setSprite(AssetManager.getTexture("bloodSplatter2"));
			}
			
			setPosition(getPosition().add(delta));
		}
		
		setScale(1 + height / 7);
		
		super.update(dt);
	}
}
