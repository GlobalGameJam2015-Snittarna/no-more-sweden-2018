package se.snittarna.pairs;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Projectile extends GameObject {
	public enum Types {BULLET, DEACCELERTING, ROCKET};
	
	private float speed;
	private float angle;
	private float startSpeed;
	private float opacity;
	
	private int tag;
	
	private Types type;
	
	private boolean byCurrentPlayer;
	
	private Random random;
	
	public boolean isByCurrentPlayer() {
		return byCurrentPlayer;
	}
	
	public Projectile(Vector2 position, Vector2 size, Animation sprite, float angle, float speed, int tag, boolean byCurrentPlayer, Types type) {
		super(position, size, sprite);
		setOriginCenter();
		this.angle = angle;
		this.startSpeed = speed;
		this.speed = speed;
		
		this.tag = tag;
		
		this.byCurrentPlayer = byCurrentPlayer;
		
		setRotation(angle*57.2957795f);
		
		opacity = 1f;
		
		this.type = type;
		
		random = new Random();
	}
	
	public void update(float dt) {
		setPosition(getPosition().add(moveDirection(dt).cpy()));
		
		Tile c = Map.collidesWihTile(getHitbox(), getScene());
		if(c != null && type == Types.ROCKET) {
			getScene().addObject(new Explosion(new Vector2(getPosition().x-32, getPosition().y-32)));
			int np = 100;
			for (int i = 0; i < np; i++) {
				float angle = i *360 / np + random.nextFloat() * 10;
				getScene().addObject(new Particle(getPosition(), new Vector2((float)Math.cos(angle), (float)Math.sin(angle)).scl(100 + random.nextFloat() * 200), Color.GRAY, 3));
			}
			AssetManager.getSound("death").play();
		}
		if (c != null && c.getType().isDestructible()) { 
			getScene().removeObject(c);
			AssetManager.getSound("shot").play();
			if (c.getType().getMarker() == 'd') {
				int np = 10;
				for (int i = 0; i < np; i++) {
					float angle = i *360 / np + random.nextFloat() * 10;
					getScene().addObject(new Particle(getPosition(), new Vector2((float)Math.cos(angle), (float)Math.sin(angle)).scl(50 + random.nextFloat() * 100), Color.BROWN, 8));
				}
			}
		}
		if (c != null && !c.getType().isWalkable()) {
			getScene().removeObject(this);
			int np = 5;
			for (int i = 0; i < np; i++) {
				float angle = i *360 / np + random.nextFloat() * 10;
				getScene().addObject(new Particle(getPosition(), new Vector2((float)Math.cos(angle), (float)Math.sin(angle)).scl(50 + random.nextFloat() * 100), Color.YELLOW, 2));
			}
			
		}
		
		if(type == Types.DEACCELERTING) {
			speed = lerp(speed, 0, 2*dt);
			if(speed <= 16) opacity = lerp(opacity, 0, 4*dt);
			getSprite().setColor(1,  1, 1, opacity);
		}
		
		super.update(dt);
	}
	
	public Types getType() {
		return type;
	}
	
	public Vector2 moveDirection(float dt) {
		return new Vector2(((float)Math.cos(angle)*speed)*dt, ((float)Math.sin(angle)*speed)*dt);
	}
	
	public int getTag() {
		return tag;
	}
}
