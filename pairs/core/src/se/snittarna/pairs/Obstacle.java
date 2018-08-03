package se.snittarna.pairs;

import com.badlogic.gdx.math.Vector2;

public class Obstacle extends GameObject {

	public Obstacle(Vector2 position, Vector2 size, Animation sprite) {
		super(position, size, sprite);
	}
	
	public void update(float dt) {
		for(GameObject g : getScene().getObjects()) {
			if(g instanceof Player) {
				if(g.getHitbox().collision(getHitbox())) {
					((Player) g).death();
				}
			}
		}
		super.update(dt);
	}
}
