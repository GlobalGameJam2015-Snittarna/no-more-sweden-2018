package se.snittarna.pairs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StartScreen extends Scene {
	private float delay;
	
	public StartScreen() {
		super();
		delay = 0;
		// lol dab
		GameScene.jumpToDeathScreen = 0;
	}
	
	public void update(float dt) {
		for (Controller controller : Controllers.getControllers()) {
			if(delay > 1 && (controller.getButton(7) || Gdx.input.isKeyJustPressed(Keys.SPACE))) Game.setCurrentScene(new GameScene());
		}
		
		delay += 2*dt;
		
		
		boolean controllerNext = false;
		for (Controller c : Controllers.getControllers()) if (c.getButton(0)) controllerNext = true;
		
		if(Gdx.input.isKeyJustPressed(Keys.SPACE) || controllerNext ) {
			Game.setCurrentScene(new GameScene());
		}
		
		super.update(dt);
	}
	
	public void drawGame(SpriteBatch gameBatch) {
		Animation background = new Animation(new Sprite(AssetManager.getTexture("cover")));
		background.setPosition(-320, -240);
		background.draw(gameBatch);
		super.drawGame(gameBatch);
	}
	
	public void drawUi(SpriteBatch uiBatch) {
		super.drawUi(uiBatch);
		AssetManager.font.getData().setScale(0.7f);
		AssetManager.font.draw(uiBatch, "PRESS START TO START", -100, 50);
		AssetManager.font.draw(uiBatch, "Cooperate to get as far as possible.", -150, -0);
		AssetManager.font.getData().setScale(0.5f);
	}
}
