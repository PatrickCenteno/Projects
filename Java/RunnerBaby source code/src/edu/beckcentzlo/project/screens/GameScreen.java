package edu.beckcentzlo.project.screens;

import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.beckcentzlo.project.game.*;
import edu.beckcentzlo.project.gameobjects.*;

public class GameScreen implements Screen, InputProcessor { //All input must be done here, not in the controller class
	public Controller controller;
	public BitmapFont scoreFont;
	private SpriteBatch spriteBatch;
	private RunnerGame runner;
	int score;
	String scoreText;
	public Music backgroundNoise;
	public Sound screamSound;
	public Sound biteSound;
	
	public GameScreen(RunnerGame g){
		super();
		
		screamSound = Gdx.audio.newSound(Gdx.files.internal("scream.wav"));
		biteSound = Gdx.audio.newSound(Gdx.files.internal("80551__ggctuk__comic-bite-2.wav"));
		backgroundNoise = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		backgroundNoise.setLooping(true);
		spriteBatch = new SpriteBatch();
		controller = new Controller(this);
		scoreFont = new BitmapFont();
		score = 0;
		scoreText = "Burger Score : ";
		runner = g;
		backgroundNoise.play();
	}
	
	public void Scream(){
		screamSound.play();
	}
	public void Bite(){
		score++;
		scoreText = "Burger Score: " + score;
		biteSound.play();
	}
	public void dispose(){
		if(backgroundNoise != null) backgroundNoise.dispose();
		if(screamSound != null) screamSound.dispose();
		if(biteSound != null) biteSound.dispose();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		backgroundNoise.stop();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float arg0) {
		controller.update(Gdx.graphics.getDeltaTime()); // UPDATE CURRENT SCREEN
	
		//RENDER STUFF ON CURRENT SCREEN
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		Random rand = new Random();
		///float finalX = rand.nextFloat();
		//Gdx.gl.glClearColor(0.73f, 0.9f, 0.96f, 1.0f);
		Gdx.gl.glClearColor(controller.randR, controller.randG, controller.randB, controller.randA);
		spriteBatch.setProjectionMatrix(controller.camera.combined);
		spriteBatch.begin();
		scoreFont.setColor(1f, 1f, 1f, 1f);
		scoreFont.draw(spriteBatch, scoreText, controller.camera.position.x - 375, 100);
		for(GameObject gObj : controller.getDrawableObjects()){
			if (gObj.drawable) gObj.render(spriteBatch);
		}
		spriteBatch.end();
		
	}
	public void gameOver(){
		((Game)Gdx.app.getApplicationListener()).setScreen(new GameOver(runner));
		
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
		Gdx.input.setInputProcessor(this);
		
	}

	@Override
	public boolean keyDown(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean keyTyped(char c) {
		if(c == 'p'){
			((Game)Gdx.app.getApplicationListener()).setScreen(new Pause(runner, this));
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
