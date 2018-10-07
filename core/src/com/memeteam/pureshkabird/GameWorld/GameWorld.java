package com.memeteam.pureshkabird.GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.memeteam.pureshkabird.GameObjects.Bird;
import com.memeteam.pureshkabird.GameObjects.ScrollHandler;
import com.memeteam.pureshkabird.PBHelpers.AssetLoader;

public class GameWorld {

	private Bird bird;
	private ScrollHandler scroller;
	private Rectangle ground;
	private int score = 0;
	private float runTime = 0;
	private int midPointY;
	private GameRenderer renderer;
	
	private GameState currentState;

	public enum GameState {
		MENU, READY, RUNNING, GAMEOVER, HIGHSCORE
	}

	public GameWorld(int midPointY) {
		currentState = GameState.MENU;
		this.midPointY = midPointY;
		//bird = new Bird(33, midPointY - 5, 17, 12);
		bird = new Bird(33, midPointY - 5, 10, 12);
		// The grass should start 66 pixels below the midPointY
		scroller = new ScrollHandler(this, midPointY + 66);
		ground = new Rectangle(0, midPointY + 66, 137, 11);
	}

    public void update(float delta) {
        this.runTime += delta;
        switch (currentState) {
            case MENU:
                break;
            case READY:
                updateMusic(0.01f);
                break;
            case RUNNING:
                updateRunning(delta);
                return;
            case GAMEOVER:
                updateMusic(-0.01f);
                return;
            case HIGHSCORE:
                updateMusic(-0.01f);
                return;
            default:
                return;
        }
        updateReady(delta);
        updateMusic(0.1f);
    }

    private void updateMusic(float volumeIncrement) {
        if (!AssetLoader.getMusicSettings()) {
            AssetLoader.pauseMusic();
        } else if (volumeIncrement > 0) {
            if (!AssetLoader.music.isPlaying()) {
                AssetLoader.playMusic();
            } else if (AssetLoader.music.getVolume() < 0.5f) {
                AssetLoader.music.setVolume(AssetLoader.music.getVolume() + volumeIncrement);
            }
        } else if (AssetLoader.music.getVolume() > 0.1f) {
            AssetLoader.music.setVolume(AssetLoader.music.getVolume() + volumeIncrement);
        } else {
            AssetLoader.pauseMusic();
        }
    }

	private void updateReady(float delta) {
		bird.updateReady(runTime);
		scroller.updateReady(delta);
	}

	public void updateRunning(float delta) {
		if (delta > .15f) {
			delta = .15f;
		}

		bird.update(delta);
		scroller.update(delta);

		if (scroller.collides(bird) && bird.isAlive()) {
			scroller.stop();
			bird.die();
			AssetLoader.play(AssetLoader.dead);
			renderer.prepareTransition(255, 255, 255, .3f);

			//AssetLoader.fall.play();
		}

		if (Intersector.overlaps(bird.getBoundingCircle(), ground)) {

			if (bird.isAlive()) {
				//AssetLoader.dead.play();
				renderer.prepareTransition(255, 255, 255, .3f);

				bird.die();
			}

			scroller.stop();
			bird.decelerate();
			currentState = GameState.GAMEOVER;

			if (score > AssetLoader.getHighScore()) {
				AssetLoader.setHighScore(score);
				currentState = GameState.HIGHSCORE;
                Input input = Gdx.input;
                input.vibrate(200);
			}
            AssetLoader.play(AssetLoader.fall);
		}
	}

	public Bird getBird() {
		return bird;

	}

	public int getMidPointY() {
		return midPointY;
	}

	public ScrollHandler getScroller() {
		return scroller;
	}

	public int getScore() {
		return score;
	}

	public void addScore(int increment) {
		score += increment;
	}

	public void start() {
		currentState = GameState.RUNNING;
	}

	public void ready() {
		currentState = GameState.READY;
		renderer.prepareTransition(0, 0, 0, 1f);
	}

	public void restart() {
		score = 0;
		bird.onRestart(midPointY - 5);
		scroller.onRestart();
		ready();
	}

	public boolean isReady() {
		return currentState == GameState.READY;
	}

	public boolean isGameOver() {
		return currentState == GameState.GAMEOVER;
	}

	public boolean isHighScore() {
		return currentState == GameState.HIGHSCORE;
	}

	public boolean isMenu() {
		return currentState == GameState.MENU;
	}

	public boolean isRunning() {
		return currentState == GameState.RUNNING;
	}

	public void setRenderer(GameRenderer renderer) {
		this.renderer = renderer;
	}

}
