#!/bin/bash

mvn clean package

java -cp target/th/*:target/dependency/* \
com.tascape.qa.th.ios.tools.JavaScriptDebugger Movies
