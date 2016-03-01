package com.play.engine.state.transition;

import com.play.engine.game.GameManager;
import com.play.engine.state.State;
import com.play.engine.state.StateBasedGame;

public class BlankTransition implements Transition{

	@Override
	public void init(State current, State next) {
		
	}

	@Override
	public void preRender(GameManager gm, StateBasedGame game) {
	
	}

	@Override
	public void postRender(GameManager gm, StateBasedGame game) {
		
	}

	@Override
	public void update(GameManager gm, StateBasedGame game) {

	}

	@Override
	public boolean isComplete() {		
		return true;
	}

}
