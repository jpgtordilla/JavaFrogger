package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class InputManager implements InputProcessor {
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                // normally would call a setter method, but no frog instance created
                Frog.upMove = true;
                Frog.downMove = false;
                break;
            case Input.Keys.DOWN:
                Frog.downMove = true;
                Frog.upMove = false;
                break;
            default:
                break;
        }
        return true;
    }

    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                Frog.upMove = false;
                break;
            case Input.Keys.DOWN:
                Frog.downMove = false;
                break;
            default:
                break;
        }
        return true;
    }

    public boolean keyTyped(char character) {
        return false;
    }

    public boolean touchDown(int x, int y, int pointer, int button) {
        return false;
    }

    public boolean touchUp(int x, int y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    public boolean touchDragged(int x, int y, int pointer) {
        return false;
    }

    public boolean mouseMoved(int x, int y) {
        return false;
    }

    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}

