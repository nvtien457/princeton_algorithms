#!/bin/bash

# This must match the install directory
INSTALL=.lift

# Sets the path to the classpath libraries
CLASSPATH=(.:${INSTALL}/algs4.jar)

# Add algs4.jar to classpath and various commmand-line options.
javac -cp "${CLASSPATH}" -g -encoding UTF-8 -Xlint:all -Xlint:-overrides -Xdiags:verbose -Xmaxwarns 10 -Xmaxerrs 10  "$@"
