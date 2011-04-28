/**
 * ModelReader.java - reads a tracer model from a file
 */
package edu.bu.cs.cs480.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.bu.cs.cs480.FloatColor;
import edu.bu.cs.cs480.Identifiable;
import edu.bu.cs.cs480.Material;
import edu.bu.cs.cs480.camera.Camera;
import edu.bu.cs.cs480.camera.OrthographicCamera;
import edu.bu.cs.cs480.camera.PerspectiveCamera;
import edu.bu.cs.cs480.camera.Resolution;
import edu.bu.cs.cs480.camera.Viewport;
import edu.bu.cs.cs480.lights.AmbientLight;
import edu.bu.cs.cs480.lights.InfinityLight;
import edu.bu.cs.cs480.lights.Light;
import edu.bu.cs.cs480.lights.PointLight;
import edu.bu.cs.cs480.surfaces.Box;
import edu.bu.cs.cs480.surfaces.ConstructiveSolidGeometry;
import edu.bu.cs.cs480.surfaces.Cylinder;
import edu.bu.cs.cs480.surfaces.Ellipsoid;
import edu.bu.cs.cs480.surfaces.Orientation;
import edu.bu.cs.cs480.surfaces.Sphere;
import edu.bu.cs.cs480.surfaces.SurfaceObject;
import edu.bu.cs.cs480.surfaces.Union;
import edu.bu.cs.cs480.vectors.Vector3D;

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
   * The identifier for an ambient light source in the model file format.
   */
  public static final String AMBIENT = "amb";
  /** The identifier for the camera definition in the model file format. */
  public static final String CAMERA = "camera";
  /**
   * The identifier for a light source at distance infinity in the model file
   * format.
   */
  public static final String INFINITY = "inf";
  /**
   * The identifier for the intersection operation on surface objects in the
   * model file format.
   */
  public static final String INTERSECTION = "intersect";
  /** The identifier for a light definition in the model file format. */
  public static final String LIGHT = "light";
  /** The identifier for a material definition in the model file format. */
  public static final String MATERIAL = "mat";
  /** The identifier for an object definition in the model file format. */
  public static final String OBJECT = "obj";
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
   * The identifier for a point light source in the model file format.
   */
  public static final String POINT = "pnt";
  /** The identifier for a render list definition in the model file format. */
  public static final String RENDER = "render";
  /** The identifier for the resolution definition in the model file format. */
  public static final String RESOLUTION = "resolution";
  /**
   * The identifier for the symmetric difference operation on surface objects in
   * the model file format.
   */
  public static final String SYMMETRIC_DIFFERENCE = "difference";
  /**
   * The identifier for the union operation on surface objects in the model file
   * format.
   */
  public static final String UNION = "union";
  /** The identifier for the viewport definition in the model file format. */
  public static final String VIEWPORT = "viewport";

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

  /** The tracer environment which can render the scene read by this class. */
  private final TracerEnvironment environment = new TracerEnvironment();

  /** Whether the current light being read is an ambient light. */
  private boolean isAmbientLight = false;

  /** The scanner which reads the model file. */
  private final Scanner scanner;

  /**
   * Instantiates this reader with the model at the specified filename.
   * location.
   * 
   * @param filename
   *          The name of the file containing the description of the tracer
   *          model.
   * @throws FileNotFoundException
   *           If no file exists at the specified location.
   * @throws FileFormatException
   *           If the file is not in the correct format, as specified by
   *           "model_file_format.txt".
   */
  public ModelReader(final String filename) throws FileNotFoundException,
      FileFormatException {
    this.scanner = new Scanner(new File(filename));

    // TODO use hashmap so we don't have to iterate to find objects by id
    final List<Material> materials = new ArrayList<Material>();
    final List<SurfaceObject> surfaceObjects = new ArrayList<SurfaceObject>();
    List<Integer> toRender = null;

    // parse the file!
    while (this.scanner.hasNext()) {
      final String token = this.scanner.next();

      // if this line starts with a hash, it is a comment so throw it out
      if (token.charAt(0) == '#') {
        this.scanner.nextLine();
        continue;
      }

      // otherwise delegate the parsing to the appropriate method
      if (token.equals(CAMERA)) {
        this.environment.setCamera(readCamera());
      } else if (token.equals(RESOLUTION)) {
        this.environment.setResolution(readResolution());
      } else if (token.equals(VIEWPORT)) {
        this.environment.setViewport(readViewport());
      } else if (token.equals(LIGHT)) {
        // this boolean value will be set to true in the readLight() method if
        // the light is an ambient light
        this.isAmbientLight = false;
        final Light light = readLight();
        if (this.isAmbientLight) {
          this.environment.addAmbientLight((AmbientLight) light);
        } else {
          this.environment.addLight(light);
        }
      } else if (token.equals(MATERIAL)) {
        materials.add(readMaterial());
      } else if (token.equals(OBJECT)) {
        surfaceObjects.add(readSurfaceObject(materials, surfaceObjects));
      } else if (token.equals(RENDER)) {
        toRender = readIntegerList();
      } else {
        throw new FileFormatException("Do not understand declaration \""
            + token + "\".");
      }
    }

    // determine which surface objects should be added to the environment based
    // on the render list
    final List<SurfaceObject> toAdd;
    if (toRender.isEmpty()) {
      toAdd = surfaceObjects;
    } else {
      toAdd = new ArrayList<SurfaceObject>();
      for (final int id : toRender) {
        toAdd.add(getObjectWithID(surfaceObjects, id));
      }
    }

    // add those surface objects to the environment
    for (final SurfaceObject surfaceObject : toAdd) {
      this.environment.addSurfaceObject(surfaceObject);
    }
  }

  /**
   * @return
   */
  public TracerEnvironment environment() {
    return this.environment;
  }

  /**
   * Creates a box with the properties specified on the current line of the
   * scanner.
   * 
   * @param materials
   *          The list of known materials which the input will reference when
   *          describing the material of the box by its ID number.
   * @return A box with the properties specified on the current line of the
   *         scanner.
   */
  protected Box readBox(final List<Material> materials) {
    final Box box = new Box();

    this.scanner.next(); // throw away the string "ID"
    final int id = this.scanner.nextInt();
    box.setId(id);

    // throw away the strings "mat" and "ID"
    this.scanner.next();
    this.scanner.next();
    final int materialID = this.scanner.nextInt();
    box.setMaterial(getObjectWithID(materials, materialID));

    box.setPosition(readTriple());
    box.setOrientation(readOrientation());
    box.setDimensions(readTriple());

    return box;
  }

  /**
   * Creates a camera with the properties specified on the current line of the
   * scanner.
   * 
   * @return A camera with the properties specified on the current line of the
   *         scanner.
   * @throws FileFormatException
   *           If the projection type is not of a known type.
   */
  protected Camera readCamera() throws FileFormatException {
    final Camera camera;

    final String projectionType = this.scanner.next();

    final Vector3D center = readTriple();
    final Vector3D lookAt = readTriple();
    final Vector3D up = readTriple();

    double focalLength = this.scanner.nextDouble();
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

    double near = this.scanner.nextDouble();
    double far = this.scanner.nextDouble();
    camera.setNear(near);
    camera.setFar(far);

    return camera;
  }

  /**
   * Reads the next three float values between 0 and 1 from the Scanner and
   * returns the corresponding color.
   * 
   * @return The color whose component values are read from the specified input.
   */
  protected FloatColor readColor() {
    final float red = this.scanner.nextFloat();
    final float green = this.scanner.nextFloat();
    final float blue = this.scanner.nextFloat();
    return new FloatColor(red, green, blue);
  }

  /**
   * Creates a union, intersection, or symmetric difference object from two
   * other surface objects.
   * 
   * Post-condition: the list of surface objects is not modified.
   * 
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
  protected ConstructiveSolidGeometry readCSG(
      final List<SurfaceObject> surfaceObjects) throws FileFormatException {
    final ConstructiveSolidGeometry result;

    this.scanner.next(); // throw away the string "ID"
    final int id = this.scanner.nextInt();

    final String type = this.scanner.next();

    this.scanner.next(); // throw away the string "ID"
    final int leftId = this.scanner.nextInt();

    this.scanner.next(); // throw away the string "ID"
    final int rightId = this.scanner.nextInt();

    final SurfaceObject leftObject = getObjectWithID(surfaceObjects, leftId);
    final SurfaceObject rightObject = getObjectWithID(surfaceObjects, rightId);

    if (type.equals(UNION)) {
      result = new Union(leftObject, rightObject);
    } else if (type.equals(INTERSECTION)) {
      // result = new Intersection(leftObject, rightObject);
      result = null;
    } else if (type.equals(SYMMETRIC_DIFFERENCE)) {
      // result = new SymmetricDifference(leftObject, rightObject);
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
   * @param materials
   *          The list of known materials which the input will reference when
   *          describing the material of the cylinder by its ID number.
   * @return A cylinder with the properties specified on the current line of the
   *         scanner.
   */
  protected Cylinder readCylinder(final List<Material> materials) {
    final Cylinder cylinder = new Cylinder();

    this.scanner.next(); // throw away the string "ID"
    final int id = this.scanner.nextInt();
    cylinder.setId(id);

    // throw away the strings "mat" and "ID"
    this.scanner.next();
    this.scanner.next();
    final int materialID = this.scanner.nextInt();
    cylinder.setMaterial(getObjectWithID(materials, materialID));

    cylinder.setPosition(readTriple());
    cylinder.setDirection(readTriple().normalized());

    final double radius = this.scanner.nextDouble();
    final double length = this.scanner.nextDouble();
    cylinder.setRadius(radius);
    cylinder.setLength(length);

    return cylinder;
  }

  /**
   * Creates an ellipsoid with the properties specified on the current line of
   * the scanner.
   * 
   * @param materials
   *          The list of known materials which the input will reference when
   *          describing the material of the ellipsoid by its ID number.
   * @return An ellipsoid with the properties specified on the current line of
   *         the scanner.
   */
  protected Ellipsoid readEllipsoid(final List<Material> materials) {
    final Ellipsoid ellipsoid = new Ellipsoid();

    this.scanner.next(); // throw away the string "ID"
    final int id = this.scanner.nextInt();
    ellipsoid.setId(id);

    // throw away the strings "mat" and "ID"
    this.scanner.next();
    this.scanner.next();
    final int materialID = this.scanner.nextInt();
    ellipsoid.setMaterial(getObjectWithID(materials, materialID));

    ellipsoid.setPosition(readTriple());
    ellipsoid.setRadii(readTriple());

    return ellipsoid;
  }

  /**
   * Reads a sequence of consecutive integers IDS of arbitrary length from the
   * specified input.
   * 
   * @return A list of integers read from the input.
   */
  protected List<Integer> readIntegerList() {
    final List<Integer> result = new ArrayList<Integer>();
    // read the first number, which is the number of IDs to read
    for (int numToRead = this.scanner.nextInt(); numToRead > 0; numToRead--) {
      // throw away the "ID" token
      this.scanner.next();
      result.add(this.scanner.nextInt());
    }

    return result;
  }

  /**
   * Creates a light with the properties specified on the current line of the
   * scanner.
   * 
   * @return A light with the properties specified on the current line of the
   *         scanner.
   * @throws FileFormatException
   *           If the specified type of light is not recognized.
   */
  protected Light readLight() throws FileFormatException {
    final Light light;

    this.scanner.next(); // throw away the string "ID"
    final int id = this.scanner.nextInt();
    final String lightType = this.scanner.next();

    if (lightType.equals(INFINITY)) {
      light = new InfinityLight();
    } else if (lightType.equals(AMBIENT)) {
      light = new AmbientLight();
      this.isAmbientLight = true;
    } else if (lightType.equals(POINT)) {
      light = new PointLight();
    } else {
      throw new RuntimeException("Do not understand light type " + lightType
          + ".");
    }

    light.setId(id);

    light.setPosition(readTriple());
    light.setDirection(readTriple().normalized());

    light.setColor(readColor());

    light.setAttenuationCoefficients(readTriple());

    final int exponent = this.scanner.nextInt();
    light.setAttenuationExponent(exponent);

    final String shadow = this.scanner.next();
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
   * @return A material with the properties specified on the current line of the
   *         scanner.
   * @throws FileFormatException
   *           If the specified type of material is not recognized.
   */
  protected Material readMaterial() {
    final Material material = new Material();

    this.scanner.next(); // throw away the string "ID"
    final int id = this.scanner.nextInt();
    material.setId(id);

    material.setColor(readColor());

    final double ambientCoefficient = this.scanner.nextDouble();
    final double diffuseCoefficient = this.scanner.nextDouble();
    final double specularCoefficient = this.scanner.nextDouble();
    material.setAmbientReflection(ambientCoefficient);
    material.setDiffuseReflection(diffuseCoefficient);
    material.setSpecularReflection(specularCoefficient);

    final double specularExponent = this.scanner.nextDouble();
    material.setSpecularExponent(specularExponent);

    final double transmission = this.scanner.nextDouble();
    final double reflection = this.scanner.nextDouble();
    final double refraction = this.scanner.nextDouble();
    material.setTransmission(transmission);
    material.setReflection(reflection);
    material.setRefraction(refraction);

    return material;
  }

  /**
   * Creates an orientation from the next three triples of double values from
   * the specified scanner.
   * 
   * @return An orientation whose three vectors have the values read from the
   *         input.
   */
  protected Orientation readOrientation() {
    final Vector3D v1 = readTriple();
    final Vector3D v2 = readTriple();
    final Vector3D v3 = readTriple();

    return new Orientation(v1.normalized(), v2.normalized(), v3.normalized());
  }

  /**
   * Returns the resolution at which to display the scene in the viewport.
   * 
   * @return The resolution at which to display the scene in the viewport.
   */
  protected Resolution readResolution() {
    final double x = this.scanner.nextDouble();
    final double y = this.scanner.nextDouble();

    final Resolution resolution = new Resolution();
    resolution.setxResolution(x);
    resolution.setyResolution(y);

    return resolution;
  }

  /**
   * Creates a sphere with the properties specified on the current line of the
   * scanner.
   * 
   * @param materials
   *          The list of known materials which the input will reference when
   *          describing the material of the sphere by its ID number.
   * @return A sphere with the properties specified on the current line of the
   *         scanner.
   */
  protected Sphere readSphere(final List<Material> materials) {
    final Sphere sphere = new Sphere();

    this.scanner.next(); // throw away the string "ID"
    final int id = this.scanner.nextInt();
    sphere.setId(id);

    // throw away the strings "mat" and "ID"
    this.scanner.next();
    this.scanner.next();
    final int materialID = this.scanner.nextInt();
    sphere.setMaterial(getObjectWithID(materials, materialID));

    sphere.setPosition(readTriple());

    final double radius = this.scanner.nextDouble();
    sphere.setRadius(radius);

    return sphere;
  }

  /**
   * Creates a surface object with the properties specified on the current line
   * of the scanner.
   * 
   * Post-condition: the list of surface objects is not modified.
   * 
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
  protected SurfaceObject readSurfaceObject(final List<Material> materials,
      final List<SurfaceObject> surfaceObjects) throws FileFormatException {
    final SurfaceObject surfaceObject;

    final String type = this.scanner.next();
    if (type.equals("sphere")) {
      surfaceObject = readSphere(materials);
    } else if (type.equals("ellipsoid")) {
      surfaceObject = readEllipsoid(materials);
    } else if (type.equals("cylinder")) {
      surfaceObject = readCylinder(materials);
    } else if (type.equals("box")) {
      surfaceObject = readBox(materials);
    } else if (type.equals("CSG")) {
      surfaceObject = readCSG(surfaceObjects);
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
   * @return The vector containing the three double values read from the
   *         specified input.
   */
  protected Vector3D readTriple() {
    double x = this.scanner.nextDouble();
    double y = this.scanner.nextDouble();
    double z = this.scanner.nextDouble();
    return new Vector3D(x, y, z);
  }

  /**
   * Returns the dimensions of the viewport as defined in the input.
   * 
   * @return The dimensions of the viewport on which to display the scene.
   */
  protected Viewport readViewport() {
    final int width = this.scanner.nextInt();
    final int height = this.scanner.nextInt();

    final Viewport viewport = new Viewport();
    viewport.setWidth(width);
    viewport.setHeight(height);

    return viewport;
  }
}
