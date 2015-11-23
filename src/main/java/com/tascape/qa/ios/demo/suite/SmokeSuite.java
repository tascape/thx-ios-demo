package com.tascape.qa.ios.demo.suite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tascape.qa.ios.demo.driver.IOS8Sampler;
import com.tascape.qa.ios.demo.test.SampleTests;
import com.tascape.qa.th.ios.driver.UiAutomationDevice;
import com.tascape.qa.th.ios.driver.LibIMobileDevice;
import com.tascape.qa.th.suite.AbstractSuite;
import java.util.List;

/**
 *
 * @author linsong wang
 */
public class SmokeSuite extends AbstractSuite {
    private static final Logger LOG = LoggerFactory.getLogger(SmokeSuite.class);

    private final IOS8Sampler app = new IOS8Sampler();

    private UiAutomationDevice device;

    @Override
    public void setUpTestClasses() {
        this.addTestClass(SampleTests.class);
    }

    @Override
    protected void setUpEnvironment() throws Exception {
        List<String> devices = LibIMobileDevice.getAllUuids();
        if (devices.isEmpty()) {
            throw new RuntimeException("Cannot find any device attached");
        }
        device = new UiAutomationDevice(devices.get(0));

        app.attachTo(device);
        app.launch();
        device.takeDeviceScreenshot();
        app.backToStart();

        this.putTestDirver(SampleTests.MOBILE_DEVICE, device);
        this.putTestDirver(SampleTests.SAMPLE_APP, app);
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
}
