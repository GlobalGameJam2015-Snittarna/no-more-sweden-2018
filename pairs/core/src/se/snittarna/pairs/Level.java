package se.snittarna.pairs;

import com.badlogic.gdx.math.Vector2;

public class Level {
	Scene s;
	
	public Level(Scene s) {
		this.s = s;
	}
	
	public void update(float dt) {
		
	}
	
	private void addObstacle(Vector2 position, int type) {
		String g = new String("obst"+type);
		s.addObject(new Obstacle(position, new Vector2(64, 64), new Animation(AssetManager.getTexture(g), new Vector2(32*2, 32*2))));
	}
	
	private void addLog(Vector2 position, int size) {
		for(int i = 0; i < size; i++)
		{	
			String g = new String("log"+(i == 0 ? "1" : (i == size-1 ? "3" : "2")));
			s.addObject(new Obstacle(position, new Vector2(64, 64), new Animation(AssetManager.getTexture(g), new Vector2(32*2, 32*2))));
		}
	}
}
