package com.play.engine.main;

import static org.lwjgl.opengl.GL11.*;

import com.play.engine.game.GameManager;
import com.play.engine.input.Keyboard;
import com.play.engine.state.State;
import com.play.engine.state.StateBasedGame;
import com.play.engine.util.Logger;

public class IntroState implements State{

	@Override
	public int getID() {
		return 0;
	}

	@Override
	public void init(GameManager gm, StateBasedGame game) {
		Logger.debug("Intro state init");
	}

	@Override
	public void enter(GameManager gm, StateBasedGame game) {
		Logger.debug("Intro state enter!");
	}

	@Override
	public void update(GameManager gm, StateBasedGame game) {
		if(Keyboard.isPressed(Keyboard.KEY_ESCAPE)) {
			game.enterState(1);
		}
	}

	@Override
	public void render(GameManager gm, StateBasedGame game) {
		glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
	}

	@Override
	public void leave(GameManager gm, StateBasedGame game) {
		Logger.debug("Intro state leave!");
	}

}
