package edu.beckcentzlo.project.screens;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import edu.beckcentzlo.project.game.*;

public class HowToPlay implements Screen{

	RunnerGame runner;
	Skin skin;
	Stage stage;
	SpriteBatch batch;
	TextButtonStyle textButtonStyle, instructionButtonStyle;
	TextButton back, instructions, instructions2;
	Pixmap pixmap;
	Label label;
	Table table;
	
	public HowToPlay(RunnerGame g){
		runner = g;
		batch = new SpriteBatch ();
		skin = new Skin();
		pixmap = new Pixmap(100, 100, Format.RGBA8888);
		pixmap.setColor(Color.GREEN);
		pixmap.fill();
		table = new Table();
		
		BitmapFont bfont=new BitmapFont();
		bfont.scale(1);
		bfont.setColor(Color.RED);
		
		BitmapFont bfont2 = new BitmapFont();
		bfont.scale(.7f);
		bfont.setColor(Color.GRAY);
		
		skin.add("default", bfont);
		skin.add("black", bfont2);
		skin.add("white", new Texture(pixmap));
		
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("white", Color.GREEN);
		textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
		
		textButtonStyle.font = skin.getFont("default");
		
		instructionButtonStyle = new TextButtonStyle();
		instructionButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
		instructionButtonStyle.font = skin.getFont("black");
		
		instructions = new TextButton("Left-Click to jump from platform"
				+ " to platform and collect the burgers.  Be carefaul not to fall off!", instructionButtonStyle);
		instructions2 = new TextButton("At the end of the game, "
				+ "press ENTER to return to the Main Menu", instructionButtonStyle);
		
		
		back = new TextButton("Back to Main Menu", textButtonStyle);
		stage = new Stage();
		
	}
	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
		
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
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
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
		back.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new Menu(runner));
            }
        });
		table.add(instructions).row();
		table.add(instructions2).row();
		table.add(back).row();
		table.setFillParent(true);
		
		stage.addActor(table);
		Gdx.input.setInputProcessor(stage);

	}
	
}
