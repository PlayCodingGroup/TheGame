package com.play.engine.graphics;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;

import com.play.engine.input.Keyboard;
import com.play.engine.input.Mouse;
import com.play.engine.input.MousePosition;
import com.play.engine.util.Logger;



public class Window {
	private GLFWErrorCallback errorCallback; //error checking
	private GLFWKeyCallback keyCallback;//keyboard input
	private GLFWMouseButtonCallback mouseButtonCallback;//mouse button input
	private GLFWCursorPosCallback cursorPosCallback;//position of cursor inside of window
	private GLFWFramebufferSizeCallback frameBufferCallback;//what is inside window, graphics, ETC.
	private GLFWWindowSizeCallback windowSizeCallback;//Window Size
	
	private Keyboard keyboard;
	private Mouse mouse;
	private MousePosition position;
	
	private long window;
	
	private String title;
	private int WIDTH, HEIGHT;
	private boolean vsync, fullscreen, visible, resizable;                                                             
	
	public Window(String title, int WIDTH, int HEIGHT, boolean vsync, boolean fullscreen, boolean visible, boolean resizable) {
		this.title = title;
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.vsync = vsync;
		this.fullscreen = fullscreen;
		this.visible = visible;
		this.resizable = resizable;
		
		init();
		
	}
	private void init() {
		if(glfwInit() != GL_TRUE) {
			Logger.error("Failed to initialize GLFW");
			throw new IllegalStateException();
			
		}
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, visible ? GL_TRUE: GL_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, resizable ? GL_TRUE: GL_FALSE);
		
		window = glfwCreateWindow(WIDTH, HEIGHT, title, fullscreen? glfwGetPrimaryMonitor(): NULL, NULL);
		if(window == NULL) {
			Logger.error("Failed to create the GLFW window.");
			new RuntimeException();
			
		}
		
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (vidmode.width() - WIDTH)/2, (vidmode.height() -HEIGHT)/2);
		
		glfwMakeContextCurrent(window);
		
		errorCallback = GLFWErrorCallback.createPrint();
		errorCallback.set();
		
		frameBufferCallback = new GLFWFramebufferSizeCallback() {

			@Override
			public void invoke(long window, int width, int height) {
				glViewport(0, 0, width, height);	
			}
		};
		frameBufferCallback.set(window);
		
		keyboard = new Keyboard();
		keyCallback = keyboard;
		keyCallback.set(window);
		mouse = new Mouse();
		mouseButtonCallback = mouse;
		mouseButtonCallback.set(window);
		position = new MousePosition();
		cursorPosCallback = position;
		cursorPosCallback.set(window);
		
		windowSizeCallback = new GLFWWindowSizeCallback() {

			@Override
			public void invoke(long window, int width, int height) {
				Window.this.WIDTH = width;
				Window.this.HEIGHT = height;
			}
			
		};
		windowSizeCallback.set(window);
		
		glfwSwapInterval(vsync ? 1 : 0); //If you want vsync put true in constructor = limit to 60 fps, if you do not then it will not.
		
		GL.createCapabilities();
	}
	
	public void clear() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); //Every frame method is called, it will clear all colors & depth, so you can set them to the new frames colors.
	}
	
	public void update() {
		glfwSwapBuffers(window);
	}
	
	public void updateInput() {
		keyboard.update();
		mouse.update();
		glfwPollEvents();
	}
	public void dispose() {
		keyCallback.release();
		mouseButtonCallback.release();
		cursorPosCallback.release();
		frameBufferCallback.release();
		windowSizeCallback.release();
		glfwTerminate();
		errorCallback.release();
	}
	
	public void close() {
		glfwSetWindowShouldClose(window, GL_TRUE);
	}
	public boolean shouldClose() {
		return glfwWindowShouldClose(window) == GL_TRUE;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getWIDTH() {
		return WIDTH;
	}
	public void setWIDTH(int WIDTH) {
		this.WIDTH = WIDTH;
	}
	public int getHEIGHT() {
		return HEIGHT;
	}
	public void setHEIGHT(int HEIGHT) {
		this.HEIGHT = HEIGHT;
	}
	public boolean vsync() {
		return vsync;
	}
	public void setVsync(boolean vsync) {
		this.vsync = vsync;
		glfwSwapInterval(vsync ? 1:0);
	}
	public boolean fullscreen() {
		return fullscreen;
	}
	public void setFullscreen(boolean fullscreen) {
		// TODO: gotta do this...
		this.fullscreen = fullscreen;
	}
	public boolean visible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
		if(visible) {
			GLFW.glfwShowWindow(window);
		}else{
			glfwHideWindow(window);
		}
	}
	public boolean resizable() {
		return resizable;
	}
}
