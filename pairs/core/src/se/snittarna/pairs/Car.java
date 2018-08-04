package se.snittarna.pairs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

public class Car extends Player {
	
	public Car(Vector2 position) {
		super(position, new Vector2(64, 64), new Animation(AssetManager.getTexture("car"), new Vector2(64, 64)), 1);
		
		ACC = 100;
		Cd = .001f;
		ALPHA = 3f;
		CdA = 1;
		baseSideFriction = 2;
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
