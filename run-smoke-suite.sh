#!/bin/bash

echo 'run "mvn clean package" if needed'

java -cp target/*:target/th/*:target/dependency/* \
com.tascape.qa.ios.demo.suite.SmokeSuite
