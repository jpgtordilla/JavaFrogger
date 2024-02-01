package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** TODO */

/**
 * 1. Finish Vehicle class
 * 2. Design collision logic in render and endgame
 */

public class Main extends ApplicationAdapter {
	public static SpriteBatch batch;
	private OrthographicCamera camera;

	@Override
	public void create () {
		Assets.createAssets();
		Frog.createFrog();
		Vehicle.createVehicles();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		batch = new SpriteBatch();
		InputManager inputProcessor = new InputManager();
		Gdx.input.setInputProcessor(inputProcessor);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		Frog.updateMotion();
		batch.begin();
		Assets.renderAssets();
		Frog.renderFrog();
		batch.end();
	}
	
	@Override
	public void dispose () {
		Frog.disposeFrog();
		Assets.disposeAssets();
	}
}
