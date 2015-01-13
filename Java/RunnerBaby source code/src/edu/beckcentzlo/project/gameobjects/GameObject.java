package edu.beckcentzlo.project.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameObject implements Updatable {
	public boolean drawable;
	public boolean updatable;
	public Sprite sprite;
	public Texture texture;

	public GameObject() {
		drawable = true;
		updatable = true;
	}

	public void render(SpriteBatch spriteBatch) {
		if (drawable)
			sprite.draw(spriteBatch);
	}
}
