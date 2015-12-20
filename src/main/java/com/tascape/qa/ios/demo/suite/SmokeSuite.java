/*
 * Copyright 2015 tascape.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tascape.qa.ios.demo.suite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tascape.qa.ios.demo.driver.Movies;
import com.tascape.qa.ios.demo.test.MoviesTests;
import com.tascape.qa.th.ios.driver.UiAutomationDevice;
import com.tascape.qa.th.ios.suite.UiAutomationTestSuite;
import com.tascape.qa.th.suite.AbstractSuite;

/**
 *
 * @author linsong wang
 */
public class SmokeSuite extends AbstractSuite implements UiAutomationTestSuite {
    private static final Logger LOG = LoggerFactory.getLogger(SmokeSuite.class);

    private final Movies app = new Movies();

    private UiAutomationDevice device;

    @Override
    public void setUpTestClasses() {
        this.addTestClass(MoviesTests.class);
    }

    @Override
    protected void setUpEnvironment() throws Exception {
        device = this.getAvailableDevice();
        app.attachTo(device);

        this.putTestDirver(MoviesTests.MOBILE_DEVICE, device);
        this.putTestDirver(MoviesTests.MOVIES_APP, app);
    }

    @Override
    protected void tearDownEnvironment() {
        if (device != null) {
            device.stop();
        }
    }

    @Override
    public String getProductUnderTest() {
        return app.getName();
    }

    @Override
    public String getProjectName() {
        return "thx-ios-demo";
    }

    public int getNumberOfEnvs() {
        return UUIDS.size();
    }
}
