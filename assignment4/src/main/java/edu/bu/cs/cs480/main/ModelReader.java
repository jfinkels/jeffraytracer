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
import edu.bu.cs.cs480.surfaces.Orientation;
import edu.bu.cs.cs480.surfaces.Sphere;
import edu.bu.cs.cs480.surfaces.SurfaceObject;
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
  /**
   * The identifier in the model file format for an orthographic projection
   * camera.
   */
  public static final String ORTHOGRAPHIC = "orthographic";
  /**
   * The identifier in the model file format for a perspective projection
   * camera.
   */
  public static final String PERSPECTIVE = "perspective";
  /**
   * The identifier for the union operation on surface objects in the model file
   * format.
   */
  public static final String UNION = "union";
  /**
   * The identifier for the intersection operation on surface objects in the
   * model file format.
   */
  public static final String INTERSECTION = "intersect";
  /**
   * The identifier for the symmetric difference operation on surface objects in
   * the model file format.
   */
  public static final String SYMMETRIC_DIFFERENCE = "difference";
  /**
   * The identifier for a light source at distance infinity in the model file
   * format.
   */
  public static final String INFINITY = "inf";
  /**
   * The identifier for an ambient light source in the model file format.
   */
  public static final String AMBIENT = "amb";
  /**
   * The identifier for a point light source in the model file format.
   */
  public static final String POINT = "pnt";
  /** The identifier for the camera definition in the model file format. */
  public static final String CAMERA = "camera";
  /** The identifier for the resolution definition in the model file format. */
  public static final String RESOLUTION = "resolution";
  /** The identifier for the viewport definition in the model file format. */
  public static final String VIEWPORT = "viewport";
  /** The identifier for a light definition in the model file format. */
  public static final String LIGHT = "light";
  /** The identifier for a material definition in the model file format. */
  public static final String MATERIAL = "mat";
  /** The identifier for an object definition in the model file format. */
  public static final String OBJECT = "obj";
  /** The identifier for a render list definition in the model file format. */
  public static final String RENDER = "render";

  /**
   * Reads a complete tracer environment from the model file at the specified
   * location.
   * 
   * @param filename
   *          The name of the file containing the description of the tracer
   *          model.
   * @return A tracer environment which can render the scene as described in the
   *         model file.
   * @throws FileNotFoundException
   *           If no file exists at the specified location.
   * @throws FileFormatException
   *           If the file is not in the correct format, as specified by
   *           "model_file_format.txt".
   */
  public static TracerEnvironment fromFile(final String filename)
      throws FileNotFoundException, FileFormatException {
    final TracerEnvironment result = new TracerEnvironment();
    final Scanner input = new Scanner(new File(filename));

    // TODO use hashmap so we don't have to iterate to find objects by id
    final List<Material> materials = new ArrayList<Material>();
    final List<SurfaceObject> surfaceObjects = new ArrayList<SurfaceObject>();
    List<Integer> toRender = null;

    while (input.hasNext()) {
      final String token = input.next();
      if (token.equals(CAMERA)) {
        result.setCamera(readCamera(input));
      } else if (token.equals(RESOLUTION)) {
        result.setResolution(readResolution(input));
      } else if (token.equals(VIEWPORT)) {
        result.setViewport(readViewport(input));
      } else if (token.equals(LIGHT)) {
        result.addLight(readLight(input));
      } else if (token.equals(MATERIAL)) {
        materials.add(readMaterial(input));
      } else if (token.equals(OBJECT)) {
        surfaceObjects.add(readSurfaceObject(input, materials, surfaceObjects));
      } else if (token.equals(RENDER)) {
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

  /**
   * Iterates over the specified list and returns the object with the specified
   * ID, or {@code null} if no such element exists in the list.
   * 
   * @param <E>
   *          The type of element in the list.
   * @param list
   *          The list of elements through which to search.
   * @param id
   *          The ID of the element to find in the list.
   * @return The object in the list with the specified ID number, or
   *         {@code null} if no such element exists.
   */
  protected static <E extends Identifiable> E getObjectWithID(
      final List<E> list, final int id) {
    for (final E element : list) {
      if (element.id() == id) {
        return element;
      }
    }

    return null;
  }

  /**
   * Creates a box with the properties specified on the current line of the
   * scanner.
   * 
   * @param input
   *          The scanner from which to read the properties of the box.
   * @param materials
   *          The list of known materials which the input will reference when
   *          describing the material of the box by its ID number.
   * @return A box with the properties specified on the current line of the
   *         scanner.
   */
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

  /**
   * Creates a camera with the properties specified on the current line of the
   * scanner.
   * 
   * @param input
   *          The scanner from which to read the properties of the camera.
   * @return A camera with the properties specified on the current line of the
   *         scanner.
   * @throws FileFormatException
   *           If the projection type is not of a known type.
   */
  protected static Camera readCamera(final Scanner input)
      throws FileFormatException {
    final Camera camera;

    final String projectionType = input.next();

    final Vector3D center = readTriple(input);
    final Vector3D lookAt = readTriple(input);
    final Vector3D up = readTriple(input);

    double focalLength = input.nextDouble();
    if (projectionType.equals(ORTHOGRAPHIC)) {
      camera = new OrthographicCamera();
    } else if (projectionType.equals(PERSPECTIVE)) {
      camera = new PerspectiveCamera();
      ((PerspectiveCamera) camera).setFocalLength(focalLength);
    } else {
      throw new FileFormatException(
          "Do not understand camera projection type \"" + projectionType
              + "\".");

    }

    camera.setPosition(center);
    camera.setDirection(lookAt.difference(center).normalized());
    camera.setUp(up.normalized());

    double near = input.nextDouble();
    double far = input.nextDouble();
    camera.setNear(near);
    camera.setFar(far);

    return camera;
  }

  /**
   * Reads the next three float values between 0 and 1 from the Scanner and
   * returns the corresponding color.
   * 
   * @param input
   *          The scanner from which to read the component values.
   * @return The color whose component values are read from the specified input.
   */
  protected static FloatColor readColor(final Scanner input) {
    final float red = input.nextFloat();
    final float green = input.nextFloat();
    final float blue = input.nextFloat();
    return new FloatColor(red, green, blue);
  }

  /**
   * Creates a union, intersection, or symmetric difference object from two
   * other surface objects.
   * 
   * Post-condition: the list of surface objects is not modified.
   * 
   * @param input
   *          The scanner from which to read the properties of this constructive
   *          solid geometry object.
   * @param surfaceObjects
   *          The list of known surface objects which the input will reference
   *          when describing the two surface objects by ID number which
   *          comprise this constructive solid geometry object.
   * @return A union, intersection, or symmetric difference object of two other
   *         surface objects, as specified by the input.
   * @throws FileFormatException
   *           If the constructive solid geometry operation is not of a known
   *           type.
   */
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

    if (type.equals(UNION)) {
      result = new Union(leftObject, rightObject);
    } else if (type.equals(INTERSECTION)) {
      //result = new Intersection(leftObject, rightObject);
      result = null;
    } else if (type.equals(SYMMETRIC_DIFFERENCE)) {
      //result = new SymmetricDifference(leftObject, rightObject);
      result = null;
    } else {
      throw new FileFormatException("Do not understand CSG type \"" + type
          + "\".");
    }

    result.setId(id);

    return result;
  }

  /**
   * Creates a cylinder with the properties specified on the current line of the
   * scanner.
   * 
   * @param input
   *          The scanner from which to read the properties of the cylinder.
   * @param materials
   *          The list of known materials which the input will reference when
   *          describing the material of the cylinder by its ID number.
   * @return A cylinder with the properties specified on the current line of the
   *         scanner.
   */
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
    cylinder.setDirection(readTriple(input).normalized());

    final double radius = input.nextDouble();
    final double length = input.nextDouble();
    cylinder.setRadius(radius);
    cylinder.setLength(length);

    return cylinder;
  }

  /**
   * Creates an ellipsoid with the properties specified on the current line of
   * the scanner.
   * 
   * @param input
   *          The scanner from which to read the properties of the ellipsoid.
   * @param materials
   *          The list of known materials which the input will reference when
   *          describing the material of the ellipsoid by its ID number.
   * @return An ellipsoid with the properties specified on the current line of
   *         the scanner.
   */
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

  /**
   * Reads a sequence of consecutive integers of arbitrary length from the
   * specified input.
   * 
   * @param input
   *          The scanner from which to read the sequence of consecutive
   *          integers.
   * @return A list of integers read from the input.
   */
  protected static List<Integer> readIntegerList(final Scanner input) {
    final List<Integer> result = new ArrayList<Integer>();
    while (input.hasNextInt()) {
      result.add(input.nextInt());
    }
    return result;
  }

  /**
   * Creates a light with the properties specified on the current line of the
   * scanner.
   * 
   * @param input
   *          The scanner from which to read the properties of the light.
   * @return A light with the properties specified on the current line of the
   *         scanner.
   * @throws FileFormatException
   *           If the specified type of light is not recognized.
   */
  protected static Light readLight(final Scanner input)
      throws FileFormatException {
    final Light light;

    input.next(); // throw away the string "ID"
    final int id = input.nextInt();
    final String lightType = input.next();

    if (lightType.equals(INFINITY)) {
      light = new InfinityLight();
    } else if (lightType.equals(AMBIENT)) {
      light = new AmbientLight();
    } else if (lightType.equals(POINT)) {
      light = new PointLight();
    } else {
      throw new RuntimeException();
    }

    light.setId(id);

    light.setPosition(readTriple(input));
    light.setDirection(readTriple(input).normalized());

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

  /**
   * Creates a material with the properties specified on the current line of the
   * scanner.
   * 
   * @param input
   *          The scanner from which to read the properties of the material.
   * @return A material with the properties specified on the current line of the
   *         scanner.
   * @throws FileFormatException
   *           If the specified type of material is not recognized.
   */
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

  /**
   * Creates an orientation from the next three triples of double values from
   * the specified scanner.
   * 
   * @param input
   *          The scanner from which to read the orientation.
   * @return An orientation whose three vectors have the values read from the
   *         input.
   */
  protected static Orientation readOrientation(final Scanner input) {
    final Vector3D v1 = readTriple(input);
    final Vector3D v2 = readTriple(input);
    final Vector3D v3 = readTriple(input);

    return new Orientation(v1.normalized(), v2.normalized(), v3.normalized());
  }

  /**
   * Returns the resolution at which to display the scene in the viewport.
   * 
   * @param input
   *          The scanner from which to read the two double values which specify
   *          the resolution.
   * @return The resolution at which to display the scene in the viewport.
   */
  protected static Resolution readResolution(final Scanner input) {
    final double x = input.nextDouble();
    final double y = input.nextDouble();

    final Resolution resolution = new Resolution();
    resolution.setxResolution(x);
    resolution.setyResolution(y);

    return resolution;
  }

  /**
   * Creates a sphere with the properties specified on the current line of the
   * scanner.
   * 
   * @param input
   *          The scanner from which to read the properties of the sphere.
   * @param materials
   *          The list of known materials which the input will reference when
   *          describing the material of the sphere by its ID number.
   * @return A sphere with the properties specified on the current line of the
   *         scanner.
   */
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

  /**
   * Creates a surface object with the properties specified on the current line
   * of the scanner.
   * 
   * Post-condition: the list of surface objects is not modified.
   * 
   * @param input
   *          The scanner from which to read the properties of the surface
   *          object.
   * @param materials
   *          The list of known materials which the input will reference when
   *          describing the material of the ellipsoid by its ID number.
   * @param surfaceObjects
   *          The list of known surface objects which the input will reference
   *          when describing the two surface objects which comprise a
   *          constructive solid geometry object by ID number.
   * @return An surface object with the properties specified on the current line
   *         of the scanner.
   */
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

  /**
   * Returns a three-dimensional vector containing the next three double values
   * from the specified scanner.
   * 
   * @param input
   *          The scanner from which to read the three double values.
   * @return The vector containing the three double values read from the
   *         specified input.
   */
  protected static Vector3D readTriple(final Scanner input) {
    double x = input.nextDouble();
    double y = input.nextDouble();
    double z = input.nextDouble();
    return new Vector3D(x, y, z);
  }

  /**
   * Returns the dimensions of the viewport as defined in the input.
   * 
   * @param input
   *          The scanner from which to read the two integer values which
   *          specify the dimensions of the viewport.
   * @return The dimensions of the viewport on which to display the scene.
   */
  protected static Viewport readViewport(final Scanner input) {
    final int width = input.nextInt();
    final int height = input.nextInt();

    final Viewport viewport = new Viewport();
    viewport.setWidth(width);
    viewport.setHeight(height);

    return viewport;
  }
}
