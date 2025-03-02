#!/bin/bash

# This must match the install directory.
INSTALL=algs4

# Sets the path to the textbook libraries.
CLASSPATH="${INSTALL}\algs4.jar;."

# Execute with textbook libraries in Java classpath.
java -cp "${CLASSPATH}" "$@"
