#!/bin/bash

echo 'run "mvn clean package" if needed'

java -cp target/*:target/dependency/* \
com.tascape.qa.th.ios.tools.UiAutomationViewer Movies
