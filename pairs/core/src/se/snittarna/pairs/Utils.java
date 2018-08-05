
package se.snittarna.pairs;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;

public class Utils {
	public static Vector2 randomVector(float lenght, Random random) {
		float angle = (float) (random.nextFloat() * 2 * Math.PI);
		return new Vector2((float)Math.cos(angle), (float)Math.sin(angle)).scl(lenght);
	}
	
	public static Vector2 vectorFromAngle(float rads) {
		return new Vector2((float)Math.cos(rads), (float)Math.sin(rads));
	}
}
