package se.snittarna.pairs;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Light extends GameObject{
	Animation a = new Animation(new Sprite(AssetManager.getTexture("plot")));
	public Light() {
		super(new Vector2(0, 0), new Vector2(0, 0), new Animation(new Sprite(AssetManager.getTexture("plot"))));
		a.setSize(8, 8);
		this.setOrder(10);
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
				p = g.getPosition().add(new Vector2(1, 1).scl(32));
			}
		}
		
		Vector2 carPointingVector = Utils.vectorFromAngle(r);
	
		p.add(carPointingVector.cpy().scl(32));
		
		Vector2 v1 = new Vector2(), v2 = new Vector2();
		
		for(int y = 0; y < 480/8; y++) {
			for(int x = 0; x < 640/8; x++) {
				boolean c = false;
				
				boolean shouldDraw = true;

				v1.set(x, y).scl(8).sub(p.cpy().add(new Vector2(0, 12).rotate((float) Math.toDegrees(r))));
				v2.set(x, y).scl(8).sub(p.cpy().add(new Vector2(0, -12).rotate((float) Math.toDegrees(r))));
				
				if (v1.len() < 250 && v1.nor().dot(carPointingVector) > .9) shouldDraw = false;
				if (v2.len() < 250 && v2.nor().dot(carPointingVector) > .9) shouldDraw = false;

				if (shouldDraw) {
					a.setColor(0, 0, 0, 1f);
					a.setPosition(x*8, y*8);
					a.draw(batch);
				}
			}
		}
		ps.clear();
	}
	
	public void draw(SpriteBatch batch) {
		drawLight(batch);
		super.draw(batch);
	}
}