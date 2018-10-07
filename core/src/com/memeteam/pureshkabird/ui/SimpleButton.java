package com.memeteam.pureshkabird.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.memeteam.pureshkabird.PBHelpers.AssetLoader;

public class SimpleButton {

	private float x, y, width, height;

	private TextureRegion buttonUp;
	private TextureRegion buttonDown;

	private Rectangle bounds;

	private boolean isPressed = false;

	public SimpleButton(float x, float y, float width, float height,
			TextureRegion buttonUp, TextureRegion buttonDown) {
		//this.x = x;
		this.x = 0;
		this.y = y;
		this.width = width;
		this.height = height;
		this.buttonUp = buttonUp;
		this.buttonDown = buttonDown;

		bounds = new Rectangle(x, y, width, height);

	}

	public boolean isClicked(int screenX, int screenY) {
		return bounds.contains(screenX, screenY);
	}

	public void draw(SpriteBatch batcher, float screenWidth) {
		if(x >= screenWidth){
			x = -width;
			bounds.x = -width;
			AssetLoader.play(AssetLoader.transition);
		}
		x+=0.3;
		bounds.x+=0.3;
		if (isPressed) {
			batcher.draw(buttonDown, x, y, width, height);
		} else {
			batcher.draw(buttonUp, x, y, width, height);
		}
	}

	public boolean isTouchDown(int screenX, int screenY) {

		if (bounds.contains(screenX, screenY)) {
			isPressed = true;
			return true;
		}

		return false;
	}

	public boolean isTouchUp(int screenX, int screenY) {
		//Gdx.net.openURI("https://play.google.com/store/apps/details?id=com.memeteam.pureshkabird");
		// It only counts as a touchUp if the button is in a pressed state.
		if (bounds.contains(screenX, screenY) && isPressed) {
			isPressed = false;
            AssetLoader.play(AssetLoader.flap);
			return true;
		}
		
		// Whenever a finger is released, we will cancel any presses.
		isPressed = false;
		return false;
	}

}