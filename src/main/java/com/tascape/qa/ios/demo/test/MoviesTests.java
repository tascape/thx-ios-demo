/*
 * Copyright 2015 - 2016 Nebula Bay.
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
package com.tascape.qa.ios.demo.test;

import com.tascape.qa.ios.demo.driver.Movies;
import com.tascape.qa.th.data.TestDataProvider;
import com.tascape.qa.th.data.TestIterationData;
import org.junit.Test;
import com.tascape.qa.th.driver.TestDriver;
import com.tascape.qa.th.ios.driver.UiAutomationDevice;
import com.tascape.qa.th.ios.model.UIAButton;
import com.tascape.qa.th.ios.test.UiAutomationTest;
import com.tascape.qa.th.test.AbstractTest;
import java.awt.geom.Point2D;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.rules.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author linsong wang
 */
public class MoviesTests extends AbstractTest implements UiAutomationTest {
    private static final Logger LOG = LoggerFactory.getLogger(MoviesTests.class);

    public static final TestDriver MOBILE_DEVICE = new TestDriver(MoviesTests.class, UiAutomationDevice.class);

    public static final TestDriver MOVIES_APP = new TestDriver(MoviesTests.class, Movies.class);

    private final UiAutomationDevice device;

    private final Movies app;

    public MoviesTests() {
        this.globalTimeout = new Timeout(15, TimeUnit.MINUTES);
        this.device = super.getEntityDriver(MOBILE_DEVICE);
        this.app = super.getEntityDriver(MOVIES_APP);
    }

    @Before
    public void setup() throws Exception {
        device.startSysLog();
        app.launch();
        app.backToStart();
        device.takeDeviceScreenshot();
    }

    @After
    public void cleanup() throws Exception {
        device.stopSysLog();
        device.takeDeviceScreenshot();
    }

    @Test
    @TestDataProvider(klass = TestIterationData.class, method = "useIterations", parameter = "7")
    public void testRandomMovieIterations() throws Exception {
        app.showDetail();
        device.dragHalfScreenDown();
        device.dragFromToForDuration(new Point2D.Float(100, 400), new Point2D.Float(100, 0), 3);
        Assert.assertTrue("cannot find bookmark button",
            device.doesElementExist("window.tableViews()[0].cells()[0].buttons()[0]", UIAButton.class, "Bookmark"));
    }

    @Test
    public void testRandomMovie() throws Exception {
        app.showDetail();
        device.dragHalfScreenDown();
        device.dragFromToForDuration(new Point2D.Float(100, 400), new Point2D.Float(100, 0), 3);
        Assert.assertTrue("cannot find bookmark button",
            device.doesElementExist("window.tableViews()[0].cells()[0].buttons()[0]", UIAButton.class, "Bookmark"));
    }

    @Test
    public void testRelaunchFromDefault0() throws Exception {
        app.showDetail();
        for (int i = 0; i < 8; i++) {
            app.showASimilarMovie();
            device.takeDeviceScreenshot();
        }
        app.launch();
        Assert.assertTrue("this is not the default screen", app.isHomeScreen());
    }

    /**
     * This is a manual test case. You need to run it in desktop interactive session.
     *
     * @throws Exception in case of any issue
     */
    @Test
    public void testManualUiDebug() throws Exception {
        app.showDetail();
        app.interactManually(15);
    }

    @Override
    public String getApplicationUnderTest() {
        return app.getName();
    }
}
