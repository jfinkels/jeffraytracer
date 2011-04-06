/**
 * ModelReader.java - reads a tracer model from a file
 */
package edu.bu.cs.cs480.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.bu.cs.cs480.AmbientLight;
import edu.bu.cs.cs480.FloatColor;
import edu.bu.cs.cs480.Identifiable;
import edu.bu.cs.cs480.InfinityLight;
import edu.bu.cs.cs480.Light;
import edu.bu.cs.cs480.Material;
import edu.bu.cs.cs480.PointLight;
import edu.bu.cs.cs480.Vector3D;
import edu.bu.cs.cs480.camera.Camera;
import edu.bu.cs.cs480.camera.OrthographicCamera;
import edu.bu.cs.cs480.camera.PerspectiveCamera;
import edu.bu.cs.cs480.camera.Resolution;
import edu.bu.cs.cs480.camera.Viewport;
import edu.bu.cs.cs480.surfaces.Box;
import edu.bu.cs.cs480.surfaces.ConstructiveSolidGeometry;
import edu.bu.cs.cs480.surfaces.Cylinder;
import edu.bu.cs.cs480.surfaces.Ellipsoid;
import edu.bu.cs.cs480.surfaces.Intersection;
import edu.bu.cs.cs480.surfaces.Orientation;
import edu.bu.cs.cs480.surfaces.Sphere;
import edu.bu.cs.cs480.surfaces.SurfaceObject;
import edu.bu.cs.cs480.surfaces.SymmetricDifference;
import edu.bu.cs.cs480.surfaces.Union;

/**
 * Reads a TracerEnvironment model from a file.
 * 
 * The file must be in the format specified by the model_file_format.txt file.
 * 
 * @author Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
 * @since Spring 2011
 */
public class ModelReader {

  public static TracerEnvironment fromFile(final String filename)
      throws FileNotFoundException, FileFormatException {
    final TracerEnvironment result = new TracerEnvironment();
    final Scanner input = new Scanner(new File(filename));

    // TODO use hashmap so we don't have to iterate to find objects by id
    final List<Material> materials = new ArrayList<Material>();
    final List<SurfaceObject> surfaceObjects = new ArrayList<SurfaceObject>();
    List<Integer> toRender = null;

    while (input.hasNextLine()) {
      final String token = input.next();
      if (token.equals("camera")) {
        result.setCamera(readCamera(input));
      } else if (token.equals("resolution")) {
        result.setResolution(readResolution(input));
      } else if (token.equals("viewport")) {
        result.setViewport(readViewport(input));
      } else if (token.equals("light")) {
        result.addLight(readLight(input));
      } else if (token.equals("mat")) {
        materials.add(readMaterial(input));
      } else if (token.equals("obj")) {
        surfaceObjects
            .add(readSurfaceObject(input, materials, surfaceObjects));
      } else if (token.equals("render")) {
        toRender = readIntegerList(input);
      } else {
        throw new FileFormatException("Do not understand declaration \""
            + token + "\".");
      }
    }

    final List<SurfaceObject> toAdd;
    if (toRender.contains(0)) {
      toAdd = surfaceObjects;
    } else {
      toAdd = new ArrayList<SurfaceObject>();
      for (final int id : toRender) {
        toAdd.add(getObjectWithID(surfaceObjects, id));
      }
    }

    for (final SurfaceObject surfaceObject : toAdd) {
      result.addSurfaceObject(surfaceObject);
    }

    return result;
  }

  protected static <E extends Identifiable> E getObjectWithID(
      final List<E> list, final int id) {
    for (final E element : list) {
      if (element.id() == id) {
        return element;
      }
    }

    return null;
  }

  protected static Box readBox(final Scanner input,
      final List<Material> materials) {
    final Box box = new Box();

    input.next(); // throw away the string "ID"
    final int id = input.nextInt();
    box.setId(id);

    // throw away the strings "mat" and "ID"
    input.next();
    input.next();
    final int materialID = input.nextInt();
    box.setMaterial(getObjectWithID(materials, materialID));

    box.setPosition(readTriple(input));
    box.setOrientation(readOrientation(input));
    box.setDimensions(readTriple(input));

    return box;
  }

