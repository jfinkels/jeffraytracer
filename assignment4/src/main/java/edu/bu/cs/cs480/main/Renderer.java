/**
 * Renderer.java -
 */
package edu.bu.cs.cs480.main;

import java.awt.image.BufferedImage;

import edu.bu.cs.cs480.FloatColor;
import edu.bu.cs.cs480.vectors.Ray;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class Renderer implements Runnable {
  private final Ray[] rays;
  private final int startRow;
  private final int endRow;
  private final int width;
  private final TracerEnvironment environment;
  private final BufferedImage result;
  private final int threadID;

  public Renderer(final Ray[] rays, final int startRow, final int endRow,
      final int width, final TracerEnvironment environment,
      final BufferedImage result,
      final int threadID) {
    this.rays = rays;
    this.startRow = startRow;
    this.endRow = endRow;
    this.width = width;
    this.environment = environment;
    
    this.result = result;
    this.threadID = threadID;
  }

  @Override
  public void run() {
    for (int y = this.startRow; y < this.endRow; ++y) {
      for (int x = 0; x < this.width; ++x) {
        final Ray ray = this.rays[y * this.width + x];
        final int color = FloatColor.toRGB(this.environment.trace(ray, 1));
        this.result.setRGB(x, y, color);
      }
    }
    synchronized (this.environment) {
      this.environment.rendererFinished(this.threadID);
      this.environment.notifyAll();
    }
  }
}
