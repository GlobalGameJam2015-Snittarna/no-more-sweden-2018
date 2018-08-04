package se.snittarna.pairs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Horse extends Player {
	

	public Horse(Vector2 position) {
		super(position, new Vector2(64, 64), new Animation(AssetManager.getTexture("horsie"), new Vector2(64, 64)), 0);
		
		ACC = 200;
		DECC = 200;
		Cd = .005f;
		ALPHA = 5f;
		CdA = 1;
		baseSideFriction = 1;
	}
	
	public void onAdd() {
		AssetManager.getSound("horse.ogg").play();
	}
	
	
	public void update(float dt) {
		move(dt);
		
		super.update(dt);
	}


	@Override
	public void death() {
		// TODO Auto-generated method stub
		
	}
	
}
