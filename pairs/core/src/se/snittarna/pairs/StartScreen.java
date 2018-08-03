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
	}
	
	public void update(float dt) {
		for (Controller controller : Controllers.getControllers()) {
			if(delay > 1) if(controller.getButton(7)) Game.setCurrentScene(new GameScene());
		}
		
		delay += 2*dt;
		
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
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
		AssetManager.font.draw(uiBatch, "Left thumbstick to move\nRight thumbstick to shoot and aim\nWhen round time runs out the round restarts \nand ghost that follows your past movment appears", -150, -0);
		AssetManager.font.getData().setScale(0.5f);
	}
}
