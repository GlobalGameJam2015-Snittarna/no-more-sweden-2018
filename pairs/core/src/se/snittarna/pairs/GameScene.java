package se.snittarna.pairs;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameScene extends Scene {
	
	public static boolean gameOver;
	private boolean firstRound;
	
	private float score;
	
	private Random random;
	
	// (e du) god (eller) praxis
	
	Car car;
	Horse horse;
	
	public GameScene() {
		random = new Random();
		
		getCamera().setPosition(10 * 32, (float) (7.5 * 32));
		addObject(new Level());
		
		car = new Car(new Vector2(320, 200));
		horse = new Horse(new Vector2(100, 0));
		
		this.addObject(car);
		this.addObject(horse);
		
		this.addObject(new Light());
		this.addObject(new Powerup(new Vector2(50, 50)));
	}
	
	public void onResume() {
		AssetManager.getSound("blip").play();
	}
	
	public void update(float dt) {
		getCamera().update(dt);
		
		score += 10f * dt;
		
		super.update(dt);
		lastDt = dt;
		
		if (car.getPosition().dst(horse.getPosition()) < 40) {
			Game.setCurrentScene(new GameOverScene((int)score));
		}
	}
	
	float lastDt;
	
	public void drawUi(SpriteBatch batch) {
		AssetManager.font.draw(batch, "score: " + (int)score, -160, 80);
		AssetManager.font.draw(batch, 1/lastDt + "", -160, 60);
		
		super.drawUi(batch);
	}
}