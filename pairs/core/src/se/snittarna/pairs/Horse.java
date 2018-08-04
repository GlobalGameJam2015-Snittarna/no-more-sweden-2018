package se.snittarna.pairs;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Horse extends Player {
	
	float timeTilPlay;
	Random random;

	public Horse(Vector2 position) {
		super(position, new Vector2(64, 64), new Animation(AssetManager.getTexture("horsie"), new Vector2(64, 64)), 0);
		
		ACC = 200;
		DECC = 200;
		Cd = .005f;
		ALPHA = 5f;
		CdA = 1;
		baseSideFriction = 1;
		
		random = new Random();
		timeTilPlay = 0;
	}
	
	public void onAdd() {
	}
	
	
	public void update(float dt) {
		move(dt);
		timeTilPlay -= dt;

		if (timeTilPlay <= 0) {
			timeTilPlay = (float)random.nextGaussian() * 3 + 10;
			AssetManager.getSound("horse.ogg").play();
			System.out.println(timeTilPlay);
		}
		
		super.update(dt);
	}


	@Override
	public void death() {
		// TODO Auto-generated method stub
		
	}
	
}
