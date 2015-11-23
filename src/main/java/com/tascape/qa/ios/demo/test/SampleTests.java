package com.tascape.qa.ios.demo.test;

import com.tascape.qa.ios.demo.driver.IOS8Sampler;
import org.junit.Test;
import com.tascape.qa.th.driver.TestDriver;
import com.tascape.qa.th.ios.driver.UiAutomationDevice;
import com.tascape.qa.th.test.AbstractTest;
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
public class SampleTests extends AbstractTest {
    private static final Logger LOG = LoggerFactory.getLogger(SampleTests.class);

    public static final TestDriver MOBILE_DEVICE = new TestDriver(SampleTests.class, UiAutomationDevice.class);

    public static final TestDriver SAMPLE_APP = new TestDriver(SampleTests.class, IOS8Sampler.class);

    private UiAutomationDevice device;

    private IOS8Sampler app;

    public SampleTests() {
        this.globalTimeout = new Timeout(5, TimeUnit.MINUTES);
    }

    @Before
    public void setup() throws Exception {
        this.device = this.getEntityDriver(MOBILE_DEVICE);
        this.app = this.getEntityDriver(SAMPLE_APP);
        app.backToStart();
        device.takeDeviceScreenshot();
    }

    @After
    public void cleanup() throws Exception {
        device.takeDeviceScreenshot();
    }

    @Test
    public void testAudoEffects() throws Exception {
        Assert.assertTrue("", true);
    }

    @Override
    public String getApplicationUnderTest() {
        return app.getName();
    }
}
