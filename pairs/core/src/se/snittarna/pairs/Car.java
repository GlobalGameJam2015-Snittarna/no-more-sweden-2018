package se.snittarna.pairs;

import com.badlogic.gdx.math.Vector2;

public class Car extends Player {
	
	public Car(Vector2 position) {
		super(position, new Vector2(64, 64), new Animation(AssetManager.getTexture("car"), new Vector2(64, 64)), 1);
		
		ACC = 100;
		DECC = 200;
		Cd = .0001f;
		ALPHA = 20f;
		CdA = 10;
		baseSideFriction = 2;
	}
	
	
	public void update(float dt) {
		if(GameScene.jumpToDeathScreen <= 0) move(dt);
		
		super.update(dt);
	}


	@Override
	public void death() {
		
	}
	
}
