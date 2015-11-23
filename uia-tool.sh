#!/bin/bash

# please run "mvn clean install" first to copy all needed jars into target/ folder
java -cp target/th/*:target/dependency/* com.tascape.qa.th.ios.tools.JavaScriptDebugger iOS8Sampler
