#!/bin/bash

mvn clean package
java -cp target/*:target/th/*:target/dependency/* \
     -Dqa.th.exec.thread.count=0 \
com.tascape.qa.ios.demo.suite.SmokeSuite
