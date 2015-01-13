package edu.beckcentzlo.project.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Hero extends AnimatedGameObject {
	public Vector2 velocity;
	public float width;
	public float height;
	public boolean grounded;
	public float airTime;
	public int jumps;
	boolean buttonPressed;
	public Vector2 origin;
	public Hero() {
		super();
		buttonPressed = false;
		airTime = 0;
		jumps = 0;
		grounded = false;
		//texture = new Texture("hero.png");
		
		int FRAME_COLS = 6;
		int FRAME_ROWS = 5;
		
		texture = new Texture(Gdx.files.internal("sprite-animation4.png.gif")); // #9
        TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth()/FRAME_COLS, texture.getHeight()/FRAME_ROWS);              // #10
        TextureRegion[] frames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
            	frames[index++] = tmp[i][j];
            }
        }
        animation = new Animation(0.025f, frames);
        sprite = new Sprite(texture);
        sprite.setSize(texture.getWidth()/FRAME_COLS*2/3, texture.getHeight()/FRAME_ROWS*2/3);
        System.out.println(texture.getWidth()/FRAME_COLS+ "    " + texture.getHeight()/FRAME_ROWS);
		//sprite = new Sprite(texture);

		//sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		width = sprite.getWidth();
		height = sprite.getHeight()-6;
		origin = new Vector2(sprite.getWidth(),3);
		velocity = new Vector2(9, 0);
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		if (!grounded){
			airTime += deltaTime;
		}

		if (!Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			buttonPressed = false;
		}
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && !buttonPressed){
			buttonPressed = true;

			System.out.println(jumps);
			if (grounded || !grounded && jumps == 1) {
				jumps ++;
				airTime = 0;
				velocity.y = 20;
				grounded = false;
			}
		}
		if (velocity.y != 0 && jumps == 0){
			grounded = false;
		}
		if (grounded == true){
			paused = false;
		}else{
			paused = true;
		}
		velocity.y -= 1.5*30 * deltaTime;
		
		sprite.setPosition(sprite.getX() + velocity.x * 30 * deltaTime,
		sprite.getY()+velocity.y * 30 * deltaTime);

	}

}
