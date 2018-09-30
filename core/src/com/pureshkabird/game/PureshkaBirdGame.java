package com.pureshkabird.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.pureshkabird.game.Screens.SplashScreen;
import com.pureshkabird.game.PBHelpers.AssetLoader;

public class PureshkaBirdGame extends Game {
	int shift;
	PureshkaBirdGame(int s){
		shift = s;
	}

	@Override
	public void create() {
		AssetLoader.load();
		setScreen(new SplashScreen(this,shift));
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
}
