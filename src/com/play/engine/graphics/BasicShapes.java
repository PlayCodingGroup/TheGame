package com.play.engine.graphics;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;
import static org.lwjgl.opengl.GL11.*;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;

public class BasicShapes {

	public static void drawCube(float size, double x, double y, double z) {
		
		GL11.glTranslated(x, y, z);
		
		FloatBuffer _0 = Util.VertexToFloatBuffer3(-size, -size, -size);
		FloatBuffer _1 = Util.VertexToFloatBuffer3(size, -size, -size);
		FloatBuffer _2 = Util.VertexToFloatBuffer3(-size, size, -size);
		FloatBuffer _3 = Util.VertexToFloatBuffer3(size, size, -size);
		FloatBuffer _4 = Util.VertexToFloatBuffer3(-size, -size, size);
		FloatBuffer _5 = Util.VertexToFloatBuffer3(size, -size, size);
		FloatBuffer _6 = Util.VertexToFloatBuffer3(-size, size, size);
		FloatBuffer _7 = Util.VertexToFloatBuffer3(size, size, size);

		glBegin(GL_QUADS); // front
		GL11.glNormal3f(0f, 0f, 1f);
		glVertex3fv(_0);
		glVertex3fv(_1);
		glVertex3fv(_3);
		glVertex3fv(_2);
		glEnd();
		
		glBegin(GL_QUADS); // right
		GL11.glNormal3f(1f, 0f, 0f);
		glVertex3fv(_1);
		glVertex3fv(_5);
		glVertex3fv(_7);
		glVertex3fv(_3);
		glEnd();

		glBegin(GL_QUADS); // back
		GL11.glNormal3f(0f, 0f, -1f);
		glVertex3fv(_5);
		glVertex3fv(_4);
		glVertex3fv(_6);
		glVertex3fv(_7);
		glEnd();

		glBegin(GL_QUADS); // left
		GL11.glNormal3f(-1f, 0f, 0f);
		glVertex3fv(_4);
		glVertex3fv(_0);
		glVertex3fv(_2);
		glVertex3fv(_6);
		glEnd();
			
		glBegin(GL_QUADS); // top
		GL11.glNormal3f(0f, 1f, 0f);
		glVertex3fv(_2);
		glVertex3fv(_3);
		glVertex3fv(_7);
		glVertex3fv(_6);
		glEnd();
		
		glBegin(GL_QUADS); // bot
		GL11.glNormal3f(0f, -1f, 0f);
		glVertex3fv(_4);
		glVertex3fv(_5);
		glVertex3fv(_1);
		glVertex3fv(_0);
		glEnd();
		
		GL11.glTranslated(-x, -y, -z);
	}
	
	

}
