#!/bin/sh
#
# jeffraytracer-gui.sh - simple script which runs the ray tracer GUI
#
# Copyright 2011 Jeffrey Finkelstein
#
# This file is part of jeffraytracer.
#
# jeffraytracer is free software: you can redistribute it and/or modify it
# under the terms of the GNU General Public License as published by the Free
# Software Foundation, either version 3 of the License, or (at your option)
# any later version.
#
# jeffraytracer is distributed in the hope that it will be useful, but WITHOUT
# ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
# FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
# more details.
#
# You should have received a copy of the GNU General Public License along with
# jeffraytracer. If not, see <http://www.gnu.org/licenses/>.

# the location of the Maven repository, where JAR files for dependencies live
M2_REPO=$HOME/.m2/repository

# change this to the path where your log4j JAR lives
LOG4JPATH=$M2_REPO/log4j/log4j/1.2.17/log4j-1.2.17.jar

# the main class for the ray tracer GUI
GUI_MAIN=jeffraytracer.main.gui.Main

java -classpath target/classes:$LOG4JPATH $GUI_MAIN
