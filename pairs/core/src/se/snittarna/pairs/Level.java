package se.snittarna.pairs;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Level extends GameObject {	
	boolean hasStarted;
	
	public Level() {
		super(new Vector2(0, 0), new Vector2(1, 1), new Animation(new Sprite(AssetManager.getTexture("plot"))));
	}
	
	public void restartLevel() {
		for(int y = 0; y < 480/64; y++) {
			for(int x = 0; x < 640/64; x++) {
				getScene().addObject(new Tile(new Vector2(x*64, y*64), "sand"));
			}
		}
	}
	
	public void update(float dt) {
		if(!hasStarted) {
			restartLevel();
		}
		super.update(dt);
	}
	
	private void addObstacle(Vector2 position, int type) {
		String g = new String("obst"+type);
		getScene().addObject(new Obstacle(position, new Vector2(64, 64), new Animation(AssetManager.getTexture(g), new Vector2(32*2, 32*2))));
	}
	
	private void addLog(Vector2 position, int size) {
		for(int i = 0; i < size; i++)
		{	
			String g = new String("log"+(i == 0 ? "1" : (i == size-1 ? "3" : "2")));
			getScene().addObject(new Obstacle(position, new Vector2(64, 64), new Animation(AssetManager.getTexture(g), new Vector2(32*2, 32*2))));
		}
	}
	
	public void draw(SpriteBatch batch) {
		super.draw(batch);
	}
}
