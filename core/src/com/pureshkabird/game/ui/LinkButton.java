package com.pureshkabird.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class LinkButton {
    private Rectangle bounds;
    private float height;
    private boolean isPressed = false;
    private String link;
    private TextureRegion textureRegion;
    private float width;
    private float x;
    private float y;

    public LinkButton(float x, float y, float width, float height, TextureRegion textureRegion, String link) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.textureRegion = textureRegion;
        this.link = link;
        this.bounds = new Rectangle(x, y, width, height);
    }

    public boolean isClicked(int screenX, int screenY) {
        return this.bounds.contains((float) screenX, (float) screenY);
    }

    public void draw(SpriteBatch batcher, float screenWidth) {
        batcher.draw(this.textureRegion, this.x, this.y, this.width, this.height);
        if (this.isPressed) {
            Gdx.net.openURI(this.link);
            this.isPressed = false;
        }
    }

    public boolean isTouchDown(int screenX, int screenY) {
        if (!this.bounds.contains((float) screenX, (float) screenY)) {
            return false;
        }
        this.isPressed = true;
        return true;
    }
}

