#!/bin/bash

echo 'run "mvn clean package" if needed'

java -cp target/th/*:target/dependency/* \
com.tascape.qa.th.ios.tools.JavaScriptDebugger Movies