  protected static Camera readCamera(final Scanner input)
      throws FileFormatException {
    final Camera camera;

    final String projectionType = input.next();

    final Vector3D center = readTriple(input);
    final Vector3D lookAt = readTriple(input);
    final Vector3D up = readTriple(input);

    double focalLength = input.nextDouble();
    if (projectionType.equals("orthographic")) {
      camera = new OrthographicCamera();
    } else if (projectionType.equals("perspective")) {
      camera = new PerspectiveCamera();
      ((PerspectiveCamera) camera).setFocalLength(focalLength);
    } else {
      throw new FileFormatException(
          "Do not understand camera projection type \"" + projectionType
              + "\".");

    }

    camera.setCenter(center);
    camera.setLookAt(lookAt);
    camera.setUp(up);

    double near = input.nextDouble();
    double far = input.nextDouble();
    camera.setNear(near);
    camera.setFar(far);

    return camera;
  }

  protected static FloatColor readColor(final Scanner input) {
    final float red = input.nextFloat();
    final float green = input.nextFloat();
    final float blue = input.nextFloat();
    return new FloatColor(red, green, blue);
  }

  protected static ConstructiveSolidGeometry readCSG(final Scanner input,
      final List<SurfaceObject> surfaceObjects) throws FileFormatException {
    final ConstructiveSolidGeometry result;

    input.next(); // throw away the string "ID"
    final int id = input.nextInt();

    final String type = input.next();

    input.next(); // throw away the string "ID"
    final int leftId = input.nextInt();

    input.next(); // throw away the string "ID"
    final int rightId = input.nextInt();

    final SurfaceObject leftObject = getObjectWithID(surfaceObjects, leftId);
    final SurfaceObject rightObject = getObjectWithID(surfaceObjects, rightId);

    if (type.equals("union")) {
      result = new Union(leftObject, rightObject);
    } else if (type.equals("intersect")) {
      result = new Intersection(leftObject, rightObject);
    } else if (type.equals("difference")) {
      result = new SymmetricDifference(leftObject, rightObject);
    } else {
      throw new FileFormatException("Do not understand CSG type \"" + type
          + "\".");
    }

    result.setId(id);

    return result;
  }

  protected static Cylinder readCylinder(final Scanner input,
      final List<Material> materials) {
    final Cylinder cylinder = new Cylinder();

    input.next(); // throw away the string "ID"
    final int id = input.nextInt();
    cylinder.setId(id);

    // throw away the strings "mat" and "ID"
    input.next();
    input.next();
    final int materialID = input.nextInt();
    cylinder.setMaterial(getObjectWithID(materials, materialID));

    cylinder.setPosition(readTriple(input));
    cylinder.setDirection(readTriple(input));

    final double radius = input.nextDouble();
    final double length = input.nextDouble();
    cylinder.setRadius(radius);
    cylinder.setLength(length);

    return cylinder;
  }

  protected static Ellipsoid readEllipsoid(final Scanner input,
      final List<Material> materials) {
    final Ellipsoid ellipsoid = new Ellipsoid();

    input.next(); // throw away the string "ID"
    final int id = input.nextInt();
    ellipsoid.setId(id);

    // throw away the strings "mat" and "ID"
    input.next();
    input.next();
    final int materialID = input.nextInt();
    ellipsoid.setMaterial(getObjectWithID(materials, materialID));

    ellipsoid.setPosition(readTriple(input));
    ellipsoid.setRadii(readTriple(input));

    return ellipsoid;
  }

  protected static List<Integer> readIntegerList(final Scanner input) {
    final List<Integer> result = new ArrayList<Integer>();
    while (input.hasNextInt()) {
      result.add(input.nextInt());
    }
    return result;
  }

