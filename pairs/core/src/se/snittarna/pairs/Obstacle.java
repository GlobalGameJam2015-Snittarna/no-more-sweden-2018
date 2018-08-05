package se.snittarna.pairs;

import com.badlogic.gdx.math.Vector2;

public class Obstacle extends GameObject {	
	private float speed;
	
	public Obstacle(Vector2 position, Vector2 size, Animation sprite) {
		super(position, size, sprite);
	}
	
	public void update(float dt) {
		if(getPosition().y <= -64) getScene().removeObject(this);
		setPosition(new Vector2(getPosition().x, getPosition().y-speed*dt));
		for(GameObject g : getScene().getObjects()) {
			if(g instanceof Level) 
				speed = ((Level) g).getSpeed();
			if(g instanceof Player) {
				if(g.getHitbox().collision(getHitbox())) {
					float score = ((GameScene) getScene()).getScore();
					Game.setCurrentScene(new GameOverScene((int)score));
				}
			}
		}
		super.update(dt);
	}
}
