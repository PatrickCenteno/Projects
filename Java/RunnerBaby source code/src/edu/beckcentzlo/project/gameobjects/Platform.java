package edu.beckcentzlo.project.gameobjects;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import edu.beckcentzlo.project.game.Controller;

public class Platform extends GameObject implements Updatable {

	public float width;
	public float height;
	public Platform(int w,int h) {
		//w = 10;
		width = w;
		height = h;
		Pixmap pmap = new Pixmap((int) w, (int) h, Format.RGBA8888);
		//pmap.setColor(0.21f, 0.37f, 0.1f, 1);
		pmap.setColor(1f, 1f, 1f, 1);
		pmap.fillRectangle(0, 0, w, h);
		//pmap.fillRectangle(x, y, w, h)
		//ship = new Ship(new Texture(pmap), 100, 100);
		
		texture = new Texture(pmap);
		sprite = new Sprite(texture);
		//sprite.setSize(width,height);
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
	}

	@Override
	public void update(float deltaTime) {
		//sprite.rotate(1);
		Hero hero = Controller.controller.hero;
		//System.out.println((hero.sprite.getX()+hero.width/2)  + "     " +  (sprite.getX()-width/2)  + "     " +(hero.sprite.getX() - hero.width/2)   + "     " +  (sprite.getX()+width/2));
		//if (hero.velocity.y <= 0 && hero.sprite.getX()+hero.width/2 > sprite.getX()-width/2  && hero.sprite.getX() - hero.width/2  < sprite.getX()+width/2 && hero.sprite.getY()+hero.height/2 > sprite.getY()-height/2  && hero.sprite.getY() - hero.height/2  < sprite.getY()+height/2){
		if (hero.velocity.y <= 0 && hero.sprite.getX()+hero.origin.x+hero.width/2 > sprite.getX() && hero.sprite.getX()+hero.origin.x-hero.width/2 < sprite.getX()+width && hero.sprite.getY()+hero.origin.y > sprite.getY() && hero.sprite.getY()+hero.origin.y < sprite.getY()+height){
			hero.grounded = true;
			hero.jumps = 0;
			hero.sprite.setY(sprite.getY()+height-hero.origin.y );
			hero.velocity.y = 0;
		}
	}

}
