package com.play.engine.game;

import com.play.engine.graphics.Window;

public class GameManager {
	
	private Game game;
	private Window window;
	
	private int FPS, UPS;
	private int targetUPS = 60;
	
	private boolean running;
	
	public GameManager(Game game, int WIDTH, int HEIGHT, boolean vsync, boolean fullscreen, boolean visible, boolean resizable){
		this.game = game;
		this.window = new Window(game.getTitle(), WIDTH, HEIGHT, vsync, fullscreen, visible, resizable);
	}
	
	public void init() {
		game.init(this);
	}
	
	public void enter() {
		game.enter(this);
	}
	
	public void update() {
		window.updateInput();
		game.update(this);
	}
	
	public void render() {
		window.clear();
		game.render(this);
		window.update();
	}
	
	public void leave() {
		game.leave(this);
	}
	
	public void start() {
		init();
		
		long lastTime = System.nanoTime();
		int frames = 0, updates = 0;
		double delta = 0.0;
		double ns = 1000000000.0 / targetUPS;
		long timer = System.currentTimeMillis();
		running = true;
		
		while (true) {
			if(window.shouldClose() || !isRunning()) {
				shutdown();
				break;
			}
			
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1.0) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				UPS = updates;
				FPS = frames;
				updates = 0;
				frames = 0;
			}
		}
	}
	
	public void shutdown() {
		window.dispose();
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public Game getGame() {
		return game;
	}
	
	public Window getWindow() {
		return window;
	}
	
	public int getFrames() {
		return FPS;
	}
	
	public int getUpdates() {
		return UPS;
	}
	
	public int getTargetUpdates() {
		return targetUPS;
	}
	
	public String getTitle() {
		return game.getTitle();
	}

}

	