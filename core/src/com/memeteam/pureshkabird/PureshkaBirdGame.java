package com.memeteam.pureshkabird;

import com.badlogic.gdx.Game;
import com.memeteam.pureshkabird.Screens.SplashScreen;
import com.memeteam.pureshkabird.PBHelpers.AssetLoader;

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
