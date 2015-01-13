package edu.beckcentzlo.project.game;

import com.badlogic.gdx.Game;

import edu.beckcentzlo.project.screens.*;

public class RunnerGame extends Game {
	//GameScreen gameScreen;
	
	@Override
	public void create(){
		//gameScreen = new GameScreen(this);
		setScreen(new Menu(this));
	}
	
	
	@Override
	public void render(){
		getScreen().render(1f);
	}
	
}
