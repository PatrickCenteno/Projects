package edu.beckcentzlo.project.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

import edu.beckcentzlo.project.game.RunnerGame;

public class Pause implements Screen, InputProcessor {
	
	RunnerGame runner;
	GameScreen currentGameScreen;
	Skin skin;
	Stage stage;
	SpriteBatch batch;
	TextButtonStyle textButtonStyle;
	TextButton pause;
	TextButton back;
	TextButton quit;
	Table table;
	
	public Pause(RunnerGame g, GameScreen c){
		currentGameScreen = c;
		runner = g;
		batch = new SpriteBatch();
		stage = new Stage();
		skin = new Skin();
		
		Pixmap pixmap = new Pixmap(100, 100, Format.RGBA8888);
		pixmap.fill();
		
		skin.add("white", new Texture(pixmap));
		
		BitmapFont bfont=new BitmapFont();
		bfont.scale(1);
		skin.add("default",bfont);
		
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = skin.getFont("default");
		
		pause = new TextButton("PAUSE", textButtonStyle);
		back = new TextButton("Press B to return to Game", textButtonStyle);
		quit = new TextButton("Press M to return to Main Menu", textButtonStyle);
		
		table = new Table();
	}

	@Override
	public void dispose() {
		skin.dispose();
		stage.dispose();

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
		Gdx.gl.glClearColor(0.73f, 0.9f, 0.96f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();

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
		table.add(pause).row();
		table.add(back).row();
		table.add(quit).row();
		table.setFillParent(true);
		
		stage.addActor(table);
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public boolean keyDown(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char c) {
		if (c == 'b'){
			((Game)Gdx.app.getApplicationListener()).setScreen(currentGameScreen);
			dispose();
		}
		if (c == 'm'){
			((Game)Gdx.app.getApplicationListener()).setScreen(new Menu(runner));
			dispose();
		}
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

}
