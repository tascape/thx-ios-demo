package com.tascape.qa.ios.demo.driver;

import com.tascape.qa.th.driver.EntityDriver;
import com.tascape.qa.th.ios.driver.UiAutomationDevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author linsong wang
 */
public class IOS8Sampler extends EntityDriver {
    private static final Logger LOG = LoggerFactory.getLogger(IOS8Sampler.class);

    public static final String BUNDLE_ID = "com.shu223.iOS8SamplerFork";

    public static final String NAME = "iOS8Sampler";

    private UiAutomationDevice device;

    public void attachTo(UiAutomationDevice device) {
        this.device = device;
    }

    public void launch() throws Exception {
        device.start(IOS8Sampler.NAME);
    }

    public void backToStart() {
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void reset() throws Exception {
        LOG.debug("na");
    }

    public UiAutomationDevice getUiaDevice() {
        return device;
    }
}
