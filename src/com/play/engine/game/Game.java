package com.play.engine.game;

public interface Game {
	
	// Create textures, create player / mobs.
	public void init(GameManager gm);
	
	// States, EX: State based game, normal game, or any type of game we create.
	public void enter(GameManager gm);
	
	// Update game, player pos, entity pos, input, update everything on screen basically.
	public void update(GameManager gm);
	
	// Draws on screen, graphics, textures, etc.
	public void render(GameManager gm);
	
	// Shut down game / leave the game.
	public void leave(GameManager gm);
	
	// Return title of the game.
	public String getTitle();

}