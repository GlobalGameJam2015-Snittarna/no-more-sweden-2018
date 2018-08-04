package se.snittarna.pairs;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class AssetManager {
	/**
	 * loads the spritesheet (assets/spritesheet.png) and the list of textures (assets/textures.txt)
	 */
	public static BitmapFont font = new BitmapFont();
	
	public static void load() {
		FreeTypeFontGenerator g = new FreeTypeFontGenerator(Gdx.files.internal("RobotoMono-Regular.ttf"));
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		param.size = 20;
		font = g.generateFont(param);

		
		textureRegions = new HashMap<String, TextureRegion>();
		spriteSheet = new Texture("spritesheet.png");
		
		font.getData().setScale(0.5f);
		
		for (String s : Gdx.files.internal("textures.txt").readString().split("\n")) {
			if (s.trim().equals("")) continue;
			System.out.println(s);
			String[] vals = s.split(",");
			textureRegions.put(vals[0], new TextureRegion(spriteSheet, Integer.parseInt(vals[1]), Integer.parseInt(vals[2]), Integer.parseInt(vals[3]), Integer.parseInt(vals[4].trim())));
		}
	}
	
	/**
	 * a list of all texture regions on the sprite sheet.
	 */
	private static HashMap<String, TextureRegion> textureRegions;
	
	/**
	 * the spritesheet containing all textures.
	 */
	private static Texture spriteSheet;
	
	public static TextureRegion getTexture(String name) {
		TextureRegion ret = textureRegions.get(name);
		if (ret == null) ret = textureRegions.get("error");
		return ret;
	}
	
	private static HashMap<String, Sound> sounds;
	
	public static Sound getSound(String name) {
		if (sounds == null) {
			sounds = new HashMap<String, Sound>();
		}
		if (sounds.containsKey(name)) {
			return sounds.get(name);
		} else {
			sounds.put(name, Gdx.audio.newSound(Gdx.files.internal(name + (name.contains(".") ? "" : ".wav"))));
			return sounds.get(name);
		}
	}
}
