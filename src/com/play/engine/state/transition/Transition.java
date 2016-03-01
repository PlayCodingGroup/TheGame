package com.play.engine.state.transition;

import com.play.engine.game.GameManager;
import com.play.engine.state.State;
import com.play.engine.state.StateBasedGame;

public interface Transition {

	public void init(State current, State next);
	
	public void preRender(GameManager gm, StateBasedGame game);
	
	public void postRender(GameManager gm, StateBasedGame game);
	
	public void update(GameManager gm, StateBasedGame game);
	
	public boolean isComplete();
}
