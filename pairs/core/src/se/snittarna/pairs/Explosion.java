package se.snittarna.pairs;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Explosion extends GameObject {
	private final float MAX_ANIMATION_COUNT = 0.5f;
	
	private float animationCount;
	
	private int currentFrame;
	
	public Explosion(Vector2 position) {
		super(position, new Vector2(64, 64), new Animation(new Sprite(AssetManager.getTexture("explosion0"))));
	}
	
	public void update(float dt) {
		animationCount += 5 *dt;
		if(animationCount >= MAX_ANIMATION_COUNT && currentFrame != 5) {
			currentFrame++;
			setSprite(new Animation(new Sprite(AssetManager.getTexture("explosion"+currentFrame))));
			
			animationCount = 0;
		}
		
		if(currentFrame >= 5) {
			getScene().removeObject(this);
		}
		
		super.update(dt);
	}
	
	public boolean isDeadliy() {
		return currentFrame <= 2;
	}
}
