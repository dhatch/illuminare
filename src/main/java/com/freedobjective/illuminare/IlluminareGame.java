package com.freedobjective.illuminare;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class IlluminareGame implements ApplicationListener {

	private OrthographicCamera camera;
	
	@Override
	public void create() {
		
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void render() {
		// Camera 
		camera.update();
		camera.apply(Gdx.gl20);
	}

	@Override
	public void resize(int width, int height) {
		float aspectRatio = (float) width/ (float)height;
		camera = new OrthographicCamera(2f * aspectRatio, 2f);
	}

	@Override
	public void resume() {
		
	}
    
}
