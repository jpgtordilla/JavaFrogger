package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class Main extends ApplicationAdapter {
	public static SpriteBatch batch;
	private OrthographicCamera camera;
	public static Sound jumpSound;

	@Override
	public void create() {
		Assets.createAssets();
		Frog.createFrog();
		// create two vehicle and spawn
		Vehicle.createVehicles();
		camera = new OrthographicCamera();
		// https://libgdx.com/wiki/start/a-simple-game
		camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		batch = new SpriteBatch();
		InputManager inputProcessor = new InputManager();
		Gdx.input.setInputProcessor(inputProcessor);
		// music (same source)
		jumpSound = Gdx.audio.newSound(Gdx.files.internal("jump.mp3"));
        Music levelMusic = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		levelMusic.setLooping(true);
		levelMusic.setVolume(0.3f); // https://libgdx.com/wiki/audio/streaming-music
		levelMusic.play();
	}

	@Override
	public void render() {
		ScreenUtils.clear(0, 0, 0, 1); // draw a black screen
		camera.update();

		batch.setProjectionMatrix(camera.combined);
		Frog.updateMotion();
		batch.begin();
		Assets.renderAssets();
		Frog.renderFrog();
		// render all elements in the vehicle array
		for (Vehicle vehicle : Vehicle.vehicleArray) {
			batch.draw(vehicle.vehicleImage, vehicle.vehicleRectangle.x, vehicle.vehicleRectangle.y);
		}
		batch.end();
		// after time interval has passed, generate two more vehicles
		if (TimeUtils.nanoTime() - Vehicle.lastSpawnTime > Constants.SPAWN_INTERVAL) { // https://libgdx.com/wiki/start/a-simple-game
			Vehicle.createVehicles();
		}
		// handle vehicle movement
		Vehicle.moveVehicles();
	}
	
	@Override
	public void dispose() {
		Frog.disposeFrog();
		Assets.disposeAssets();
	}
}
