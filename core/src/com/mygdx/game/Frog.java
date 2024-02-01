package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class Frog {
    private static Texture frogImage;
    private static Texture resizedFrogImage;
    private static Rectangle frog;
    private static int targetY;
    // made static since only one frog player is needed (no need for setter methods)
    public static boolean upMove = false;
    public static boolean downMove = false;
    private static boolean frogCanMove = true;
    public static long lastMoveTime = 0;

    public static void createFrog() {
        frogImage = new Texture(Gdx.files.local("frog.png"));
        frog = new Rectangle();
        frog.width = Constants.FROG_WIDTH;
        frog.height = Constants.FROG_HEIGHT;
        frog.x = (float) Constants.SCREEN_WIDTH / 2 - frog.width / 2;
        frog.y = Constants.FROG_START_Y;
    }

    public static void renderFrog() {
        Main.batch.draw(frogImage, frog.x, frog.y);
    }

    public static void disposeFrog() {
        frogImage.dispose();
    }

    // call this every frame in Main.render()
    public static void updateMotion() {
        // if a fraction of a second has passed, the frog can move again
        if (TimeUtils.nanoTime() - lastMoveTime > Constants.MOVE_INTERVAL) {
            frogCanMove = true;
            // update timer
            lastMoveTime = TimeUtils.nanoTime();
        }
        if (upMove && frogCanMove) {
            if (frog.y < Constants.SCREEN_HEIGHT - frog.height) {
                frog.y += frog.height / 2;
            }
            frogCanMove = false;
        }
        if (downMove && frogCanMove) {
            if (frog.y > frog.width / 4) {
                frog.y -= frog.height / 2;
            }
            frogCanMove = false;
        }
    }
}
