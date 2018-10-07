package com.memeteam.pureshkabird.Screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.memeteam.pureshkabird.TweenAccessors.SpriteAccessor;
import com.memeteam.pureshkabird.PBHelpers.AssetLoader;
import com.memeteam.pureshkabird.PureshkaBirdGame;

public class SplashScreen implements Screen {

	private TweenManager manager;
	private SpriteBatch batcher;
	private Sprite sprite;
	private PureshkaBirdGame game;
	boolean isOnline;
	private int shift = 0;
	
	public SplashScreen(PureshkaBirdGame game, boolean isOnline) {
		this.game = game;
        this.isOnline = isOnline;
        if (isOnline) {
            this.shift = 100;
        }
	}

	@Override
	public void show() {
		sprite = new Sprite(AssetLoader.logo);
		sprite.setColor(1, 1, 1, 0);

		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		float scale = width * .7f / sprite.getWidth();

		sprite.setSize(sprite.getWidth() * scale, sprite.getHeight() * scale);
		sprite.setPosition((width / 2) - (sprite.getWidth() / 2), (height / 2)
				- (sprite.getHeight() / 2));
		setupTween();
		batcher = new SpriteBatch();
	}

	private void setupTween() {
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());
		manager = new TweenManager();

		TweenCallback cb = new TweenCallback() {
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				game.setScreen(new GameScreen());
                AssetLoader.play(AssetLoader.start);
			}
		};

		Tween.to(sprite, SpriteAccessor.ALPHA, (float) 1.3).target(1)
				.ease(TweenEquations.easeInOutQuad).repeatYoyo(1, .4f)
				.setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE)
				.start(manager);
	}

	@Override
	public void render(float delta) {
		manager.update(delta);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batcher.begin();
		sprite.draw(batcher);
		//System.out.println(Gdx.graphics.getWidth()+" x "+Gdx.graphics.getHeight());
		AssetLoader.creditsFont.draw(batcher, "Eduard Climov",
				5,
				Gdx.graphics.getHeight()-Gdx.graphics.getHeight()+(float)40+(float)shift);
		AssetLoader.creditsFont.draw(batcher, "Alex Onuferco",
				Gdx.graphics.getWidth() - (float)130,
				Gdx.graphics.getHeight()-Gdx.graphics.getHeight()+(float)40+(float)shift);
		AssetLoader.creditsFont.draw(batcher, "meme3team@gmail.com",
				Gdx.graphics.getWidth()/2 - (float)108,
				Gdx.graphics.getHeight()-Gdx.graphics.getHeight()+(float)20+(float)shift);
		batcher.end();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
