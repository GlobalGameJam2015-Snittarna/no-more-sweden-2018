package se.snittarna.pairs;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Level extends GameObject {	
	boolean hasStarted;
	
	float worldSpeed = 0;
	
	private float addBackgroundCount;
	private float addBackgroundCountMax;
	
	private float spawnObstacleCount;
	private float spawnObstacleCountMax;
	private float currentLevel;
	private float levelCount;
	private float levelCountMax;
	
	public Level() {
		super(new Vector2(0, 0), new Vector2(1, 1), new Animation(new Sprite(AssetManager.getTexture("plot"))));
		addBackgroundCount = addBackgroundCountMax;
		worldSpeed = 32;
		setOrder(1);
		
		spawnObstacleCountMax = 128/2;
		levelCountMax = 128+32;
	}
	
	public void restartLevel() {
		for(int i = 0; 640/64 > i; i++) {
			getScene().addObject(new Tile(new Vector2(i*64, 480-64), "sand"));
			getScene().addObject(new Tile(new Vector2(i*64, 480), "sand"));
		}
		for(int y = 0; y < 480/64; y++) {
			for(int x = 0; x < 640/64; x++) {
				getScene().addObject(new Tile(new Vector2(x*64, y*64), "sand"));
			}
		}
	}
	
	public void update(float dt) {
		if(!hasStarted) {
			restartLevel();
			hasStarted = true;
		}
		
		addBackgroundCountMax = 64-(int)worldSpeed*dt;
		addBackgroundCount += 1;
		if(addBackgroundCount >= addBackgroundCountMax) {
			for(int i = 0; 640/64 > i; i++) {
				getScene().addObject(new Tile(new Vector2(i*64, 480), "sand"));
			}
			addBackgroundCount = 0;
		}
		levelCount += 10*dt; 
		if(levelCount >= levelCountMax-(currentLevel*2)) {
			currentLevel += 1;
			worldSpeed += 30;
			levelCount = 0;
		}
		
		spawnObstacleCount += 10*dt;
		if(spawnObstacleCount >= spawnObstacleCountMax-currentLevel) {
			Vector2 v = new Vector2(random(0, 600), random(480, 480+100));
			if(random(0, 2) == 1) {
				addObstacle(v, 1);
			} else {
				addLog(v, random(2, 6));
			}
			spawnObstacleCount = random(0, (int)spawnObstacleCountMax/2);
		}
		super.update(dt);
	}
	
	public int random(int Min, int Max)
	{
	     return (int) (Math.random()*(Max-Min))+Min;
	}
	
	private void addObstacle(Vector2 position, int type) {
		String g = new String("obst"+type);
		getScene().addObject(new Obstacle(position, new Vector2(64, 64), new Animation(AssetManager.getTexture(g), new Vector2(32*2, 32*2))));
	}
	
	private void addLog(Vector2 position, int size) {
		
		for(int i = 0; i < size; i++)
		{	
			String g = new String("log"+(i == 0 ? "1" : (i == size-1 ? "3" : "2")));
			getScene().addObject(new Obstacle(position.cpy().add(64*i, 0), new Vector2(64, 64), new Animation(AssetManager.getTexture(g), new Vector2(32*2, 32*2))));
		}
	}
	
	public void draw(SpriteBatch batch) {
		super.draw(batch);
	}
	
	public float getSpeed() {
		return worldSpeed;
	}
}
