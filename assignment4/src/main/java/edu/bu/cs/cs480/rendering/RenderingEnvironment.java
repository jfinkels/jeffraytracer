/**
 * RenderingEnvironment.java - 
 */
package edu.bu.cs.cs480.rendering;

import java.util.List;

import edu.bu.cs.cs480.camera.Camera;
import edu.bu.cs.cs480.camera.Resolution;
import edu.bu.cs.cs480.camera.Viewport;
import edu.bu.cs.cs480.lights.AmbientLight;
import edu.bu.cs.cs480.lights.Light;
import edu.bu.cs.cs480.surfaces.SurfaceObject;

/**
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public interface RenderingEnvironment {
  void addLight(final Light light);
  void addAmbientLight(final AmbientLight light);
  void addSurfaceObject(final SurfaceObject surfaceObject);
  void setCamera(final Camera camera);
  void setResolution(final Resolution resolution);
  void setViewport(final Viewport viewport);
  List<Light> lights();
  List<AmbientLight> ambientLights();
  List<SurfaceObject> surfaceObjects();
  Camera camera();
  Resolution resolution();
  Viewport viewport();
}
