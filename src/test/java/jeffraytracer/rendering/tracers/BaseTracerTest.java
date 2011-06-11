/**
 * BaseTracerTest.java - test for the BaseTracer class
 */
package jeffraytracer.rendering.tracers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import jeffraytracer.DoubleColor;
import jeffraytracer.Material;
import jeffraytracer.Ray;
import jeffraytracer.Vector3D;
import jeffraytracer.camera.Camera;
import jeffraytracer.camera.OrthographicCamera;
import jeffraytracer.camera.Resolution;
import jeffraytracer.camera.Viewport;
import jeffraytracer.lights.InfinityLight;
import jeffraytracer.lights.Light;
import jeffraytracer.rendering.DefaultRenderingEnvironment;
import jeffraytracer.rendering.RenderingEnvironment;
import jeffraytracer.surfaces.Sphere;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for the BaseTracer class.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class BaseTracerTest {

  /** The BaseTracer object to test. */
  private BaseTracer t = null;

  /** Set up the BaseTracer to test. */
  @Before
  public void setUp() {
    final Viewport v = new Viewport();
    v.setWidth(2);
    v.setHeight(2);

    final Resolution r = new Resolution();
    r.setXResolution(1);
    r.setYResolution(1);

    final Camera c = new OrthographicCamera();
    c.setPosition(new Vector3D(0, 0, -1));
    c.setDirection(new Vector3D(0, 0, 1));
    c.setUp(new Vector3D(0, 1, 0));
    c.setNear(0);
    c.setFar(10);

    final Light l = new InfinityLight();
    l.setPosition(new Vector3D(0, 0, -1));
    l.setDirection(new Vector3D(0, 0, 1));
    l.setColor(new DoubleColor(1, 1, 1));
    l.setAttenuationCoefficients(new Vector3D(1, 1, 1));
    l.setAttenuationExponent(1);
    l.setShadow(false);

    final Material m = new Material();
    m.setColor(new DoubleColor(1, 0, 0));
    m.setAmbientReflection(1);
    m.setDiffuseReflection(1);
    m.setSpecularReflection(1);
    m.setSpecularExponent(1);
    m.setReflection(1);
    m.setRefraction(1);
    m.setTransmission(0);

    final Sphere s = new Sphere();
    s.setPosition(new Vector3D(0, 0, 2));
    s.setRadius(.1);
    s.setMaterial(m);
    s.compile();

    final RenderingEnvironment e = new DefaultRenderingEnvironment();
    e.setViewport(v);
    e.setResolution(r);
    e.setCamera(c);
    e.addSurfaceObject(s);

    this.t = new LinearTracer(e);
  }

  /**
   * Test method for
   * {@link jeffraytracer.rendering.tracers.BaseTracer#trace(jeffraytracer.Ray)}
   * .
   */
  @Test
  public void testTrace() {
    final Ray r = new Ray();
    r.setPosition(new Vector3D(0, 0, 0));
    r.setDirection(new Vector3D(0, 0, 1));
    Vector3D color = this.t.trace(r);
    assertFalse(BaseTracer.BACKGROUND_COLOR.equalTo(color));

    r.setPosition(new Vector3D(0, -1, 0));
    color = this.t.trace(r);
    assertTrue(BaseTracer.BACKGROUND_COLOR.equalTo(color));
  }
}
