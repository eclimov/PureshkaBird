package com.pureshkabird.game.PBHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

	public static Texture texture, logoTexture;
	public static TextureRegion logo, zbLogo, bg, grass, bird, birdDown,
			birdUp, skullUp, skullDown, bar, playButtonUp, playButtonDown,
			ready, gameOver, highScore, scoreboard, star, noStar, retry;
	public static Animation birdAnimation;
	public static Sound dead, flap, coin1, coin2, fall, start, transition;
	public static Music music;
	public static BitmapFont font, shadow, whiteFont, creditsFont;
	private static Preferences prefs;
	public static ParticleEffect particleEffect;

	public static void load() {

		logoTexture = new Texture(Gdx.files.internal("data/transparentlogo.png"));
		logoTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		logo = new TextureRegion(logoTexture, 0, 0, 512, 128);

		texture = new Texture(Gdx.files.internal("data/nt15.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		playButtonUp = new TextureRegion(texture, 0, 332, 116, 64);
		playButtonDown = new TextureRegion(texture, 116, 332, 116, 64);
		playButtonUp.flip(false, true);
		playButtonDown.flip(false, true);
		
		ready = new TextureRegion(texture, 59*4, 83*4, 34*4, 7*4);
		ready.flip(false, true);

		retry = new TextureRegion(texture, 59*4, 110*4, 33*4, 7*4);
		retry.flip(false, true);
		
		gameOver = new TextureRegion(texture, 59*4, 92*4, 46*4, 7*4);
		gameOver.flip(false, true);

		scoreboard = new TextureRegion(texture, 111*4, 83*4, 97*4, 37*4);
		scoreboard.flip(false, true);

		star = new TextureRegion(texture, 608, 280, 40, 40);
		noStar = new TextureRegion(texture, 660, 280, 40, 40);

		star.flip(false, true);
		noStar.flip(false, true);

		highScore = new TextureRegion(texture, 236, 404, 192, 28);
		highScore.flip(false, true);

		zbLogo = new TextureRegion(texture, 0, 216, 536, 96);
		
		zbLogo.flip(false, true);

		bg = new TextureRegion(texture, 0, 0, 544, 168);
		bg.flip(false, true);

		grass = new TextureRegion(texture, 0, 168, 660, 48);
		grass.flip(false, true);

		birdDown = new TextureRegion(texture, 562, 0, 33, 48);
		birdDown.flip(false, true);

		bird = new TextureRegion(texture, 631, 0, 32, 48);
		bird.flip(false, true);

		birdUp = new TextureRegion(texture, 698, 0, 33, 48);
		birdUp.flip(false, true);

		TextureRegion[] birds = { birdDown, bird, birdUp };
		
		birdAnimation = new Animation(0.06f, birds);
		birdAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		
		birdAnimation = new Animation(0.06f, birds);
		birdAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

		skullUp = new TextureRegion(texture, 768, 0, 96, 56);
		skullUp.flip(false,  true);
		// Create by flipping existing skullUp
		skullDown = new TextureRegion(skullUp);
		skullDown.flip(false, true);

		bar = new TextureRegion(texture, 544, 64, 96, 12);
		bar.flip(false, true);

		dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
		flap = Gdx.audio.newSound(Gdx.files.internal("data/flap.wav"));
		coin1 = Gdx.audio.newSound(Gdx.files.internal("data/coin1.wav"));
		coin2 = Gdx.audio.newSound(Gdx.files.internal("data/coin2.wav"));
		fall = Gdx.audio.newSound(Gdx.files.internal("data/fall.wav"));
		start = Gdx.audio.newSound(Gdx.files.internal("data/start.wav"));
		transition = Gdx.audio.newSound(Gdx.files.internal("data/transition.wav"));
		music = Gdx.audio.newMusic(Gdx.files.internal("data/music.ogg"));
		music.setLooping(true);
		music.setVolume((float) 0.0);
		
		font = new BitmapFont(Gdx.files.internal("data/whitetext.fnt"));
		font.getData().setScale(.25f, -.25f);

		whiteFont = new BitmapFont(Gdx.files.internal("data/whitetext.fnt"));
		whiteFont.getData().setScale(.1f, -.1f);

		creditsFont = new BitmapFont(Gdx.files.internal("data/arial.fnt"));
		creditsFont.getData().setScale(.6f, .6f);
		creditsFont.setColor(Color.BLACK);
		
		shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
		shadow.getData().setScale(.25f, -.25f);

		// Create (or retrieve existing) preferences file
		prefs = Gdx.app.getPreferences("PureshkaBird");

		if (!prefs.contains("highScore")) {
			prefs.putInteger("highScore", 0);
		}
	}

	public static void setHighScore(int val) {
		prefs.putInteger("highScore", val);
		prefs.flush();
	}

	public static int getHighScore() {
		return prefs.getInteger("highScore");
	}

	public static void dispose() {
		// We must dispose of the texture when we are finished.
		texture.dispose();

		// Dispose sounds
		dead.dispose();
		flap.dispose();
		coin1.dispose();
		coin2.dispose();

		font.dispose();
		shadow.dispose();
	}

}