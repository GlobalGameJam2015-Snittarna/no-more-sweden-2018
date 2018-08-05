package se.snittarna.pairs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverScene extends Scene {
	private float delay;
	private int score;
	
	public GameOverScene(int score) {
		super();
		this.score = score;
		delay = 0;
	}
	
	public void update(float dt) {
		// WOW fin KOD som FUNGERAR utan kontroller (M�N TRO)
		// Space fungerar också, men bara när minst en kontroll är inkopplad :^))))
		for (Controller controller : Controllers.getControllers()) {
			if (delay > 1 && (controller.getButton(7) || Gdx.input.isKeyJustPressed(Keys.SPACE))) Game.setCurrentScene(new StartScreen());
		}
		if (delay > 1 && Gdx.input.isKeyJustPressed(Keys.SPACE)) Game.setCurrentScene(new StartScreen());
		
		delay += 2*dt;
		
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
		AssetManager.font.draw(uiBatch, "GAME OVER", -100, 50);
		AssetManager.font.draw(uiBatch, "Score: " + score, -150, -0);
		AssetManager.font.getData().setScale(0.5f);
	}
}
