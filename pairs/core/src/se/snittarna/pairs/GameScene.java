package se.snittarna.pairs;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameScene extends Scene {
	
	public static boolean gameOver;
	
	private float score;
	public static float jumpToDeathScreen;
	
	private Random random;
	
	// (e du) god (eller) praxis
	
	Car car;
	Horse horse;
	
	public GameScene() {
		random = new Random();
		
		getCamera().setPosition(10 * 32, (float) (7.5 * 32));
		addObject(new Level());
		
		car = new Car(new Vector2(300, 100));
		horse = new Horse(new Vector2(300, 300));
		
		this.addObject(car);
		this.addObject(horse);
		
		this.addObject(new Light());
	}
	
	public void onResume() {
		AssetManager.getSound("blip").play();
	}
	
	private boolean hasPlayedSound;
	
	public void update(float dt) {
		getCamera().update(dt);
		
		if (jumpToDeathScreen <= 0) score += 10f * dt;
		
		super.update(dt);
		lastDt = dt;
		
		if (car.getPosition().dst(horse.getPosition()) < 40) {
			if(jumpToDeathScreen >= 30) Game.setCurrentScene(new GameOverScene((int)score));
			jumpToDeathScreen += 10*dt;
			if (!hasPlayedSound) {
				AssetManager.getSound("hit").play();
				hasPlayedSound = true;
			}
		}
		
		boolean cont = false;
		for (Controller controller : Controllers.getControllers()) {
			if (controller.getButton(0)) cont = true;
		}
		if (jumpToDeathScreen > 10 && (Gdx.input.isKeyPressed(Keys.SPACE) || cont)) {
			Game.setCurrentScene(new GameOverScene((int)score));
		}
	}
	
	float lastDt;
	
	public void drawUi(SpriteBatch batch) {
		AssetManager.font.draw(batch, "score: " + (int)score, -160, 80);
		//AssetManager.font.draw(batch, 1/lastDt + "", -160, 60);
		
		if(GameScene.jumpToDeathScreen > 0) AssetManager.font.draw(batch, "DEAD", 0, 0);
		
		super.drawUi(batch);
	}
	
	public float getScore() {
		return score;
	}
}