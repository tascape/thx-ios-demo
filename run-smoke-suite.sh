#!/bin/bash

mvn clean package
java -cp target/*:target/th/*:target/dependency/* \
com.tascape.qa.ios.demo.suite.SmokeSuite
