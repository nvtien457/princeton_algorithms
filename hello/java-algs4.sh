#!/bin/bash

# This must match the install directory.
INSTALL=.lift

# Sets the path to the textbook libraries.
CLASSPATH=(.:${INSTALL}/algs4.jar)

# Execute with textbook libraries in Java classpath.
java -cp "${CLASSPATH}" "$@"