  protected static Light readLight(final Scanner input)
      throws FileFormatException {
    final Light light;

    input.next(); // throw away the string "ID"
    final int id = input.nextInt();
    final String lightType = input.next();

    if (lightType.equals("inf")) {
      light = new InfinityLight();
    } else if (lightType.equals("amb")) {
      light = new AmbientLight();
    } else if (lightType.equals("pnt")) {
      light = new PointLight();
    } else {
      throw new RuntimeException();
    }

    light.setId(id);

    light.setPosition(readTriple(input));
    light.setDirection(readTriple(input));

    light.setColor(readColor(input));

    light.setAttenuationCoefficients(readTriple(input));

    final int exponent = input.nextInt();
    light.setAttenuationExponent(exponent);

    final String shadow = input.next();
    if (shadow.equals("shadow_on")) {
      light.setShadow(true);
    } else if (shadow.equals("shadow_off")) {
      light.setShadow(false);
    } else {
      throw new FileFormatException("Do not understand shadow \"" + shadow
          + "\".");
    }

    return light;
  }

  protected static Material readMaterial(final Scanner input) {
    final Material material = new Material();

    input.next(); // throw away the string "ID"
    final int id = input.nextInt();
    material.setId(id);

    material.setColor(readColor(input));

    final double ambientCoefficient = input.nextDouble();
    final double diffuseCoefficient = input.nextDouble();
    final double specularCoefficient = input.nextDouble();
    material.setAmbientReflection(ambientCoefficient);
    material.setDiffuseReflection(diffuseCoefficient);
    material.setSpecularReflection(specularCoefficient);

    final double specularExponent = input.nextDouble();
    material.setSpecularExponent(specularExponent);

    final double transmission = input.nextDouble();
    final double reflection = input.nextDouble();
    final double refraction = input.nextDouble();
    material.setTransmission(transmission);
    material.setReflection(reflection);
    material.setRefraction(refraction);

    return material;
  }

  protected static Orientation readOrientation(final Scanner input) {
    final Vector3D v1 = readTriple(input);
    final Vector3D v2 = readTriple(input);
    final Vector3D v3 = readTriple(input);

    return new Orientation(v1, v2, v3);
  }

  protected static Resolution readResolution(final Scanner input) {
    final double x = input.nextDouble();
    final double y = input.nextDouble();

    final Resolution resolution = new Resolution();
    resolution.setxResolution(x);
    resolution.setyResolution(y);

    return resolution;
  }

  protected static Sphere readSphere(final Scanner input,
      final List<Material> materials) {
    final Sphere sphere = new Sphere();

    input.next(); // throw away the string "ID"
    final int id = input.nextInt();
    sphere.setId(id);

    // throw away the strings "mat" and "ID"
    input.next();
    input.next();
    final int materialID = input.nextInt();
    sphere.setMaterial(getObjectWithID(materials, materialID));

    sphere.setPosition(readTriple(input));

    final double radius = input.nextDouble();
    sphere.setRadius(radius);

    return sphere;
  }

  // post-condition: list of surface objects is not modified
  protected static SurfaceObject readSurfaceObject(final Scanner input,
      final List<Material> materials, final List<SurfaceObject> surfaceObjects)
      throws FileFormatException {
    final SurfaceObject surfaceObject;

    final String type = input.next();
    if (type.equals("sphere")) {
      surfaceObject = readSphere(input, materials);
    } else if (type.equals("ellipsoid")) {
      surfaceObject = readEllipsoid(input, materials);
    } else if (type.equals("cylinder")) {
      surfaceObject = readCylinder(input, materials);
    } else if (type.equals("box")) {
      surfaceObject = readBox(input, materials);
    } else if (type.equals("CSG")) {
      surfaceObject = readCSG(input, surfaceObjects);
    } else {
      throw new FileFormatException("Do not understand surface object type \""
          + type + "\".");
    }

    return surfaceObject;
  }

  protected static Vector3D readTriple(final Scanner input) {
    double x = input.nextDouble();
    double y = input.nextDouble();
    double z = input.nextDouble();
    return new Vector3D(x, y, z);
  }

  protected static Viewport readViewport(final Scanner input) {
    final int width = input.nextInt();
    final int height = input.nextInt();

    final Viewport viewport = new Viewport();
    viewport.setWidth(width);
    viewport.setHeight(height);

    return viewport;
  }
}
