package edu.beckcentzlo.project.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import edu.beckcentzlo.project.game.Constants;

public class Asteroid extends GameObject implements Updatable {

	private float rotationalVel;

	public Asteroid() {
		texture = new Texture("Asteroid_tex.png");
		sprite = new Sprite(texture);
		sprite.setSize(Constants.ASTEROIDS_SIZE, Constants.ASTEROIDS_SIZE);
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
	}

	@Override
	public void update(float deltaTime) {
		sprite.rotate(rotationalVel); // TODO: Student, use delta time here

		// Student, create Asteroid behavior here.

	}

}
