package edu.beckcentzlo.project.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;


import com.badlogic.gdx.utils.Timer;

import edu.beckcentzlo.project.game.RunnerGame;

public class GameOver implements Screen {
	
	RunnerGame runner;
	GameScreen gameScreen;
	SpriteBatch batch;
	Texture background;
	BitmapFont font;
	
	public GameOver(RunnerGame g){
		runner = g;
		batch = new SpriteBatch();
		background = new Texture(Gdx.files.internal("gameOverBackGround.png"));
		gameScreen = new GameScreen(g);
		font = new BitmapFont();
	}
	
	@Override
	public void dispose() {
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float arg0) {
		batch.begin();
		batch.draw(background, 0f, 0f);
		font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		font.draw(batch, "Your final score is: " + gameScreen.score, 250, 250);
			if(Gdx.input.isKeyPressed(Keys.ENTER))
				((Game)Gdx.app.getApplicationListener()).setScreen(new Menu(runner));

		batch.end();
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		
	}
	
	
}
