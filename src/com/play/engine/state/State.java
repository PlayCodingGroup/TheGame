package com.play.engine.state;

import com.play.engine.game.GameManager;

public interface State {
	// EX: Intro State, Main Menu state, Game state, Pause state, Player Selection state.
	
	public int getID(); // Every state will have an ID.
	
	public void init(GameManager gm, StateBasedGame game);
	
	public void enter(GameManager gm, StateBasedGame game);
	
	public void update(GameManager gm, StateBasedGame game);
	
	public void render(GameManager gm, StateBasedGame game);

	public void leave(GameManager gm, StateBasedGame game);
	

}
