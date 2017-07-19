package com.wsgames.escape;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.swing.text.View;

public class Escape extends Game{

	public static final float screenWidth = 800;
	public static final float screenHeight = 1400;

	Viewport viewport;
	OrthographicCamera camera;

	// TODO use this later
	TextureAtlas uiAtlas;

	AssetManager assetManager;

	@Override
	public void create () {

		camera = new OrthographicCamera();
		camera.position.set(screenWidth/2,screenHeight/2,0);
		viewport = new FillViewport(screenWidth,screenHeight,camera);

		assetManager = new AssetManager();

		// TODO uncomment when ui pack is created
		//assetManager.load("ui.pack",TextureAtlas.class);
		assetManager.load("bg.png",Texture.class);
		assetManager.load("button.png",Texture.class);
		assetManager.load("grid.png",Texture.class);
		assetManager.load("player.png",Texture.class);
		assetManager.load("wall.png",Texture.class);
		assetManager.finishLoading();

		setScreen(new GameScene(this));

	}

	@Override
	public void render () {
        super.render();
	}
	
	@Override
	public void dispose () {}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		viewport.update(width,height);
	}
}
