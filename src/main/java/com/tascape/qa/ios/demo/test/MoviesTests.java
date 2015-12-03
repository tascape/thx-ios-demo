package com.tascape.qa.ios.demo.test;

import com.tascape.qa.ios.demo.driver.Movies;
import com.tascape.qa.th.data.TestDataProvider;
import com.tascape.qa.th.data.TestIterationData;
import org.junit.Test;
import com.tascape.qa.th.driver.TestDriver;
import com.tascape.qa.th.ios.driver.UiAutomationDevice;
import com.tascape.qa.th.ios.model.UIAButton;
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
public class MoviesTests extends AbstractTest {
    private static final Logger LOG = LoggerFactory.getLogger(MoviesTests.class);

    public static final TestDriver MOBILE_DEVICE = new TestDriver(MoviesTests.class, UiAutomationDevice.class);

    public static final TestDriver MOVIES_APP = new TestDriver(MoviesTests.class, Movies.class);

    private final UiAutomationDevice device;

    private final Movies app;

    public MoviesTests() {
        this.globalTimeout = new Timeout(5, TimeUnit.MINUTES);
        this.device = super.getEntityDriver(MOBILE_DEVICE);
        this.app = super.getEntityDriver(MOVIES_APP);
    }

    @Before
    public void setup() throws Exception {
        app.backToStart();
        device.takeDeviceScreenshot();
    }

    @After
    public void cleanup() throws Exception {
        device.takeDeviceScreenshot();
    }

    @Test
    @TestDataProvider(klass = TestIterationData.class, method = "useIterations", parameter = "15")
    public void testRandomMovie() throws Exception {
        app.showDetail();
        device.dragFromToForDuration(new Point2D.Float(100, 400), new Point2D.Float(100, 0), 3);
        Assert.assertTrue("cannot find bookmark button",
            device.doesElementExist("window.tableViews()[0].cells()[0].buttons()[0]", UIAButton.class, "Bookmark"));
    }

    @Override
    public String getApplicationUnderTest() {
        return app.getName();
    }
}
