package se.snittarna.pairs;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TileType {
	private char marker; // represents it in map file
	private TextureRegion texture;
	private boolean destructible, walkable;
	
	public boolean isWalkable() {
		return walkable;
	}

	public char getMarker() {
		return marker;
	}

	public TextureRegion getTexture() {
		return texture;
	}

	public boolean isDestructible() {
		return destructible;
	}

	public TileType(char marker, TextureRegion texture, boolean destructible, boolean walkable) {
		this.marker = marker;
		this.texture = texture;
		this.destructible = destructible;
		this.walkable = walkable;
	}
}
