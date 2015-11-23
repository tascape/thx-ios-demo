#!/bin/bash

# please run "mvn clean install" first to copy all needed jars into target/ folder
java -cp target/*:target/th/*:target/dependency/* com.tascape.qa.ios.demo.suite.SmokeSuite
