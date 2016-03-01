package com.play.engine.graphics;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

public class Util {

	public static FloatBuffer VertexToFloatBuffer3(float x, float y, float z) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(3);
		buffer.put(x);
		buffer.put(y);
		buffer.put(z);
		buffer.position(0);
		return buffer;
	}
	
	public static FloatBuffer VertexToFloatBuffer4(float x, float y, float z, float w) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(4);
		buffer.put(x);
		buffer.put(y);
		buffer.put(z);
		buffer.put(w);
		buffer.position(0);
		return buffer;
	}
	
}
