jeffraytracer
=============

This package contains a ray tracer which supports quadric surfaces (spheres and
ellipses), linear surfaces (planes and boxes) and combinations of the two
(cylinders).

This file was last updated on 7 September 2011.

Copyright license
-----------------

This package is distributed under the terms of the GNU General Public License
version 3. For more information, see the file `COPYING` in this directory.

Getting started
---------------

This project uses [Maven](http://maven.apache.org/) for build management and
requires Java 1.6 to compile.

To package this project into a JAR file, run

    mvn package

This produces an executable JAR file in the `target/` directory.

To compile this project, run

    mvn compile

To test this project, run

    mvn test

To get project reports and development information, run

    mvn site
  
You can then view the site by opening `target/site/index.html` in a web
browser.

Running the GUI
---------------

A *very* simple graphical user interface is provided, so you can easily view
the results of tracing a model. In order to use the GUI, first compile the ray
tracer by running

    mvn compile

then run

    ./jeffraytracer-gui.sh

Use the "File > Load model..." command to load and trace a model file. The
results will be shown in the window when tracing has completed. Some example
models are provided in the `src/test/resources/` directory.

Writing models
--------------

The model file format is specified in model_file_format.txt. Example files are
provided in the `src/test/resources/` directory.

Development
-----------

To prepare this package for development in Eclipse, run

    mvn eclipse:eclipse -DdownloadSources=true -DdownloadJavadocs=true

and

    mvn eclipse:configure-workspace -Declipse.workspace=/path/to/workspace

where `/path/to/workspace` is the path to your Eclipse workspace.

Eclipse automatic source formatting rules can be found in `formatter.xml` in
the top-level directory of this project.

To use Checkstyle, the Maven Checkstyle plugin, or the Eclipse Checkstyle
plugin, use the `checkstyle.xml` file in the top-level directory of this
project.

Contact
-------

Jeffrey Finkelstein <jeffrey.finkelstein@gmail.com>
