package edu.beckcentzlo.project.gameobjects;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import edu.beckcentzlo.project.game.Controller;

public class Burger extends GameObject implements Updatable {

	private float rotationalVel;
	public int width;
	public int height;
	
	public Burger() {
		texture = new Texture("burger-clip-art-4.png");
		sprite = new Sprite(texture);
		sprite.setSize(40,40);
		width = 40;
		height = 40;
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		Random rand = new Random();
		rotationalVel = rand.nextFloat()*30-15;
	}

	@Override
	public void update(float deltaTime) {
		sprite.rotate(rotationalVel); // TODO: Student, use delta time here
		Hero hero = Controller.controller.hero;
		//System.out.println((hero.sprite.getX()+hero.width/2)  + "     " +  (sprite.getX()-width/2)  + "     " +(hero.sprite.getX() - hero.width/2)   + "     " +  (sprite.getX()+width/2));
		//if (hero.velocity.y <= 0 && hero.sprite.getX()+hero.width/2 > sprite.getX()-width/2  && hero.sprite.getX() - hero.width/2  < sprite.getX()+width/2 && hero.sprite.getY()+hero.height/2 > sprite.getY()-height/2  && hero.sprite.getY() - hero.height/2  < sprite.getY()+height/2){
		if (drawable && hero.sprite.getX()+hero.origin.x+hero.width/2 > sprite.getX() && hero.sprite.getX()+hero.origin.x-hero.width/2 < sprite.getX()+width && hero.sprite.getY()+hero.origin.y+hero.height > sprite.getY() && hero.sprite.getY()+hero.origin.y < sprite.getY()+height){
			drawable = false;
			Controller.controller.Bite();
		}
		// Student, create Asteroid behavior here.

	}

}
