package com.play.engine.main;

import com.play.engine.game.GameManager;

import com.play.engine.state.StateBasedGame;


public class Main extends StateBasedGame{
	
	private static GameManager manager;
	
	public Main(String title) {
		super(manager, title);	
	}
	public static void main(String[] args) {
		manager = new GameManager(new Main("Test Play Game"), 960, 540, true, false, true, true);
		manager.start();
		
	}
	@Override
	public void initStatesList() {
		addState(new IntroState());
		addState(new MainMenuState());
	}
}

