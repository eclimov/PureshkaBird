package com.pureshkabird.game.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class SwitchButton {
    private Rectangle bounds;
    private float height;
    private boolean isOn;
    private TextureRegion switchOff;
    private TextureRegion switchOn;
    private float width;
    private float x;
    private float y;

    public SwitchButton(float x, float y, float width, float height, TextureRegion switchOn, TextureRegion switchOff, boolean isOn) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.switchOn = switchOn;
        this.switchOff = switchOff;
        this.isOn = isOn;
        this.bounds = new Rectangle(x, y, width, height);
    }

    public boolean isClicked(int screenX, int screenY) {
        if (this.bounds.contains((float) screenX, (float) screenY)) {
            this.isOn = !this.isOn;
        }
        return this.bounds.contains((float) screenX, (float) screenY);
    }

    public void draw(SpriteBatch batcher, float screenWidth) {
        if (this.isOn) {
            batcher.draw(this.switchOn, this.x, this.y, this.width, this.height);
            return;
        }
        batcher.draw(this.switchOff, this.x, this.y, this.width, this.height);
    }

    public boolean getState() {
        return this.isOn;
    }
}
