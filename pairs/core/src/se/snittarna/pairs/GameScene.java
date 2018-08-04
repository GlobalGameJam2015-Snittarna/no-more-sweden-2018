package se.snittarna.pairs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Music.OnCompletionListener;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameScene extends Scene {
	
	public static boolean gameOver;
	private boolean firstRound;
	
	private float score;
	
	private Random random;
	
	// (e du) god (eller) praxis
	
	public GameScene() {
		random = new Random();
		
		getCamera().setPosition(10 * 32, (float) (7.5 * 32));
		addObject(new Level());
		this.addObject(new Car(new Vector2(320, 200)));
		this.addObject(new Horse(new Vector2(100, 0)));
		this.addObject(new Light());
	}
	
	public void onResume() {
		AssetManager.getSound("blip").play();
	}
	
	public void update(float dt) {
		getCamera().update(dt);
		
		score += 10f * dt;
		
		super.update(dt);
	}
	
	public void drawUi(SpriteBatch batch) {
		AssetManager.font.draw(batch, "score: " + (int)score, -160, 80);
		
		super.drawUi(batch);
	}
}