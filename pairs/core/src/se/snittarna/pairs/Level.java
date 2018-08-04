package se.snittarna.pairs;

import java.util.ArrayList;

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
		int lightLength = 64;
		Vector2 p = new Vector2(0, 0);
		
		ArrayList<Vector2> ps = new ArrayList<Vector2>();
		
		for(GameObject g : getScene().getObjects()) {
			if(g instanceof Car) {
				r = g.getRotation();
				p = g.getPosition().cpy();
			}
		}
		
		for(int i = 0; i < lightLength; i++) {
			ps.add(new Vector2(p.x+(float)Math.cos(r)*i, p.y+(float)Math.sin(r)*i));
			ps.add(new Vector2(p.x+(float)Math.cos(r-0.2f)*i, p.y+(float)Math.sin(r-0.2f)*i));
			ps.add(new Vector2(p.x+(float)Math.cos(r+0.2f)*i, p.y+(float)Math.sin(r+0.2f)*i));
		}
		
		for(int y = 0; y < 480/4; y++) {
			for(int x = 0; x < 640/4; x++) {
				boolean c = false;
				
				for(Vector2 v : ps) {
					if(new Rectangle((int)v.x, (int)v.y, 4, 4).collision(new Rectangle(x*4, y*4, 4, 4))) {
						c = true;
					}
				}

				if(c) a.setColor(0, 0, 0, 0);
				else a.setColor(0, 0, 0, 0.8f);
				a.setPosition(x*4, y*4);
				a.draw(batch);
			}
		}
		ps.clear();
	}
	
	public void draw(SpriteBatch batch) {
		drawLight(batch);
		super.draw(batch);
	}
}
