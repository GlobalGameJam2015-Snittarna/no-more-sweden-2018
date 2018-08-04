package se.snittarna.pairs;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Level extends GameObject {
	Animation a = new Animation(new Sprite(AssetManager.getTexture("plot")));
	
	public Level() {
		super(new Vector2(0, 0), new Vector2(1, 1), new Animation(new Sprite(AssetManager.getTexture("plot"))));
		
		a.setSize(8, 8);
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
	
	ArrayList<Vector2> ps = new ArrayList<Vector2>();
	int reg = 0;
	public void drawLight(SpriteBatch batch) {
		float r = 0;
		int lightLength = 64*2;
		Vector2 p = new Vector2(0, 0);
		reg += 1;
		for(GameObject g : getScene().getObjects()) {
			if(g instanceof Car) {
				r = g.getRotation();
				p = g.getPosition().cpy();
			}
		}
		
		for(int i = -1; i < lightLength; i++) {
			for (int j = 0; j < 2; j++) {
				ps.add(new Vector2((p.x+32)+(float)Math.cos(r + j * 0.2f)*i + (float)Math.cos(r)*32 + (float)Math.sin(r) * 8, (p.y+32)+(float)Math.sin(r + j * 0.2f)*i + (float)Math.sin(r)*32 - (float)Math.cos(r) * 8));
				ps.add(new Vector2((p.x+32)+(float)Math.cos(r + j * 0.2f)*i + (float)Math.cos(r)*32 - (float)Math.sin(r) * 8, (p.y+32)+(float)Math.sin(r + j * 0.2f)*i + (float)Math.sin(r)*32 + (float)Math.cos(r) * 8));
			}
			//ps.add(new Vector2(p.x+(float)Math.cos(r-0.2f)*i + (float)Math.cos(r)*64, p.y+(float)Math.sin(r-0.2f)*i + (float)Math.sin(r)*64));
			//ps.add(new Vector2(p.x+(float)Math.cos(r+0.2f)*i + (float)Math.cos(r)*64, p.y+(float)Math.sin(r+0.2f)*i + (float)Math.sin(r)*64));
		}
		
		for(int y = 0; y < 480/8; y++) {
			for(int x = 0; x < 640/8; x++) {
				boolean c = false;
				
				for(Vector2 v : ps) {
					if(new Rectangle((int)v.x, (int)v.y, 8, 8).collision(new Rectangle(x*8, y*8, 8, 8))) {
						c = true;
					}
				}

				if(c) a.setColor(0, 0, 0, 0);
				else a.setColor(0, 0, 0, 0.8f);
				a.setPosition(x*8, y*8);
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
