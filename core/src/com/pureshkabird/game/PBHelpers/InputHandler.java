package com.pureshkabird.game.PBHelpers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.pureshkabird.game.GameObjects.Bird;
import com.pureshkabird.game.GameWorld.GameWorld;
import com.pureshkabird.game.ui.LinkButton;
import com.pureshkabird.game.ui.SimpleButton;
import com.pureshkabird.game.ui.SwitchButton;

public class InputHandler implements InputProcessor {
	private Bird myBird;
	private GameWorld myWorld;

	private List<SimpleButton> menuButtons;

	private SimpleButton playButton;
	private LinkButton rateButton;

	private float scaleFactorX;
	private float scaleFactorY;

    private SwitchButton musicSwitchButton;
    private SwitchButton soundSwitchButton;

	public InputHandler(GameWorld myWorld, float scaleFactorX, float scaleFactorY) {
		this.myWorld = myWorld;
		myBird = myWorld.getBird();

		int midPointY = myWorld.getMidPointY();

		this.scaleFactorX = scaleFactorX;
		this.scaleFactorY = scaleFactorY;

		menuButtons = new ArrayList<SimpleButton>();
		playButton = new SimpleButton(
				136 / 2 - (AssetLoader.playButtonUp.getRegionWidth() / 2),
				midPointY + 10, 29, 16,
                AssetLoader.playButtonUp,
				AssetLoader.playButtonDown
        );
		menuButtons.add(playButton);

        this.rateButton = new LinkButton(
                (68 - (AssetLoader.rateLink.getRegionWidth() / 10)),
                (midPointY + 30), 46.0f, 18.0f,
                AssetLoader.rateLink,
                "https://play.google.com/store/apps/details?id=com.memeteam.pureshkabird"
        );

        this.soundSwitchButton = new SwitchButton(
                6.0f, 6.0f, 16.0f, 16.0f,
                AssetLoader.soundSwitchOn,
                AssetLoader.soundSwitchOff,
                AssetLoader.getSoundSettings()
        );

        this.musicSwitchButton = new SwitchButton(
                6.0f, 28.0f, 16.0f, 16.0f,
                AssetLoader.musicSwitchOn,
                AssetLoader.musicSwitchOff,
                AssetLoader.getMusicSettings()
        );
	}

	@Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenX = scaleX(screenX);
        screenY = scaleY(screenY);
        if (this.myWorld.isRunning()) {
            this.myBird.onClick();
        } else if (this.myWorld.isMenu()) {
            this.playButton.isTouchDown(screenX, screenY);
            if (this.soundSwitchButton.isClicked(screenX, screenY)) {
                AssetLoader.setSoundSettings(this.soundSwitchButton.getState());
                if (this.soundSwitchButton.getState()) {
                    AssetLoader.play(AssetLoader.dead);
                }
            } else if (this.musicSwitchButton.isClicked(screenX, screenY)) {
                AssetLoader.setMusicSettings(this.musicSwitchButton.getState());
            } else {
                AssetLoader.play(AssetLoader.dead);
            }
        } else if (this.myWorld.isReady()) {
            if (this.soundSwitchButton.isClicked(screenX, screenY)) {
                AssetLoader.setSoundSettings(this.soundSwitchButton.getState());
                if (this.soundSwitchButton.getState()) {
                    AssetLoader.play(AssetLoader.dead);
                }
            } else if (this.musicSwitchButton.isClicked(screenX, screenY)) {
                AssetLoader.setMusicSettings(this.musicSwitchButton.getState());
            } else {
                this.myWorld.start();
                this.myBird.onClick();
            }
        }
        if ((this.myWorld.isGameOver() || this.myWorld.isHighScore()) && !this.rateButton.isTouchDown(screenX, screenY)) {
            this.myWorld.restart();
        }
        return true;
    }

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		screenX = scaleX(screenX);
		screenY = scaleY(screenY);

        if (!this.myWorld.isMenu() || !this.playButton.isTouchUp(screenX, screenY)) {
            return false;
        }
        this.myWorld.ready();
        return true;
	}

	@Override
	public boolean keyDown(int keycode) {

		// Can now use Space Bar to play the game
		if (keycode == Keys.SPACE) {

			if (myWorld.isMenu()) {
				myWorld.ready();
			} else if (myWorld.isReady()) {
				myWorld.start();
			}

			myBird.onClick();

			if (myWorld.isGameOver() || myWorld.isHighScore()) {
				myWorld.restart();
			}

		}

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	private int scaleX(int screenX) {
		return (int) (screenX / scaleFactorX);
	}

	private int scaleY(int screenY) {
		return (int) (screenY / scaleFactorY);
	}

	public List<SimpleButton> getMenuButtons() {
		return menuButtons;
	}

    public LinkButton getRateButton() {
        return this.rateButton;
    }

    public SwitchButton getSoundSwitchButton() {
        return this.soundSwitchButton;
    }

    public SwitchButton getMusicSwitchButton() {
        return this.musicSwitchButton;
    }
}
