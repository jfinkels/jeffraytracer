// ************************************************************************
// Tank Class.
// This class draws a checkered ground and the bounding box of the tank.
// ************************************************************************
//
package edu.bu.cs.cs480.shapes;

import java.nio.ByteBuffer;

import javax.media.opengl.GL;

import com.sun.opengl.util.BufferUtil;

import edu.bu.cs.cs480.drawing.Displayable;

public class Tank implements Displayable {
  private static final int CHECK_IMAGE_WIDTH = 64;
  private static final int CHECK_IMAGE_HEIGHT = 64;

  private final float w, h, d; // width, height and depth
  private int tank_object;
  private ByteBuffer checkImageBuf = //
  BufferUtil.newByteBuffer(CHECK_IMAGE_WIDTH * CHECK_IMAGE_HEIGHT * 3);

  public Tank(float w, float h, float d) {
    this.w = w;
    this.h = h;
    this.d = d;
  }

  // Original C code was from the Red Book. The Java version is from
  // Kiet Le website "Red Book Examples using JOGL"
  // http://ak.kiet.le.googlepages.com/theredbookinjava.html
  private void makeCheckImage() {
    byte c = (byte) 0xFF;
    for (int i = 0; i < CHECK_IMAGE_WIDTH; i++) {
      for (int j = 0; j < CHECK_IMAGE_HEIGHT; j++) {
        c = (byte) ((((byte) ((i & 0x8) == 0 ? 0x00 : 0xff)//
        ^ (byte) ((j & 0x8) == 0 ? 0x00 : 0xff))));
        this.checkImageBuf.put((byte) c);
        this.checkImageBuf.put((byte) c);
        this.checkImageBuf.put((byte) c);
      }
    }
    this.checkImageBuf.rewind();
  }

  private void initTexture(GL gl) {
    makeCheckImage();

    gl.glPixelStorei(GL.GL_UNPACK_ALIGNMENT, 1);
    gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, 3, CHECK_IMAGE_WIDTH,
        CHECK_IMAGE_HEIGHT, 0, GL.GL_RGB, GL.GL_UNSIGNED_BYTE, this.checkImageBuf);
    gl.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_CLAMP);
    gl.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL.GL_CLAMP);
    gl.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER,
        GL.GL_NEAREST);
    gl.glTexParameterf(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER,
        GL.GL_NEAREST);
    gl.glTexEnvf(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_DECAL);
  }

  @Override
  public void initialize(GL gl) {
    // Create the display list for the Tank.
    initTexture(gl);
    this.tank_object = gl.glGenLists(1);

    gl.glNewList(this.tank_object, GL.GL_COMPILE);
    gl.glBegin(GL.GL_LINE_STRIP);
    gl.glVertex3f(w / 2, h / 2, d / 2);
    gl.glVertex3f(-w / 2, h / 2, d / 2);
    gl.glVertex3f(-w / 2, -h / 2, d / 2);
    gl.glVertex3f(w / 2, -h / 2, d / 2);
    gl.glVertex3f(w / 2, h / 2, d / 2);
    gl.glVertex3f(w / 2, h / 2, -d / 2);
    gl.glVertex3f(-w / 2, h / 2, -d / 2);
    gl.glVertex3f(-w / 2, -h / 2, -d / 2);
    gl.glVertex3f(w / 2, -h / 2, -d / 2);
    gl.glVertex3f(w / 2, h / 2, -d / 2);
    gl.glEnd();
    gl.glBegin(GL.GL_LINES);
    gl.glVertex3f(-w / 2, -h / 2, d / 2);
    gl.glVertex3f(-w / 2, -h / 2, -d / 2);
    gl.glVertex3f(-w / 2, h / 2, d / 2);
    gl.glVertex3f(-w / 2, h / 2, -d / 2);
    gl.glVertex3f(w / 2, -h / 2, d / 2);
    gl.glVertex3f(w / 2, -h / 2, -d / 2);
    gl.glEnd();
    gl.glEnable(GL.GL_TEXTURE_2D);
    gl.glBegin(GL.GL_QUADS);
    gl.glTexCoord2f(1.0f, 1.0f);
    gl.glVertex3f(w / 2, -h / 2, d / 2);
    gl.glTexCoord2f(1.0f, 0.0f);
    gl.glVertex3f(w / 2, -h / 2, -d / 2);
    gl.glTexCoord2f(0.0f, 0.0f);
    gl.glVertex3f(-w / 2, -h / 2, -d / 2);
    gl.glTexCoord2f(0.0f, 1.0f);
    gl.glVertex3f(-w / 2, -h / 2, d / 2);
    gl.glEnd();
    gl.glDisable(GL.GL_TEXTURE_2D);
    gl.glEndList();
  }

  @Override
  public void draw(GL gl) {
    // draw the tank
    gl.glPushAttrib(GL.GL_CURRENT_BIT);
    gl.glColor3f(0.65f, 0.0f, 0.45f); // purple
    gl.glCallList(this.tank_object);
    gl.glPopAttrib();
  }
}
