package se.snittarna.pairs;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Level extends GameObject {
	Animation a = new Animation(new Sprite(AssetManager.getTexture("plot")));
	
	public Level() {
		super(new Vector2(0, 0), new Vector2(1, 1), new Animation(new Sprite(AssetManager.getTexture("plot"))));
		
		a.setSize(4, 4);
	}

	public void update(float dt) {
		
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
	
	public void drawLight(SpriteBatch batch) {
		float r = 0;

		for(GameObject g : getScene().getObjects()) {
			if(g instanceof Car) {
				r = g.getRotation();
			}
		}
		
		a.setColor(0, 0, 0, 0.7f);
		for(int y = 0; y < 480/4; y++) {
			for(int x = 0; x < 640/4; x++) {
				a.setPosition(x*4, y*4);
				a.draw(batch);
			}
		}
	}
	
	public void draw(SpriteBatch batch) {
		drawLight(batch);
		super.draw(batch);
	}
}
