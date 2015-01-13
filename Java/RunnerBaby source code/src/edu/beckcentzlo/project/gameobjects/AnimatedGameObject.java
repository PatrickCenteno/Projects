package edu.beckcentzlo.project.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimatedGameObject extends GameObject {

	Animation animation; 
    float time;
    boolean repeat;
    int frame_width;
    int frame_height;
    boolean paused;
    public AnimatedGameObject(){
    	paused = false;
    	repeat = true;
    	time = 0f;
    	frame_height = 0;
    	frame_width = 0;
    }
	@Override
	public void update(float deltaTime) {
		if (!paused) time += Gdx.graphics.getDeltaTime();
		// TODO Auto-generated method stub
	}
	
	@Override
	public void render(SpriteBatch spriteBatch) {
		
		if (drawable && animation != null) {
			 TextureRegion currentFrame;
		     currentFrame = animation.getKeyFrame(time, repeat);
		    // System.out.println(currentFrame.getRegionX()+" "+currentFrame.getRegionY()+" "+currentFrame.getRegionWidth()+" "+currentFrame.getRegionHeight());
		     sprite.setRegion(currentFrame);
		     sprite.draw(spriteBatch);
		    
		}  
	}
}
