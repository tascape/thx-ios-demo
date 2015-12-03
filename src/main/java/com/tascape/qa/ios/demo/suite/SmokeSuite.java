package com.tascape.qa.ios.demo.suite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tascape.qa.ios.demo.driver.Movies;
import com.tascape.qa.ios.demo.test.MoviesTests;
import com.tascape.qa.th.ios.driver.UiAutomationDevice;
import com.tascape.qa.th.ios.driver.LibIMobileDevice;
import com.tascape.qa.th.suite.AbstractSuite;
import java.util.Collections;
import java.util.List;
import org.libimobiledevice.ios.driver.binding.exceptions.SDKException;

/**
 *
 * @author linsong wang
 */
public class SmokeSuite extends AbstractSuite {
    private static final Logger LOG = LoggerFactory.getLogger(SmokeSuite.class);

    private static final List<String> UUIDS;

    static {
        try {
            UUIDS = Collections.synchronizedList(LibIMobileDevice.getAllUuids());
        } catch (SDKException ex) {
            throw new RuntimeException(ex);
        }
    }

    private final Movies app = new Movies();

    private UiAutomationDevice device;

    @Override
    public int getNumberOfEnvs() {
        return UUIDS.size();
    }

    @Override
    public void setUpTestClasses() {
        this.addTestClass(MoviesTests.class);
    }

    @Override
    protected void setUpEnvironment() throws Exception {
        String uuid = UUIDS.remove(0);
        try {
            device = new UiAutomationDevice(uuid);

            app.attachTo(device);
            app.launch();
            device.takeDeviceScreenshot();
            app.backToStart();

            this.putTestDirver(MoviesTests.MOBILE_DEVICE, device);
            this.putTestDirver(MoviesTests.MOVIES_APP, app);
        } catch (Exception ex) {
            UUIDS.add(uuid);
            throw ex;
        }
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
