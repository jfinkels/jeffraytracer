/**
 * BaseTracerTest.java - test for the BaseTracer class
 */
package edu.bu.cs.cs480.rendering.tracers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.bu.cs.cs480.DoubleColor;
import edu.bu.cs.cs480.Material;
import edu.bu.cs.cs480.Ray;
import edu.bu.cs.cs480.Vector3D;
import edu.bu.cs.cs480.camera.Camera;
import edu.bu.cs.cs480.camera.OrthographicCamera;
import edu.bu.cs.cs480.camera.Resolution;
import edu.bu.cs.cs480.camera.Viewport;
import edu.bu.cs.cs480.lights.InfinityLight;
import edu.bu.cs.cs480.lights.Light;
import edu.bu.cs.cs480.rendering.DefaultRenderingEnvironment;
import edu.bu.cs.cs480.rendering.RenderingEnvironment;
import edu.bu.cs.cs480.surfaces.Sphere;

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
   * {@link edu.bu.cs.cs480.rendering.tracers.BaseTracer#trace(edu.bu.cs.cs480.Ray)}
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
