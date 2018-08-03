package se.snittarna.pairs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Animation extends Sprite {
	private int maxFrame;
	private int minFrame;
	private int currentFrame;
	private float maxAnimationCount;
	private float animationCount;
	
	public Animation(Sprite sprite) {
		super(sprite);
	}
	
	public Animation(Sprite sprite, int maxFrame, int minFrame, float maxAnimationCount) {
		super(sprite);
		this.maxFrame = maxFrame;
		this.minFrame = minFrame;
		this.maxAnimationCount = maxAnimationCount;
	}
	
	public Animation(TextureRegion region, Vector2 size) {
		this.setRegion(region);
		this.setSize(size.x, size.y);
	}
	
	public Animation(Texture sprite) {
		super(sprite);
	}
	
	public int frame(int frame, int size) {
		return 1+frame+size*frame;
	}
	
	public void setAnimation(int maxFrame, float maxAnimationCount) {
		this.maxFrame = maxFrame;
		this.maxAnimationCount = maxAnimationCount;
	}
	
	public void animate(float dt) {
		animationCount += 10 * dt;
		//System.out.println(currentFrame + ": ADSA");
		this.setRegion(frame(minFrame, this.getRegionWidth()) + currentFrame*this.getRegionWidth()+1+currentFrame, 
				this.getRegionY(), this.getRegionWidth(), this.getRegionHeight());
		if(animationCount > maxAnimationCount) {
			currentFrame += 1;
			if(currentFrame >= maxFrame) {
				currentFrame = 0;
			}
			animationCount = 0;
		}
	}
}
