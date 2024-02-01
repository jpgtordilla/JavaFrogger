package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Assets {
    private static Texture fieldImage;
    private static Texture roadImage;
    private static Texture riverImage;
    private static Rectangle field;
    private static Rectangle road;
    private static Rectangle river;

    public static void createAssets() {
        fieldImage = new Texture(Gdx.files.local("field.png"));
        roadImage = new Texture(Gdx.files.local("road.jpeg"));
        riverImage = new Texture(Gdx.files.local("blue.png"));

        field = new Rectangle();
        field.width = Constants.SCREEN_WIDTH;
        field.height = Constants.SCREEN_HEIGHT;
        field.x = 0;
        field.y = 0;

        road = new Rectangle();
        road.width = Constants.SCREEN_WIDTH;
        road.height = Constants.SCREEN_HEIGHT;
        road.x = 0;
        road.y = Constants.ROAD_Y;

        river = new Rectangle();
        river.width = Constants.SCREEN_WIDTH;
        river.height = Constants.SCREEN_HEIGHT;
        river.x = 0;
        river.y = Constants.RIVER_Y;
    }

    public static void renderAssets() {
        Main.batch.draw(fieldImage, field.x, field.y);
        Main.batch.draw(roadImage, road.x, road.y);
        Main.batch.draw(riverImage, river.x, river.y);
    }

    public static void disposeAssets() {
        fieldImage.dispose();
        roadImage.dispose();
        riverImage.dispose();
    }
}
