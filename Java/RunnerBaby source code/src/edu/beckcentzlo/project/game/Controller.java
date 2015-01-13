package edu.beckcentzlo.project.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.List;

import edu.beckcentzlo.project.gameobjects.Asteroid;
import edu.beckcentzlo.project.gameobjects.Burger;
import edu.beckcentzlo.project.gameobjects.GameObject;
import edu.beckcentzlo.project.gameobjects.Hero;
import edu.beckcentzlo.project.gameobjects.Platform;
import edu.beckcentzlo.project.screens.GameScreen;

public class Controller{
	
	public boolean isDead;
	public boolean isScreaming;
	public OrthographicCamera camera;
	public ArrayList<GameObject> drawableObjects; 
	public static Controller controller;
	public Hero hero;
	private GameScreen screen;
	public float randR, randG,
			randB, randA;
	private int timer;
	
	public Controller(GameScreen screen){
		Random rand = new Random();
		isDead = false;
		isScreaming = false;
		this.screen = screen;
		//add hero
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		hero = new Hero();
		hero.sprite.setPosition(0, 500);
		drawableObjects = new ArrayList<GameObject>();
		drawableObjects.add(hero);

		randR = rand.nextFloat();
		randG = rand.nextFloat();
		randB = rand.nextFloat();
		randA = rand.nextFloat();
		
		//for (int i =0;i<60;i++){
			Platform platform = new Platform(1400,5050);
			drawableObjects.add(platform);
			platform.sprite.setPosition(0, -5000);
		//}
			
		controller = this;
		
		
		//screen.backgroundNoise.play();
		//backgroundNoise.setVolume(0.5f);
	}
	
	private int nextSpawn = 0;
	public void update(float deltaTime) {
		//Spawn new platforms
		Random rand = new Random();
		
		if (camera.position.x >= nextSpawn){
			int ammount = 7+rand.nextInt(3);
			//ArrayList<Integer> heights = new ArrayList<Integer>();
			for (int i=0;i<ammount;i++){
				Platform platform = new Platform(90+rand.nextInt(180),30);
				drawableObjects.add(0,platform);
				platform.sprite.setPosition((int)camera.position.x + 1000+ rand.nextInt(800), (i)*(460/ammount)+rand.nextInt(20));
				
			}
			ammount = 1+rand.nextInt(6);
			for (int i=0;i<ammount;i++){
				Burger burger = new Burger();
				drawableObjects.add(burger);
				burger.sprite.setPosition((int)camera.position.x + 1000+ rand.nextInt(800), rand.nextInt(480));
				
			}
			nextSpawn += 800;
		}
		
		
		// Update All Objects
		for(int i=0; i<drawableObjects.size();i++){
			GameObject gObg = (GameObject) drawableObjects.get(i);
			if (gObg.updatable) gObg.update(deltaTime);
			if (gObg.sprite.getX()+gObg.sprite.getWidth() < camera.position.x-400){
				drawableObjects.remove(gObg);
				i--;
			}
		}
		
		//Camera follow hero
		camera.position.x = hero.sprite.getX()+230;
		camera.position.y = 240;
		camera.update();
		
		if (hero.sprite.getY() < -250 && !isScreaming){
			isScreaming = true;
			screen.Scream();
		}
		if (hero.sprite.getY() < -4000 && !isDead){
			isDead = true;
			screen.gameOver();
		}
		timer ++;
		if (timer %50 == 0){
			randR = rand.nextFloat();
			randG = rand.nextFloat();
			randB = rand.nextFloat();
			randA = rand.nextFloat();
		}
		
	}

	public ArrayList<GameObject> getDrawableObjects() {
		return drawableObjects;
	}
	public void Bite() {
		// TODO Auto-generated method stub
		screen.Bite();
	}
	
	
	
}
