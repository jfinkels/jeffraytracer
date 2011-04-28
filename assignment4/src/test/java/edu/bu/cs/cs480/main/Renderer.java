/**
 * Renderer.java -
 */
package edu.bu.cs.cs480.main;

import java.awt.image.BufferedImage;
import java.util.Map;

import edu.bu.cs.cs480.surfaces.Intercept;
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
  private final Map<Ray, Intercept> intercepts;
  private final BufferedImage result;
  private final int threadID;

  public Renderer(final Ray[] rays, final int startRow, final int endRow,
      final int width, final TracerEnvironment environment,
      final Map<Ray, Intercept> intercepts, final BufferedImage result,
      final int threadID) {
    this.rays = rays;
    this.startRow = startRow;
    this.endRow = endRow;
    this.width = width;
    this.environment = environment;
    this.intercepts = intercepts;
    this.result = result;
    this.threadID = threadID;
  }

  @Override
  public void run() {
    synchronized (this.environment) {
      for (int y = this.startRow; y < this.endRow; ++y) {
        for (int x = 0; x < this.width; ++x) {
          final Ray ray = this.rays[y * this.width + x];
          final int color = this.environment.trace(this.intercepts.get(ray), 1);
          this.result.setRGB(x, y, color);
        }
      }
      this.environment.rendererFinished(this.threadID);
      this.environment.notifyAll();
    }
  }
}
