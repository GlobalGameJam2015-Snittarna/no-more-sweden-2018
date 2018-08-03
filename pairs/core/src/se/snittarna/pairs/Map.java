package se.snittarna.pairs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Map {
	
	private final static int TILESIZE = 32;
	
	private static TileType[] tileTypes = new TileType[] {
		new TileType('x', AssetManager.getTexture("indestructible-tile"), false, false),
		new TileType('d', AssetManager.getTexture("destructible-tile"), true, false),
		new TileType('s', AssetManager.getTexture("spawn"), false, true),
		new TileType('S', AssetManager.getTexture("spawn"), false, true),
		new TileType(' ', AssetManager.getTexture("grass-tile"), false, true)
	};
	
	private static TileType findTileType(char marker) {
		for (TileType t : tileTypes) {
			if (t.getMarker() == marker) return t;
		}
		System.out.println("couldnt find " + marker);
		return null;
	}
	
	/**
	 * 
	 * @param rect
	 * @param scene
	 * @return the first tile collided with, or null if none
	 */
	public static Tile collidesWihTile(Rectangle rect, Scene scene) {
		for (GameObject g : scene.getObjects()) {
			if (g instanceof Tile) {
				if (!((Tile) g).getType().isWalkable()) {
					if (g.getHitbox().collision(rect)) {
						return (Tile)g;
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * returns the first tile with the given marker in the scene, or null
	 * @param marker
	 * @param scene
	 */
	public static Tile firstTile(char marker, Scene scene) {
		System.out.println("looking for " + marker);
		System.out.println(scene.getObjects().size() + " objects");
		for (GameObject g : scene.getObjects()) {
			if (g instanceof Tile) {
				Tile t = (Tile)g;
				System.out.println("tile " + t);
				if (t.getType().getMarker() == marker) return t;
				System.out.println("not it");
			} else System.out.println(" not tile");
		}
		return null;
	}
	
	/** 
	 * loads tile locations and adds the tiles to a scene
	 * @param map
	 * which map to load. located in assets/maps
	 * @param scene
	 * which scene to add the tiles to
	 */
	public static void loadMap(int map, Scene scene) {
		String file = Gdx.files.internal("maps/" + map + ".map").readString();
		
		String[] lines = file.split("\n");
		int y = 0;
		for (String line : lines) {
			int x = 0;
			for (char c : line.toCharArray()) {
				TileType t = findTileType(c);
				if (t != null) {
					scene.addObject(new Tile(new Vector2(x, y).scl(TILESIZE), t));
					if (t.isDestructible()) scene.addObject(new Tile(new Vector2(x, y).scl(TILESIZE), findTileType(' ')));
				}
				else System.out.println("couldnt find tiletype " + c);
				x++;
			}
			y++;
		}
	}
}
