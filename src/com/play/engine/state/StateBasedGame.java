package com.play.engine.state;

import java.util.HashMap;

import com.play.engine.game.BasicGame;
import com.play.engine.game.GameManager;
import com.play.engine.state.transition.BlankTransition;
import com.play.engine.state.transition.Transition;
import com.play.engine.util.Logger;

public abstract class StateBasedGame extends BasicGame{
	
	//EX: 0 - Intro, 1- Main, 2- Game
	private HashMap<Integer, State> states = new HashMap<Integer, State>();
	
	private State currentState;
	private State nextState;
	
	private Transition leave; // Leave the state.
	private Transition enter; // Enter the next state that you want to go into.
	
	private GameManager manager;
	
	public StateBasedGame(GameManager manager, String title) {
		super(manager, title);
	}
	
	/* EX: addState(new IntroState);
	       addState(new MainState);
	       addState(new GameState); 
	       in Main.java, public void initStatesList() {}*/
	public abstract void initStatesList();

	@Override
	public void init(GameManager gm) {
		this.manager = gm;
		
		currentState = new State() {

			@Override
			public int getID() {
				return -1;
			}

			@Override
			public void init(GameManager gm, StateBasedGame game) {
			}

			@Override
			public void enter(GameManager gm, StateBasedGame game) {	
			}

			@Override
			public void update(GameManager gm, StateBasedGame game) {
			}

			@Override
			public void render(GameManager gm, StateBasedGame game) {
			}

			@Override
			public void leave(GameManager gm, StateBasedGame game) {
			}
		};
		
		initStatesList();
		for(int i = 0; i < states.size(); i++) {
			getState(i).init(manager, this);
		}
	}

	@Override
	public void enter(GameManager gm) {
		currentState.enter(gm, this);
	}

	@Override
	public void update(GameManager gm) {
		if(leave != null) {
			leave.update(gm, this);
			if(leave.isComplete()) {
				currentState.leave(gm, this);
				State previous = currentState;
				currentState = nextState;
				nextState = null;
				leave = null;
				if(enter == null) {
					currentState.enter(gm, this);
				} else {
					enter.init(currentState, previous);
				}
			} else {
				return;
			}
		}
		
		if(enter != null) {
			enter.update(gm, this);
			if(enter.isComplete()) {
				currentState.enter(gm, this);
				enter = null;
			} else {
				return;
			}
		}
		
		currentState.update(gm, this);
	}

	@Override
	public void render(GameManager gm) {
		if(leave != null) {
			leave.preRender(gm, this);
		} else if(enter != null){
			enter.preRender(gm, this);
		}
		
		currentState.render(gm, this);
		
		if(leave != null) {
			leave.postRender(gm, this);
		} else if(enter != null){
			enter.postRender(gm, this);
		}
	}

	@Override
	public void leave(GameManager gm) {
		currentState.leave(gm, this);
	}
	
	public void addState(State state) {
		states.put(state.getID(), state);
		if(state.getID() == 0) {
			currentState = state;
		}
	}
	
	// To switch states
	public void enterState(int state) {
		enterState(state, null, null); // Do not want a transition.
	}
	
	public void enterState(int state, Transition leave, Transition enter) {
		if (leave == null) {
			leave = new BlankTransition();
		}
		if (enter == null) {
			enter = new BlankTransition();
		}
		this.leave = leave;
		this.enter = enter;
		
		nextState = getState(state);
		if(nextState == null) {
			Logger.error("The state with ID " + state + " is an invalid state.");
			throw new RuntimeException();
		}
		leave.init(currentState, nextState);
	}
	
	public State getCurrentState() {
		return currentState;
	}
	
	public State getNextState() {
		return nextState;
	}
	
	public int getCurrentStateID() {
		return currentState.getID();
	}
	
	// Pass in Key, return value at index of key.
	public State getState(int id) {
		return(State) states.get(new Integer(id));
	}
	
	public boolean isTransitioning() {
		return (leave != null) || (enter != null);
	}
	
	public Transition getLeaveTransition() {
		return leave;
	}
	
	public Transition getEnterTransition() {
		return enter;
	}
}
