package com.pureshkabird.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.pureshkabird.game.Screens.SplashScreen;
import com.pureshkabird.game.PBHelpers.AssetLoader;

public class PureshkaBirdGame extends Game {
	boolean isOnline;
	int shift;
	PureshkaBirdGame(boolean isOnline) {
        this.isOnline = isOnline;
    }

	@Override
	public void create() {
		AssetLoader.load();
		setScreen(new SplashScreen(this, this.isOnline));
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
}
